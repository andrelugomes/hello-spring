package com.example.springboot2rest.repository;

import java.util.Optional;

import com.example.springboot2rest.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    Page<City> findByStateId(Integer stateId, Pageable pageable);

    Page<City> findByStateUf(String uf, Pageable pageable);

    Page<City> findByStateRegionId(Integer regionId, Pageable pageable);

    Page<City> findByNameContaining(String name, Pageable pageable); // like '%name%'

    Optional<City> findByCod(Integer cod);
}
