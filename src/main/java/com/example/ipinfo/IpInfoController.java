package com.example.ipinfo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IpInfoController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private String baseRequestUri = "http://ip-api.com/json";

    @GetMapping("/")
    public IpInfo getInfo(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
       
        IpInfo ipInfo = this.restTemplate.exchange(String.format("%s/%s", this.baseRequestUri, remoteAddr), HttpMethod.GET, null, IpInfo.class).getBody();
        
        if (ipInfo != null) {
            ipInfo.setIp(remoteAddr);
        }
        
        return ipInfo;      
    }
}
