package com.example.springboot2rest.repository;

import java.util.List;

import com.example.springboot2rest.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "staties", collectionResourceRel = "staties", itemResourceRel = "state")
public interface StateRepository extends JpaRepository<State, Integer> {

    @RestResource(path = "region")
    List<State> findByRegionId(@Param("regionId") Integer regionId);

    List<State> findByUf(@Param("uf") String uf);

}
