package com.clinichub.core.tenant.domain;

import com.clinichub.core.shared.types.TenantId;
import com.clinichub.core.sharedkernel.domain.Address;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class Tenant {

    private TenantId id;
    private String name;
    private String email;
    private String phone;
    private Address address;
    private String logo;
    private boolean active;
    

    public Tenant(TenantId id, String name, String email, String phone, Address address, String logo) {
        setName(name);           
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        this.id = id;
        this.logo = logo;
        this.active = true;      
    }
    
    public void changeName(String name) {
        setName(name);
    }
    
    public void changeEmail(String email) {
        setEmail(email);
    }
    
    public void changePhone(String phone) {
        setPhone(phone);
    }
    
    public void changeAddress(Address address) {
        setAddress(address);
    }
    
    public void changeLogo(String logo) {
        this.logo = logo;
    }
    
    public void deactivate() {
        this.active = false;
    }
    
    public void activate() {
        this.active = true;
    }
    
 
    
    private void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tenant name is required");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Tenant name too long");
        }
        this.name = name.trim();
    }
    
    private void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        this.email = email.trim();
    }
    
    private void setPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone is required");
        }
        this.phone = phone.trim();
    }
    
    private void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address is required");
        }
        this.address = address;
    }
}




