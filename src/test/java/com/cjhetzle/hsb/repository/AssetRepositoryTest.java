package com.cjhetzle.hsb.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cjhetzle.hsb.entity.Asset;

@DataJpaTest(properties = {
        "spring.test.database.replace=none",
        "spring.datasource.url=jdbc:tc:postgresql:15-alpine:///assets"
})
class AssetRepositoryTest {

    @Autowired
    AssetRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.save(new Asset("Todo Item 1", true));
        repository.save(new Asset("Todo Item 2", false));
        repository.save(new Asset("Todo Item 3", false));
    }

    @Test
    void pass() {
        assertTrue(true);
    }

    @Test
    void findAll() {
        assertThat(repository.findAll()).hasSize(3);
    }

  @Test
        void getByName() {
        assertThat(repository.findByName("Asset 1")).isInstanceOf(Asset.class);
        assertThat(repository.findByName("Asset -1")).isNotInstanceOf(Asset.class);
        }

    @Test
    void getById() {
        assertThat(repository.findById(3)).isInstanceOf(Asset.class);
        assertThat(repository.findById(-1)).isNotInstanceOf(Asset.class);
    }

    @Test
    void deleteById() {
        repository.deleteById(1);
        assertThat(repository.findAll()).hasSize(2);
    }

    @Test
    void createAsset() {
        Asset asset1 = new Asset("Asset 4", false);
        repository.save(asset1);
        assertThat(repository.findAll()).hasSize(4);
    }
}