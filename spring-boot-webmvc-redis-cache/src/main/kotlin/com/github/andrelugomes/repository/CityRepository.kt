package com.github.andrelugomes.repository

import com.github.andrelugomes.model.City
import com.github.andrelugomes.model.CitySerializable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : PagingAndSortingRepository<City, String> {

    fun findAllByUf(uf: Int, pageable: Pageable): Page<City>
}