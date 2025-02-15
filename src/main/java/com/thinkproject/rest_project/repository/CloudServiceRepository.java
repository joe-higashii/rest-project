//CloudServiceRepository.java

package com.thinkproject.rest_project.repository;

import com.thinkproject.rest_project.model.CloudService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudServiceRepository extends JpaRepository<CloudService, Long> {
}

