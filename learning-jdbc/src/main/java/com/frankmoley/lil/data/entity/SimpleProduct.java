package com.frankmoley.lil.data.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class SimpleProduct {

    private UUID productId;
    private String name;
    private BigDecimal price;
    private UUID vendorId;
    private String vendorName;
    private String contact;
    private String phoneNumer;
    private String email;
    private String address;
    
    public UUID getProductId() {
        return productId;
    }
    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public UUID getVendorId() {
        return vendorId;
    }
    public void setVendorId(UUID vendorId) {
        this.vendorId = vendorId;
    }
    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getPhoneNumer() {
        return phoneNumer;
    }
    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "SimpleProduct [productId=" + productId + ", name=" + name + ", price=" + price + ", vendorId="
                + vendorId + ", vendorName=" + vendorName + ", contact=" + contact + ", phoneNumer=" + phoneNumer
                + ", email=" + email + ", address=" + address + "]";
    }
}
