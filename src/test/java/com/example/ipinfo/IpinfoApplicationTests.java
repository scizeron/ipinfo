package com.example.ipinfo;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IpinfoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void localhostClient() {   	    
	    ResponseEntity<IpInfo> ipInfo = restTemplate.getForEntity("/", IpInfo.class);
	    Assert.assertThat(ipInfo.getStatusCode(), CoreMatchers.is(HttpStatus.BAD_REQUEST));
	}

}
