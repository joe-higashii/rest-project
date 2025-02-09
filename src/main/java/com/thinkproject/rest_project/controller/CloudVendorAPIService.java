package com.thinkproject.rest_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.repository.CloudVendorRepository;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIService {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;

    @GetMapping("/{vendorId}")
    public CloudVendor getCloudVendorDetails(@PathVariable("vendorId") Long vendorId) {
        Optional<CloudVendor> cloudVendor = cloudVendorRepository.findById(vendorId);

        return cloudVendor.orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    @PostMapping
    public CloudVendor createCloudVendor(@RequestBody CloudVendor cloudVendor) {
        return cloudVendorRepository.save(cloudVendor);
    }

    @PutMapping("/{vendorId}")
    public CloudVendor updateCloudVendor(@PathVariable("vendorId") Long vendorId,
                                        @RequestBody CloudVendor cloudVendor) {
        if (!cloudVendorRepository.existsById(vendorId)) {
            throw new RuntimeException("Vendor not found");
        }

        cloudVendor = new CloudVendor(cloudVendor.getVendorName(), 
                                    cloudVendor.getVendorAddress(), 
                                    cloudVendor.getVendorPhone());
        cloudVendor.setVendorId(vendorId);

        return cloudVendorRepository.save(cloudVendor);
    }

    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendor(@PathVariable("vendorId") Long vendorId) {
        if (!cloudVendorRepository.existsById(vendorId)) {
            throw new RuntimeException("Vendor not found");
        }
        cloudVendorRepository.deleteById(vendorId);
        return "Vendor deleted successfully";
    }
}
