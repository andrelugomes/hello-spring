package com.example.springboot2rest.repository;

import java.util.Optional;

import com.example.springboot2rest.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    Page<State> findByRegionId(Integer regionId, Pageable pageable);

    Optional<State> findByUf(String uf);

    Optional<State> findByCod(String cod);

    Page<State> findByNameContaining(String name, Pageable pageable); // like '%name%'
}
