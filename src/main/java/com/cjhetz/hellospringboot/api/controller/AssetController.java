package com.cjhetz.hellospringboot.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjhetz.hellospringboot.api.model.Asset;
import com.cjhetz.hellospringboot.api.model.json.AssetDto;
import com.cjhetz.hellospringboot.service.AssetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("{id}")
    public Asset getAsset(@PathVariable("id") Integer id) {
        Optional<Asset> optAss = assetService.getAsset(id);
        if (optAss.isPresent())
            return optAss.get();
        return null;
    }

    @GetMapping
    public List<Asset> getAssets() {
        return assetService.getAssets();
    }

    @PostMapping
    public String createAsset(@RequestBody Asset asset) {
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
