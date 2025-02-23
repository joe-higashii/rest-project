//CloudVendorAPIService.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.config.documentation.CloudVendorAPIControllerApiOperations.*;
import com.thinkproject.rest_project.dto.CloudVendorDTO;
import com.thinkproject.rest_project.dto.request.CreateCloudVendorRequest;
import com.thinkproject.rest_project.dto.request.UpdateCloudVendorRequest;
import com.thinkproject.rest_project.service.CloudVendorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cloudvendor")
@SecurityRequirement(name = "bearerAuth")
public class CloudVendorAPIService {

    private final CloudVendorService cloudVendorService;

    @GetVendorDetails
    @GetMapping("/{vendorId}")
    public CloudVendorDTO getCloudVendorDetails(@PathVariable("vendorId") Long vendorId) {
        return cloudVendorService.getCloudVendorDTOById(vendorId);
    }

    @CreateVendor
    @PostMapping
    public CloudVendorDTO createCloudVendor(@Valid @RequestBody CreateCloudVendorRequest request) {
        CloudVendorDTO vendorDTO = new CloudVendorDTO(
            request.getVendorName(),
            request.getVendorAddress(),
            request.getVendorPhone()
        );
        return cloudVendorService.createVendor(vendorDTO);
    }

    @UpdateVendor
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{vendorId}")
    public CloudVendorDTO updateCloudVendor(@PathVariable("vendorId") Long vendorId,
                                            @Valid @RequestBody UpdateCloudVendorRequest request) {
        CloudVendorDTO vendorDTO = new CloudVendorDTO(
            request.getVendorName(),
            request.getVendorAddress(),
            request.getVendorPhone()
        );
        return cloudVendorService.updateVendor(vendorId, vendorDTO);
    }

    @DeleteVendor
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendor(@PathVariable("vendorId") Long vendorId) {
        cloudVendorService.deleteVendor(vendorId);
        return "Vendor deleted successfully";
    }
}

