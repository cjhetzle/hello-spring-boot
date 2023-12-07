package com.cjhetzle.hsb.service;

import com.cjhetzle.hsb.entity.Asset;
import com.cjhetzle.hsb.repository.AssetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    /**
     * @param id
     * @return Optional<Asset>
     */
    public Optional<Asset> getAsset(final Integer id) {
        return assetRepository.findById(id);
    }

    /**
     * @return List<Asset>
     */
    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    public void createAsset(final Asset asset) {
        assetRepository.save(asset);
    }

    public void deleteAsset(final Integer id) {
        assetRepository.deleteById(id);
    }

    public Asset promoteAsset(final Integer id) {
        Optional<Asset> optAss = assetRepository.findById(id);
        if (!optAss.isPresent()) {
            return null;
        }

        Asset asset = optAss.get();

        asset.setIsPromoted(!asset.getIsPromoted());

        // is this even necessary?
        assetRepository.save(asset);

        return asset;
    }
}
