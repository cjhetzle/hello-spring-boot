package com.cjhetzle.hsb.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssetTest {

    private Asset asset;
    private static String assetName = "test-asset";
    private static Boolean assetIsPromoted = false;

    @BeforeEach
    void setup() {
        asset = new Asset(assetName, assetIsPromoted);
    }

    @Test
    void getAssetIdTest() {
        assertThat(asset.getId()).isNotNull().isInstanceOf(Integer.class)
                .isGreaterThanOrEqualTo(0);
    }

    @Test
    void assetNameTest() {
        assertThat(asset.getName()).isNotNull().isInstanceOf(String.class)
                .isEqualTo(assetName);

        String newAssetName = "new-test-asset";

        asset.setName(newAssetName);

        assertThat(asset.getName()).isNotNull().isInstanceOf(String.class)
                .isEqualTo(newAssetName);
    }

    @Test
    void assetIsPromotedTest() {
        assertThat(asset.getIsPromoted()).isNotNull()
                .isInstanceOf(Boolean.class).isEqualTo(assetIsPromoted);

        Boolean opposite = !assetIsPromoted;

        asset.setIsPromoted(opposite);

        assertThat(asset.getIsPromoted()).isNotNull()
                .isInstanceOf(Boolean.class).isEqualTo(opposite);
    }
}
