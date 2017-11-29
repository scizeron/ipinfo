package com.example.ipinfo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ip Info {
    
    private String message;
    
    private String nodeLocation;
    
    private String nodeName;
   
    private String ip;
    
    private String city;
    
    private String country;
    
    private String countryCode;
    
    private String org;
    
    private String region;
    
    private String regionName;
}
