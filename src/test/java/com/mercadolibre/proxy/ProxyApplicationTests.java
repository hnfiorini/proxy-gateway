package com.mercadolibre.proxy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

//import redis.embedded.RedisServer;

@TestConfiguration
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class ProxyApplicationTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyApplicationTests.class);
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	TestRestTemplate template;

	//private RedisServer redisServer;
	
	@LocalServerPort
    String port;
	
	public ProxyApplicationTests() {
		//this.redisServer = new RedisServer(6379);
//		this.redisServer = RedisServer.builder()
//                .port(6379)
//                .setting("maxmemory 128M") //maxheap 128M
//                .build();
    }
	
	@PostConstruct
    public void postConstruct() {
        //redisServer.start();
    }
	
	@RepeatedTest(20)
    public void whenCallRedisGetThroughGateway_thenOKStatusOrIsReceived() {
        String url = "http://localhost:" + port + "/categories/MLA97994";

        ResponseEntity<String> r = restTemplate.getForEntity(url, String.class);

        LOGGER.info("Received: status->{}, reason->{}, remaining->{}", 
          r.getStatusCodeValue(), r.getStatusCode().getReasonPhrase(), 
          r.getHeaders().get("X-RateLimit-Remaining"));
    }
	
	@PreDestroy
    public void preDestroy() {
        //redisServer.stop();
    }

}
