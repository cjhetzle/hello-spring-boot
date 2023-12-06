package com.cjhetz.hellospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjhetz.hellospringboot.api.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
    
}
