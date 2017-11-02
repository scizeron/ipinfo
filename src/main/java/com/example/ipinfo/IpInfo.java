package com.example.ipinfo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpInfo {
   
    private String ip;
    
    private String city;
    
    private String country;
    
    private String countryCode;
    
    private String org;
    
    private String region;
    
    private String regionName;
}
