package com.example.ipinfo;

import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class IpInfoController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private String baseRequestUri = "http://ip-api.com/json";
    
    private static final Pattern PRIVATE_IP = Pattern.compile("(^127\\.)|(^10\\.)|(^172\\.1[6-9]\\.)|(^172\\.2[0-9]\\.)|(^172\\.3[0-1]\\.)|(^192\\.168\\.)");
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IpInfoController.class);
    
    private ObjectMapper mapper = new ObjectMapper();
    
    private String nodeLocation;
    
    private String nodeName;
    
    @PostConstruct
    public void init() {
       try {
           HttpHeaders httpHeaders = new HttpHeaders();
           httpHeaders.add("Metadata", "true");
           
           ResponseEntity<JsonNode> exchange = new RestTemplate().exchange("http://169.254.169.254/metadata/instance/compute?api-version=2017-04-02", HttpMethod.GET, new HttpEntity<>(httpHeaders), JsonNode.class);
           this.nodeLocation = exchange.getBody().get("location").asText();
           this.nodeName = exchange.getBody().get("name").asText();     
       } catch(Exception e) {
           LOGGER.debug("Failed to get the cloud metadata instance");
       }
      }
    
    
    @GetMapping("/")
    public ResponseEntity<IpInfo> getInfo(HttpServletRequest request) throws Exception {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
                   
        if (StringUtils.isEmpty(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }

        IpInfo ipInfo;
        
        if (StringUtils.isEmpty(remoteAddr)) {
            return handleBadRequest("The remoteAddr is empty, impossible to get additional information");
        }
        
        if (PRIVATE_IP.matcher(remoteAddr).find()) {
            return handleBadRequest(String.format("%s is a private IP, impossible to get additional information", remoteAddr));
        }        
        
        LOGGER.debug("Get additional info for \"{}\"", remoteAddr);
        
        ipInfo = this.restTemplate.exchange(String.format("%s/%s", this.baseRequestUri, remoteAddr), HttpMethod.GET, null, IpInfo.class).getBody();
        
        if (ipInfo != null) {
            ipInfo.setIp(remoteAddr);
            ipInfo.setNodeLocation(this.nodeLocation);
            ipInfo.setNodeName(this.nodeName);
        }
        
        if (LOGGER.isDebugEnabled() && ipInfo != null) {
            LOGGER.debug("{}: {}", remoteAddr, new String(this.mapper.writeValueAsBytes(ipInfo), "UTF-8"));
        }
                        
        return ResponseEntity.ok(ipInfo);      
    }

    /**
     * 
     * @param message
     * @return
     */
    private ResponseEntity<IpInfo> handleBadRequest(String message) {
        IpInfo ipInfo;
        LOGGER.warn(message);
        ipInfo = new IpInfo();
        ipInfo.setMessage(message);
        return ResponseEntity.badRequest().body(ipInfo);
    }
}
