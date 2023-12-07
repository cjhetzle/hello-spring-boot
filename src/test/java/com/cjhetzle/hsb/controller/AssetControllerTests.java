package com.cjhetzle.hsb.controller;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.cjhetzle.hsb.entity.Asset;
import com.cjhetzle.hsb.repository.AssetRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
                refAsset1 = repository
                                .save(new Asset(refAsset1.getName(), refAsset1.getIsPromoted()));
                refAsset2 = repository
                                .save(new Asset(refAsset2.getName(), refAsset2.getIsPromoted()));
                refAsset3 = repository
                                .save(new Asset(refAsset3.getName(), refAsset3.getIsPromoted()));
        }

        @Test
        void getAssetByIdTest() {
                assertThat(
                                controller.getAsset(
                                                refAsset1.getId()))
                                                                .isEqualTo(refAsset1);

                assertThat(
                                controller.getAsset(
                                                refAsset2.getId()))
                                                                .isEqualTo(refAsset2);

                assertThat(
                                controller.getAsset(
                                                refAsset3.getId()))
                                                                .isEqualTo(refAsset3);
        }

        @Test
        void getAllAssetsTest() {
                assertThat(controller.getAssets())
                                .hasSize(3)
                                .contains(refAsset1, refAsset2, refAsset3);
        }

        @Test
        void createAssetsTest() {
                Asset refAsset4 = new Asset("Asset 4", false);

                assertThat(controller.createAsset(refAsset4))
                                .contains(refAsset4.getName(),
                                                String.valueOf(refAsset4.getId()),
                                                refAsset4.getIsPromoted().toString());

                assertThat(controller.getAssets())
                                .contains(refAsset4);
        }

        @Test
        void deleteAssetByIdTest() {
                assertThat(controller.deleteAsset(refAsset3.getId()))
                                .isEqualToIgnoringCase("success");

                assertThat(controller.getAssets())
                                .doesNotContain(refAsset3);
        }

        @Test
        void promoteAssetByIdTest() {
                assertThat(controller.getAsset(refAsset1.getId())
                                .getIsPromoted())
                                                .isEqualTo(refAsset1.getIsPromoted());

                refAsset1.setIsPromoted(!refAsset1.getIsPromoted());

                assertThat(controller.promoteAsset(refAsset1.getId()))
                                .contains(refAsset1.getIsPromoted().toString());
        }

}
