//UsageContractRepository.java
package com.thinkproject.rest_project.repository;

import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.model.UsageContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsageContractRepository extends JpaRepository<UsageContract, Long> {

    @Query("SELECT uc.status, COUNT(uc) FROM UsageContract uc GROUP BY uc.status")
    List<Object[]> countContractsByStatus();

    @Query("SELECT uc.service FROM UsageContract uc WHERE uc.client.id = :clientId")
    List<CloudService> findServicesByClientId(Long clientId);

    @Query("SELECT uc.client FROM UsageContract uc WHERE uc.service.id = :serviceId")
    List<Client> findClientsByServiceId(Long serviceId);
}

