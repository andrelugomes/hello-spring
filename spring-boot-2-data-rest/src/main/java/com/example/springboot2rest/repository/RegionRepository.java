package com.example.springboot2rest.repository;

import com.example.springboot2rest.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "regions", collectionResourceRel = "regions", itemResourceRel = "region")
public interface RegionRepository extends JpaRepository<Region, Integer> {

}
