//UsageContractService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.UsageContractDTO;
import com.thinkproject.rest_project.exception.ResourceNotFoundException;
import com.thinkproject.rest_project.model.UsageContract;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.repository.UsageContractRepository;
import com.thinkproject.rest_project.repository.ClientRepository;
import com.thinkproject.rest_project.repository.CloudServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsageContractService {

    private final UsageContractRepository usageContractRepository;
    private final ClientRepository clientRepository;
    private final CloudServiceRepository cloudServiceRepository;
    private final ModelMapper modelMapper;

    public List<UsageContractDTO> getAllUsageContractsAsDTO() {
        log.info("Fetching all usage contracts as DTOs");
        List<UsageContractDTO> dtos = usageContractRepository.findAll().stream()
                .map(contract -> modelMapper.map(contract, UsageContractDTO.class))
                .toList();
        log.info("Found {} usage contracts", dtos.size());
        return dtos;
    }

    public List<UsageContract> getContractsByStatus(String status) {
        log.info("Fetching contracts with status {}", status);
        List<UsageContract> contracts = usageContractRepository.findAll().stream()
                .filter(contract -> contract.getStatus().equalsIgnoreCase(status))
                .toList();
        log.info("Found {} contracts with status {}", contracts.size(), status);
        return contracts;
    }

    public List<UsageContract> getContractsByDateRange(Date startDate, Date endDate) {
        log.info("Fetching contracts between {} and {}", startDate, endDate);
        List<UsageContract> contracts = usageContractRepository.findAll().stream()
                .filter(contract -> !contract.getStartDate().before(startDate) &&
                                    (contract.getEndDate() == null || !contract.getEndDate().after(endDate)))
                .toList();
        log.info("Found {} contracts in the date range", contracts.size());
        return contracts;
    }

    public List<Object[]> getContractStatistics() {
        log.info("Fetching contract statistics");
        return usageContractRepository.countContractsByStatus();
    }

    public List<CloudService> getServicesByClient(Long clientId) {
        log.info("Fetching services for client with id {}", clientId);
        if (!clientRepository.existsById(clientId)) {
            log.error("Client not found with id {}", clientId);
            throw new ResourceNotFoundException("Cliente não encontrado com ID: " + clientId);
        }
        List<CloudService> services = usageContractRepository.findServicesByClientId(clientId);
        log.info("Found {} services for client {}", services.size(), clientId);
        return services;
    }

    public List<Client> getClientsByService(Long serviceId) {
        log.info("Fetching clients for service with id {}", serviceId);
        if (!cloudServiceRepository.existsById(serviceId)) {
            log.error("Service not found with id {}", serviceId);
            throw new ResourceNotFoundException("Serviço não encontrado com ID: " + serviceId);
        }
        List<Client> clients = usageContractRepository.findClientsByServiceId(serviceId);
        log.info("Found {} clients for service {}", clients.size(), serviceId);
        return clients;
    }

    public UsageContract createContract(UsageContract usageContract, Long clientId, Long serviceId) {
        log.info("Creating contract for client {} and service {}", clientId, serviceId);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> {
                    log.error("Client not found with id {}", clientId);
                    return new ResourceNotFoundException("Cliente não encontrado com ID: " + clientId);
                });
        CloudService service = cloudServiceRepository.findById(serviceId)
                .orElseThrow(() -> {
                    log.error("Service not found with id {}", serviceId);
                    return new ResourceNotFoundException("Serviço não encontrado com ID: " + serviceId);
                });
        usageContract.setClient(client);
        usageContract.setService(service);
        usageContract.setStartDate(new Date());
        UsageContract savedContract = usageContractRepository.save(usageContract);
        log.info("Created contract with id {}", savedContract.getId());
        return savedContract;
    }

    public UsageContract updateContract(Long id, String status, Date endDate) {
        log.info("Updating contract with id {}", id);
        UsageContract existingContract = usageContractRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Contract not found with id {}", id);
                    return new ResourceNotFoundException("Contrato não encontrado com ID: " + id);
                });
        existingContract.setStatus(status);
        existingContract.setEndDate(endDate);
        UsageContract updatedContract = usageContractRepository.save(existingContract);
        log.info("Updated contract with id {}", updatedContract.getId());
        return updatedContract;
    }

    public void deleteContract(Long id) {
        log.info("Deleting contract with id {}", id);
        UsageContract existingContract = usageContractRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Contract not found with id {}", id);
                    return new ResourceNotFoundException("Contrato não encontrado com ID: " + id);
                });
        usageContractRepository.delete(existingContract);
        log.info("Deleted contract with id {}", id);
    }
}

