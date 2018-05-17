package com.example.springboot2rest.repository;

import java.util.List;

import com.example.springboot2rest.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByUf(@Param("uf") String uf);

}
