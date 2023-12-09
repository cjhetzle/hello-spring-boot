package com.cjhetzle.hsb.repository;

import com.cjhetzle.hsb.entity.Asset;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface AssetRepository
        extends ListCrudRepository<Asset, Integer>,
        ListPagingAndSortingRepository<Asset, Integer> {

    List<Asset> findByName(String name);

    List<Asset> findAllByParentAsset(Integer id);
}
