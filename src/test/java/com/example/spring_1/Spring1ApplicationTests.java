package com.example.spring_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

//
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Spring1ApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> devContainer = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    @Container
    private final GenericContainer<?> prodContainer = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);


    @Test
    void contextLoads() {
        Integer devPort = devContainer.getMappedPort(8080);
        //
        ResponseEntity<String> devEntity = restTemplate.getForEntity("http://localhost:" + devPort + "/profile", String.class);
        //
        Assertions.assertEquals("Current profile is dev", devEntity.getBody());
    }

    @Test
    void prodResponseTest() {
        Integer prodPort = prodContainer.getMappedPort(8081);
        //
        ResponseEntity<String> prodEntity = restTemplate.getForEntity("http://localhost:" + prodPort + "/profile", String.class);
        //
        Assertions.assertEquals("Current profile is production", prodEntity.getBody());
    }
}


