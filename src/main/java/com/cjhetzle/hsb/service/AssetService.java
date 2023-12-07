package com.cjhetzle.hsb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjhetzle.hsb.entity.Asset;
import com.cjhetzle.hsb.repository.AssetRepository;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    public Optional<Asset> getAsset(Integer id) {
        return assetRepository.findById(id);
    }

    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    public void createAsset(Asset asset) {
        assetRepository.save(asset);
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
