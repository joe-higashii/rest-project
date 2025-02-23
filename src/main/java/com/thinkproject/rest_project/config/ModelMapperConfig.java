//ModelMapperConfig.java
package com.thinkproject.rest_project.config;

import com.thinkproject.rest_project.dto.ClientDTO;
import com.thinkproject.rest_project.dto.CloudVendorDTO;
import com.thinkproject.rest_project.dto.CloudServiceDTO;
import com.thinkproject.rest_project.dto.UsageContractDTO;
import com.thinkproject.rest_project.mapper.GenericMapper;
import com.thinkproject.rest_project.mapper.ModelMapperGenericMapper;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.model.UsageContract;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public GenericMapper<Client, ClientDTO> clientMapper(ModelMapper modelMapper) {
        return new ModelMapperGenericMapper<>(modelMapper, Client.class, ClientDTO.class);
    }

    @Bean
    public GenericMapper<CloudService, CloudServiceDTO> cloudServiceMapper(ModelMapper modelMapper) {
        return new ModelMapperGenericMapper<>(modelMapper, CloudService.class, CloudServiceDTO.class);
    }

    @Bean
    public GenericMapper<UsageContract, UsageContractDTO> usageContractMapper(ModelMapper modelMapper) {
        return new ModelMapperGenericMapper<>(modelMapper, UsageContract.class, UsageContractDTO.class);
    }

    @Bean
    public GenericMapper<CloudVendor, CloudVendorDTO> cloudVendorMapper(ModelMapper modelMapper) {
        return new ModelMapperGenericMapper<>(modelMapper, CloudVendor.class, CloudVendorDTO.class);
    }
}

