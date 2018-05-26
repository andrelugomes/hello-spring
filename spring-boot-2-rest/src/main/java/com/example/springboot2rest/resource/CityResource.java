package com.example.springboot2rest.resource;

import javax.validation.Valid;

import com.example.springboot2rest.mapper.CityMapper;
import com.example.springboot2rest.dto.CityDTO;
import com.example.springboot2rest.model.City;
import com.example.springboot2rest.repository.CityRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cities")
public class CityResource {

    @Autowired
    private CityRepository repository;

    @Autowired
    private CityMapper cityMapper;

    @GetMapping(produces = API.V1)
    @ApiOperation(value = "Get all Cities ", notes = "V1", response = City.class, produces = API.V1)
    public Page<City> cities(@RequestParam(value = "uf", required = false) String uf,
            @RequestParam(value = "state_id", required = false) Integer stateId,
            @RequestParam(value = "region_id", required = false) Integer regionId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "cod", required = false) Integer cod,
            final Pageable pageable) {
        if (uf != null) {
            return repository.findByStateUf(uf, pageable);
        } else if (stateId != null) {
            return repository.findByStateId(stateId, pageable);
        } else if (regionId != null) {
            return repository.findByStateRegionId(regionId, pageable);
        } else if (name != null) {
            return repository.findByNameContaining(name, pageable);
        }else {
            return repository.findAll(pageable);
        }
    }

    @ApiOperation(value = "Get a City by ID", notes = "V1", response = City.class, produces = API.V1)
    @GetMapping(value = "/{city_id}", produces = API.V1)
    public City cityV1(@PathVariable("city_id") final Integer id){
        return repository.findById(id).get();
    }

    @ApiOperation(value = "Get a City by ID", notes = "V2", response = CityDTO.class, produces = API.V2)
    @GetMapping(value = "/{city_id}", produces = API.V2)
    public CityDTO cityV2(@PathVariable("city_id") final Integer id){
        final City city = repository.findById(id).get();
        return cityMapper.toDto(city);
    }

    @PostMapping(produces = API.V1)
    @ResponseStatus(code = HttpStatus.CREATED)
    public City create(@RequestBody @Valid final City city){
       final City save = repository.save(city);
        return repository.findById(save.getId()).get();
    }
}
