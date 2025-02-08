package com.thinkproject.rest_project.model;

public class CloudVendor
{
    private String vendorId;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhone;
    
    public CloudVendor() {
    }

    public CloudVendor(String vendorAddress, String vendorId, String vendorName, String vendorPhone) {
        this.vendorAddress = vendorAddress;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorPhone = vendorPhone;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

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
