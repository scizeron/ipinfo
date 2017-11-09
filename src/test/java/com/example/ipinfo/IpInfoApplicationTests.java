package com.example.ipinfo;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class IpInfoApplicationTests {

    @Autowired
    private TestRestTemplate client;
    
    @Autowired
    private RestTemplate restTemplate;
    
	@Test
	public void requestFromLocalhost() {   	  
	    // when
	    ResponseEntity<IpInfo> ipInfo = client.getForEntity("/", IpInfo.class);
	    // then
	    Assert.assertThat(ipInfo.getStatusCode(), CoreMatchers.is(HttpStatus.BAD_REQUEST));
	}

	@SuppressWarnings("unchecked")
    @Test
    public void requestWithPublicIP() {        
	    // given
	    final HttpHeaders headers = new HttpHeaders();
	    headers.add("X-Forwarded-For", "194.9.245.19");
	   
	    final IpInfo body = new IpInfo();
	    body.setCity("London");
	    body.setCountry("United Kingdom");
	    body.setCountryCode("GB");
	    body.setIp("194.9.245.19");
	    body.setRegion("ENG");
	    body.setRegionName("England");

	    Mockito.when(this.restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.eq(null)
	            , (Class<IpInfo>) Mockito.any())).thenReturn(new ResponseEntity<IpInfo>(body, HttpStatus.OK));
	     
	     // when
	    ResponseEntity<IpInfo> ipInfo = client.exchange("/",HttpMethod.GET, new HttpEntity<>(headers), IpInfo.class);
        
	    // then
	    Assert.assertThat(ipInfo.getStatusCode(), CoreMatchers.is(HttpStatus.OK));
	    Assert.assertThat(ipInfo.getBody().getCity(), CoreMatchers.is("London"));
	    Assert.assertThat(ipInfo.getBody().getCountry(), CoreMatchers.is("United Kingdom"));
	    Assert.assertThat(ipInfo.getBody().getCountryCode(), CoreMatchers.is("GB"));
	    Assert.assertThat(ipInfo.getBody().getIp(), CoreMatchers.is("194.9.245.19"));
	    Assert.assertThat(ipInfo.getBody().getRegion(), CoreMatchers.is("ENG"));
	    Assert.assertThat(ipInfo.getBody().getRegionName(), CoreMatchers.is("England"));    
    }
	
	
	@SuppressWarnings("unchecked")
    @Test
    public void requestContainingProxyIps() {        
        // given
        final HttpHeaders headers = new HttpHeaders();
        headers.add("X-Forwarded-For", "194.9.245.19, 172.35.89.16");
       
        final IpInfo body = new IpInfo();
        body.setCity("London");
        body.setCountry("United Kingdom");
        body.setCountryCode("GB");
        body.setIp("194.9.245.19");
        body.setRegion("ENG");
        body.setRegionName("England");

        Mockito.when(this.restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.eq(null)
                , (Class<IpInfo>) Mockito.any())).thenReturn(new ResponseEntity<IpInfo>(body, HttpStatus.OK));
         
         // when
        ResponseEntity<IpInfo> ipInfo = client.exchange("/",HttpMethod.GET, new HttpEntity<>(headers), IpInfo.class);
        
        // then
        Assert.assertThat(ipInfo.getStatusCode(), CoreMatchers.is(HttpStatus.OK));
        Assert.assertThat(ipInfo.getBody().getCity(), CoreMatchers.is("London"));
        Assert.assertThat(ipInfo.getBody().getCountry(), CoreMatchers.is("United Kingdom"));
        Assert.assertThat(ipInfo.getBody().getCountryCode(), CoreMatchers.is("GB"));
        Assert.assertThat(ipInfo.getBody().getIp(), CoreMatchers.is("194.9.245.19"));
        Assert.assertThat(ipInfo.getBody().getRegion(), CoreMatchers.is("ENG"));
        Assert.assertThat(ipInfo.getBody().getRegionName(), CoreMatchers.is("England"));    
    }
    
	
	@After
	public void afterTest() {
	   Mockito.reset(this.restTemplate);
	}
	
	
}
