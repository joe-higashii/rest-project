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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsageContractService {

    private final UsageContractRepository usageContractRepository;
    private final ClientRepository clientRepository;
    private final CloudServiceRepository cloudServiceRepository;

    public List<UsageContractDTO> getAllUsageContractsAsDTO() {
        return usageContractRepository.findAll().stream()
                .map(contract -> new UsageContractDTO(
                        contract.getId(),
                        contract.getStatus(),
                        contract.getStartDate(),
                        contract.getEndDate(),
                        new com.thinkproject.rest_project.dto.ClientDTO(contract.getClient().getId(), contract.getClient().getName(), contract.getClient().getEmail()),
                        new com.thinkproject.rest_project.dto.CloudServiceDTO(contract.getService().getId(), contract.getService().getName(), contract.getService().getDescription())
                )).toList();
    }

    public List<UsageContract> getContractsByStatus(String status) {
        return usageContractRepository.findAll().stream()
                .filter(contract -> contract.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    public List<UsageContract> getContractsByDateRange(Date startDate, Date endDate) {
        return usageContractRepository.findAll().stream()
                .filter(contract -> !contract.getStartDate().before(startDate) &&
                                    (contract.getEndDate() == null || !contract.getEndDate().after(endDate)))
                .toList();
    }

    public List<Object[]> getContractStatistics() {
        return usageContractRepository.countContractsByStatus();
    }

    public List<CloudService> getServicesByClient(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Cliente não encontrado com ID: " + clientId);
        }
        return usageContractRepository.findServicesByClientId(clientId);
    }

    public List<Client> getClientsByService(Long serviceId) {
        if (!cloudServiceRepository.existsById(serviceId)) {
            throw new ResourceNotFoundException("Serviço não encontrado com ID: " + serviceId);
        }
        return usageContractRepository.findClientsByServiceId(serviceId);
    }

    public UsageContract createContract(UsageContract usageContract, Long clientId, Long serviceId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clientId));
        CloudService service = cloudServiceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + serviceId));
        usageContract.setClient(client);
        usageContract.setService(service);
        usageContract.setStartDate(new Date());
        return usageContractRepository.save(usageContract);
    }

    public UsageContract updateContract(Long id, String status, Date endDate) {
        UsageContract existingContract = usageContractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato não encontrado com ID: " + id));
        existingContract.setStatus(status);
        existingContract.setEndDate(endDate);
        return usageContractRepository.save(existingContract);
    }

    public void deleteContract(Long id) {
        UsageContract existingContract = usageContractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato não encontrado com ID: " + id));
        usageContractRepository.delete(existingContract);
    }
}

