//JwtAuthenticationFilter.java
package com.thinkproject.rest_project.filter;

import com.thinkproject.rest_project.model.User;
import com.thinkproject.rest_project.repository.UserRepository;
import com.thinkproject.rest_project.util.JwtUtil;
import com.thinkproject.rest_project.util.AppConstants;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith(AppConstants.BEARER_PREFIX)) {
            String token = authHeader.substring(AppConstants.BEARER_PREFIX.length());
            String username = jwtUtil.extractUsername(token);

            if (jwtUtil.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Attempt to retrieve the user from the repository
                User user = userRepository.findByUsername(username).orElse(null);

                if (user != null) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(AppConstants.ROLE_PREFIX + user.getRole());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(authority)
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // Log the incident and continue without setting authentication
                    log.error("User not found for username extracted from token: {}", username);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}

