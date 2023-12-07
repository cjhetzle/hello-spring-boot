package com.cjhetzle.hsb.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.cjhetzle.hsb.entity.Asset;

public interface AssetRepository
        extends ListCrudRepository<Asset, Integer>, ListPagingAndSortingRepository<Asset, Integer> {

    List<Asset> findByName(String name);
}
