package com.cjhetzle.hsb.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cjhetzle.hsb.entity.Asset;

@DataJpaTest(properties = {
        "spring.test.database.replace=none",
        "spring.datasource.url=jdbc:tc:postgresql:15-alpine:///assets",
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
})
class AssetRepositoryTest {

    @Autowired
    AssetRepository repository;
    
    static Asset refAsset1, refAsset2, refAsset3;

    @BeforeAll
    static void init() {
        refAsset1 = new Asset("Asset 1", true);
        refAsset2 = new Asset("Asset 2", false);
        refAsset3 = new Asset("Asset 3", false); 
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        refAsset1 = repository.save(new Asset(refAsset1.getName(), refAsset1.getIsPromoted()));
        refAsset2 = repository.save(new Asset(refAsset2.getName(), refAsset2.getIsPromoted()));
        refAsset3 = repository.save(new Asset(refAsset3.getName(), refAsset3.getIsPromoted()));
    }

    @Test
    void findAll() {
        assertThat(repository.findAll()).hasSize(3);
    }

    @Test
    void getByName() {
        List<Asset> o1 = repository.findByName("Asset 1");
        List<Asset> o2 = repository.findByName("Asset -1");
        assertThat(o1).hasSize(1);
        assertThat(o2).isEmpty();
    }

    @Test
    void getById() {
        assertThat(repository.findById(refAsset2.getId()).isPresent()).isTrue();
        assertThat(repository.findById(-1).isPresent()).isFalse();
    }

    @Test
    void deleteById() {
        repository.deleteById(refAsset1.getId());
        assertThat(repository.findAll()).hasSize(2);
    }

    @Test
    void createAsset() {
        Asset asset1 = new Asset("Asset 4", false);
        repository.save(asset1);
        assertThat(repository.findAll()).hasSize(4);
    }
}