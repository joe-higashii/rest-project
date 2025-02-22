//RateLimitingFilter.java
package com.thinkproject.rest_project.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final int MAX_REQUESTS = 100; // Maximum requests allowed per window
    private final long WINDOW_SIZE_MS = 60 * 1000; // 1 minute window

    // Define a local constant for HTTP 429 Too Many Requests
    private static final int SC_TOO_MANY_REQUESTS = 429;

    // Map to store request count per IP and the start of the window
    private final Map<String, RateLimitInfo> ipRequestCounts = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String clientIp = request.getRemoteAddr();
        long currentTime = Instant.now().toEpochMilli();

        RateLimitInfo rateLimitInfo = ipRequestCounts.compute(clientIp, (ip, info) -> {
            if (info == null || currentTime - info.windowStart >= WINDOW_SIZE_MS) {
                // Start a new window
                return new RateLimitInfo(currentTime, new AtomicInteger(1));
            } else {
                info.requestCount.incrementAndGet();
                return info;
            }
        });

        if (rateLimitInfo.requestCount.get() > MAX_REQUESTS) {
            log.warn("Rate limit exceeded for IP: {}", clientIp);
            response.setStatus(SC_TOO_MANY_REQUESTS);
            response.getWriter().write("Too many requests - rate limit exceeded.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static class RateLimitInfo {
        long windowStart;
        AtomicInteger requestCount;

        RateLimitInfo(long windowStart, AtomicInteger requestCount) {
            this.windowStart = windowStart;
            this.requestCount = requestCount;
        }
    }
}

