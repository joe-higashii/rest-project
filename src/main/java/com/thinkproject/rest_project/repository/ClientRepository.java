//ClientRepository.java
package com.thinkproject.rest_project.repository;

import com.thinkproject.rest_project.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

