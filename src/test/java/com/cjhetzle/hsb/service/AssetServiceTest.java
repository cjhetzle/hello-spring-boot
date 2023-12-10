package com.cjhetzle.hsb.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cjhetzle.hsb.entity.Asset;
import com.cjhetzle.hsb.repository.AssetRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class AssetServiceTest {

    @LocalServerPort
    private Integer port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15.5-alpine");

    @Autowired
    AssetService service;

    @Autowired
    AssetRepository repository;

    static Asset refAsset1;
    static Asset refAsset2;
    static Asset refAsset3;

    @BeforeAll
    static void init() {
        refAsset1 = new Asset("Asset 1", true);
        refAsset2 = new Asset("Asset 2", false);
        refAsset3 = new Asset("Asset 3", false);
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        refAsset1 = repository.save(
                new Asset(refAsset1.getName(), refAsset1.getIsPromoted()));
        refAsset2 = repository.save(new Asset(refAsset2.getName(),
                refAsset2.getIsPromoted(), refAsset1.getId()));
        refAsset3 = repository.save(new Asset(refAsset3.getName(),
                refAsset3.getIsPromoted(), refAsset2.getId()));
    }

    @Test
    void deleteAsset_expectParentAssetIdRemoved() {
        service.deleteAsset(refAsset1.getId());

        assertThat(repository.findByName(refAsset2.getName()).get(0)
                .getParentAsset()).isNull();
    }

    @Test
    void promoteAsset_checkChildIsPromoted() {
        service.promoteAsset(refAsset2.getId());

        assertThat(repository.findById(refAsset2.getId()).get().getIsPromoted())
                .isTrue();

        assertThat(repository.findById(refAsset3.getId()).get().getIsPromoted())
                .isTrue();

        service.promoteAsset(refAsset1.getId());

        assertThat(repository.findById(refAsset1.getId()).get().getIsPromoted())
                .isFalse();

        assertThat(repository.findById(refAsset2.getId()).get().getIsPromoted())
                .isFalse();

        assertThat(repository.findById(refAsset3.getId()).get().getIsPromoted())
                .isFalse();

    }

    @Test
    void promoteInvalidAsset_shouldReturnNull() {
        assertThat(service.promoteAsset(-1)).isNull();
    }

    @Test
    void promoteHeadlessAsset_returnSuccess() {
        refAsset2.setParentAsset(-1);

        refAsset2 = repository.save(refAsset2);

        service.promoteAsset(refAsset1.getId());

        assertThat(repository.findById(refAsset1.getId()).get().getIsPromoted())
                .isFalse();

        assertThat(repository.findById(refAsset2.getId()).get().getIsPromoted())
                .isFalse();

        service.promoteAsset(refAsset2.getId());

        assertThat(repository.findById(refAsset1.getId()).get().getIsPromoted())
                .isFalse();

        assertThat(repository.findById(refAsset2.getId()).get().getIsPromoted())
                .isTrue();

        assertThat(repository.findById(refAsset3.getId()).get().getIsPromoted())
                .isTrue();
    }
}
