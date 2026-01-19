package com.clinichub.adapters.web.tenant.dto;

import lombok.Data;

@Data
public class UpdateTenantDto {

    private String name;
 
    private String email;

    private String phone;
    

    private String street;
    
    private String number;
    private String zip;
    
    private Long cityId;
    
    private String logo;
}
