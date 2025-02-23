//CloudServiceController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.config.documentation.CloudServiceControllerApiOperation.*;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.dto.CloudServiceDTO;
import com.thinkproject.rest_project.dto.request.CreateCloudServiceRequest;
import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.service.CloudServiceService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cloudservice")
@SecurityRequirement(name = "bearerAuth")
public class CloudServiceController {

    private final CloudServiceService cloudServiceService;

    @GetAllServices
    @GetMapping
    public List<CloudServiceDTO> getAllServices() {
        return cloudServiceService.getAllServicesAsDTO();
    }

    @GetClientServices
    @GetMapping("/{id}/clients")
    public List<?> getClientsByServiceId(
        @Parameter(description = "ID do serviço", required = true) @PathVariable Long id) {
        return cloudServiceService.getClientsByServiceId(id);
    }

    @CreateService
    @PostMapping
    public CloudService createService(
        @Parameter(description = "Detalhes do serviço a ser criado", required = true)
        @Valid @RequestBody CreateCloudServiceRequest request) {
            CloudService cloudService = new CloudService();
            cloudService.setName(request.getName());
            cloudService.setDescription(request.getDescription());
            CloudVendor vendor = new CloudVendor();
            vendor.setVendorId(request.getVendorId());
            cloudService.setVendor(vendor);
            return cloudServiceService.createService(cloudService);
    }
}

