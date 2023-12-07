package com.cjhetzle.hsb.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjhetzle.hsb.entity.Asset;
import com.cjhetzle.hsb.entity.json.AssetDto;
import com.cjhetzle.hsb.service.AssetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/assets")
public class AssetController {

    Logger logger = LoggerFactory.getLogger(AssetController.class);

    @Autowired
    private AssetService assetService;

    @GetMapping("{id}")
    public Asset getAsset(@PathVariable("id") Integer id) {
        logger.debug("entering getAsset");
        Optional<Asset> optAss = assetService.getAsset(id);
        if (optAss.isPresent())
            return optAss.get();
        return null;
    }

    @GetMapping
    public List<Asset> getAssets() {
        logger.warn("entering getAssets");
        return assetService.getAssets();
    }

    @PostMapping
    public String createAsset(@RequestBody Asset asset) {
        logger.debug("entering createAsset");
        assetService.createAsset(asset);
        String jsonResponse = "failed to parse.";
        try {
            jsonResponse = new ObjectMapper().writeValueAsString(AssetDto.fromEntity(asset));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    @DeleteMapping("{id}")
    public String deleteAsset(@PathVariable("id") Integer id) {
        try {
            assetService.deleteAsset(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Success";
    }

    @PostMapping("{id}")
    public String promoteAsset(@PathVariable("id") Integer id) {
        String jsonResponse = "failed to parse.";
        try {
            jsonResponse = new ObjectMapper().writeValueAsString(AssetDto.fromEntity(assetService.promoteAsset(id)));
        } catch (Exception e) {
            return e.getMessage();
        }
        return jsonResponse;
    }
}
