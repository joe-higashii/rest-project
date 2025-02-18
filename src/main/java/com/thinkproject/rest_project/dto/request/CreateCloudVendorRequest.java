package com.thinkproject.rest_project.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateCloudVendorRequest {

    @NotBlank(message = "Nome do fornecedor não pode ser vazio")
    private String vendorName;

    @NotBlank(message = "Endereço do fornecedor não pode ser vazio")
    private String vendorAddress;

    @NotBlank(message = "Telefone do fornecedor não pode ser vazio")
    private String vendorPhone;

    public String getVendorName() {
        return vendorName;
    }
 
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
 
    public String getVendorAddress() {
        return vendorAddress;
    }
 
    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }
 
    public String getVendorPhone() {
        return vendorPhone;
    }
 
    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }
}
