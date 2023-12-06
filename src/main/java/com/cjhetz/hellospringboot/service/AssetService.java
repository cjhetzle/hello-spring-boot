package com.cjhetz.hellospringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cjhetz.hellospringboot.api.model.Asset;
import com.cjhetz.hellospringboot.repository.AssetRepository;

@Service
public class AssetService {

    private AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        super();
        this.assetRepository = assetRepository;
    }

    public Optional<Asset> getAsset(Integer id) {
        return assetRepository.findById(id);
    }

    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    public void createAsset(Asset asset) {
        assetRepository.saveAndFlush(asset);
    }

    public void deleteAsset(Integer id) {
        assetRepository.deleteById(id);
    }

    public Asset promoteAsset(Integer id) {
        Optional<Asset> optAss = assetRepository.findById(id);
        if (!optAss.isPresent())
            return null;
        
        Asset asset = optAss.get();

        asset.setIsPromoted(asset.getIsPromoted() ? false : true);

        // is this even necessary?
        assetRepository.save(asset);

        return asset;
    }

}
