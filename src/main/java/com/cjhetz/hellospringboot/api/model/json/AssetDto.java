package com.cjhetz.hellospringboot.api.model.json;

import com.cjhetz.hellospringboot.api.model.Asset;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssetDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("isPromoted")
    private boolean isPromoted;

    public static AssetDto fromEntity(Asset asset) {
        AssetDto dto = new AssetDto();
        if (asset == null)
            return dto;
        dto.setId(asset.getId());
        dto.setName(asset.getName());
        dto.setPromoted(asset.getIsPromoted());
        return dto;
    }
}
