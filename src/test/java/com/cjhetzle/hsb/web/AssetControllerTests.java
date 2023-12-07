package com.cjhetzle.hsb.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.cjhetzle.hsb.repository.AssetRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver" })
@Testcontainers
public class AssetControllerTests {

    @LocalServerPort
    private Integer port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    AssetRepository repository;

    @Autowired
    AssetController controller;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void getAssetByIdTest() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getAllAssetsTest() {
        assertTrue(true);
    }

    @Test
    void createAssetsTest() {
        assertTrue(true);
    }

    @Test
    void deleteAssetByIdTest() {
        assertTrue(true);
    }

    @Test
    void promoteAssetByIdTest() {
        assertTrue(true);
    }

}
