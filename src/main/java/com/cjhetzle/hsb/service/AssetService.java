package com.cjhetzle.hsb.service;

import com.cjhetzle.hsb.entity.Asset;
import com.cjhetzle.hsb.repository.AssetRepository;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {

    private Logger logger = LoggerFactory.getLogger(AssetService.class);

    @Autowired
    private AssetRepository assetRepository;

    public Optional<Asset> getAsset(final Integer id) {
        return assetRepository.findById(id);
    }

    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    public void createAsset(final Asset asset) {
        assetRepository.save(asset);
    }

    public void deleteAsset(final Integer id) {
        assetRepository.deleteById(id);
    }

    /**
     * Take the id for an Asset.  Get the Asset, flip the value
     * of 'isPromoted' then update the database entry.

     * @param id id of Asset to update
     * @return copy of new Asset
     */
    public Asset promoteAsset(final Integer id) {
        String methodName = "promoteAsset";
        logger.trace(String.format("entering %s...", methodName));
        Optional<Asset> optAss = assetRepository.findById(id);
        if (!optAss.isPresent()) {
            return null;
        }

        Asset asset = optAss.get();

        Boolean isPromoted = asset.getIsPromoted();
        
        logger.info(String.format(
                "Promotion status: %s -> %s", isPromoted, !isPromoted));

        asset.setIsPromoted(!isPromoted);
        
        logger.info(String.format(
                "Updating instance as: %s", asset.toString()));
        // is this even necessary?
        assetRepository.save(asset);

        logger.trace(String.format("exiting %s...", methodName));
        return asset;
    }
}
