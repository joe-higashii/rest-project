package com.thinkproject.rest_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.repository.CloudVendorRepository;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIService
{
    @Autowired
    private CloudVendorRepository cloudVendorRepository;

    @GetMapping("/{vendorId}")
    public CloudVendor getCloudVendorDetails(@PathVariable("vendorId") String vendorId)
    {
        Optional<CloudVendor> cloudVendor = cloudVendorRepository.findById(vendorId);
        
        return cloudVendor.orElseThrow(() -> new RuntimeException("Vendor not found"));
    }
}
