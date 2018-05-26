package com.example.springboot2rest.mapper;

import java.util.List;

import com.example.springboot2rest.dto.StateDTO;
import com.example.springboot2rest.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface StateMapper {

    @Mappings({
        @Mapping(target = "cod", source = "cod"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "uf", source = "uf"),
        @Mapping(target = "region", source = "region.name")
    })
    StateDTO toDto(State state);

    @Mappings({
        @Mapping(target = "cod", source = "cod"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "uf", source = "uf"),
        @Mapping(target = "region", ignore = true)
    })
    State toEntity(StateDTO dto);

    List<StateDTO> statesToStatesDTO (List<State> states);

    default Page<StateDTO> statesToStatesDTO(Page<State> states) {
        List<StateDTO> statesDTO = statesToStatesDTO(states.getContent());
        return new PageImpl<>(statesDTO, states.getPageable(), states.getTotalElements());
    }
}
