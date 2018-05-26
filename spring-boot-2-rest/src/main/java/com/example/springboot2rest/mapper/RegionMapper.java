package com.example.springboot2rest.mapper;

import com.example.springboot2rest.dto.RegionDTO;
import com.example.springboot2rest.model.Region;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    RegionDTO toDto(Region region);

    Region toEntity(RegionDTO dto);
}
