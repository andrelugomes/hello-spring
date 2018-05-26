package com.example.springboot2rest.mapper;

import java.util.List;

import com.example.springboot2rest.dto.CityDTO;
import com.example.springboot2rest.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mappings({
        @Mapping(target = "cod", source = "cod"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "state", source = "state.uf")
    })
    CityDTO toDto(City city);

    List<CityDTO> ciciesToCitiesDto(List<City> user);

    default Page<CityDTO> ciciesToCitiesDto(Page<City> cities) {
        List<CityDTO> citiesDto = ciciesToCitiesDto(cities.getContent());
        return new PageImpl<>(citiesDto, cities.getPageable(), cities.getTotalElements());
    }
}
