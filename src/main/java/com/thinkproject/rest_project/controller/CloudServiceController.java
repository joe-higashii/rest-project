//CloudServiceController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.repository.CloudServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudservice")
public class CloudServiceController {

    @Autowired
    private CloudServiceRepository cloudServiceRepository;

    @GetMapping
    public List<CloudService> getAllServices() {
        return cloudServiceRepository.findAll();
    }

    @PostMapping
    public CloudService createService(@RequestBody CloudService cloudService) {
        return cloudServiceRepository.save(cloudService);
    }
}
