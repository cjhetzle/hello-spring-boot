package com.cjhetz.hellospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjhetz.hellospringboot.api.model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    Asset findByName(String name);
}
