package com.cjhetzle.hsb.entity.json;

import com.cjhetzle.hsb.entity.Asset;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDto {

    @JsonProperty
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("isPromoted")
    private boolean isPromoted;
    @JsonProperty("parentAsset")
    private Integer parentAsset;

    public static AssetDto fromEntity(final Asset asset) {
        AssetDto dto = new AssetDto();
        if (asset == null) {
            return dto;
        }

        dto.setId(asset.getId());
        dto.setName(asset.getName());
        dto.setPromoted(asset.getIsPromoted());
        dto.setParentAsset(asset.getParentAsset());
        return dto;
    }
}
