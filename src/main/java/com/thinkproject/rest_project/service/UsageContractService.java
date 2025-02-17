//UsageContractService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.model.UsageContract;
import com.thinkproject.rest_project.repository.UsageContractRepository;
import com.thinkproject.rest_project.repository.ClientRepository;
import com.thinkproject.rest_project.repository.CloudServiceRepository;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.CloudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsageContractService {

    @Autowired
    private UsageContractRepository usageContractRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CloudServiceRepository cloudServiceRepository;

    public List<UsageContract> getAllContracts() {
        return usageContractRepository.findAll();
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

    public UsageContract createContract(UsageContract usageContract) {
        
        // Validar cliente
        Long clientId = usageContract.getClient().getId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clientId));
        
        // Validar serviço
        Long serviceId = usageContract.getService().getId();
        CloudService service = cloudServiceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + serviceId));
        
        // Relacionar cliente e serviço ao contrato
        usageContract.setClient(client);
        usageContract.setService(service);

        // Registrar data de início
        usageContract.setStartDate(new Date());
        
        return usageContractRepository.save(usageContract);
    }

    public UsageContract updateContract(Long id, UsageContract usageContract) {
        UsageContract existingContract = usageContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado com ID: " + id));
        
        // Atualizar detalhes do contrato
        existingContract.setEndDate(usageContract.getEndDate());
        existingContract.setStatus(usageContract.getStatus());

        return usageContractRepository.save(existingContract);
    }

    public void deleteContract(Long id) {
        UsageContract existingContract = usageContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado com ID: " + id));
        
        usageContractRepository.delete(existingContract);
    }
}

