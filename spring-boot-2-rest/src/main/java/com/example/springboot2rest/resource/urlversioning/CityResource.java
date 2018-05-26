package com.example.springboot2rest.resource.urlversioning;

import com.example.springboot2rest.mapper.CityMapper;
import com.example.springboot2rest.dto.CityDTO;
import com.example.springboot2rest.model.City;
import com.example.springboot2rest.repository.CityRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("CityResourceUrlVersioning")
@RequestMapping(value = "/")
public class CityResource {

    @Autowired
    private CityRepository repository;

    @Autowired
    private CityMapper cityMapper;

    @ApiOperation(value = "Get a City by ID", response = City.class)
    @GetMapping(value = "/v1/cities/{city_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public City cityV1URL(@PathVariable("city_id") final Integer id){
        return repository.findById(id).get();
    }

    @ApiOperation(value = "Get a City by ID", response = CityDTO.class)
    @GetMapping(value = "/v2/cities/{city_id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CityDTO cityV2URL(@PathVariable("city_id") final Integer id){
        final City city = repository.findById(id).get();
        return cityMapper.toDto(city);
    }
}
