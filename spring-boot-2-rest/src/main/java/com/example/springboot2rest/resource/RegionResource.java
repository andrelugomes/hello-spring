package com.example.springboot2rest.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.springboot2rest.model.City;
import com.example.springboot2rest.model.Region;
import com.example.springboot2rest.model.State;
import com.example.springboot2rest.partial.RegionPartial;
import com.example.springboot2rest.repository.CityRepository;
import com.example.springboot2rest.repository.RegionRepository;
import com.example.springboot2rest.repository.StateRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/regions", produces = {API.V1})
@Api(value = "/regions", description = "All regions from Brazil")
public class RegionResource implements API {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    @ApiOperation(value = "View a list of available regions",
        response = Region.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 403, message = "Access is forbidden"),
        @ApiResponse(code = 404, message = "Resource is not found"),
        @ApiResponse(code = 500, message = "Something get wrong!")
    })
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
            value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
            value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
            value = "Sorting criteria in the format: property,[asc|desc]")
    })
    public Page<Region> regions(final Pageable pageable){
        return regionRepository.findAll(pageable);
    }

    @GetMapping(value = "/{region_id}/states")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property,[asc|desc]")
    })
    public Page<State> beCareful(@PathVariable("region_id") final Integer id, final Pageable pageable){
        return stateRepository.findByRegionId(id, pageable);
    }

    @GetMapping(value = "/{region_id}/states/{state_id}/cities", produces = { V1 }) //redundant
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property,[asc|desc]")
    })
    public Page<City> youCrossedTheLineV1(@PathVariable("region_id") final Integer regionId,
            @PathVariable("state_id") final Integer stateId,final Pageable pageable){

        final State state = stateRepository.findById(stateId).get();
        return cityRepository.findByStateId(state.getId(), pageable);
    }


    @GetMapping(value = "/{id}")
    public Region region(@PathVariable final Integer id){
        return regionRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Region create(@RequestBody @Valid final Region region){
        return regionRepository.save(region);
    }

    @PutMapping(value = "/{id}")
    public Region update(@PathVariable final Integer id, @RequestBody @Valid final Region region){
        final Region entity = regionRepository.findById(id).get();
        BeanUtils.copyProperties(region,entity);
        return regionRepository.save(entity);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable final Integer id){
        final Region region = regionRepository.findById(id).get();
        regionRepository.delete(region);
    }

    @PatchMapping(value = "/{id}")
    public Region partialUpdate(@PathVariable final Integer id, @RequestBody @NotNull final RegionPartial partials){
        final Region region = regionRepository.findById(id).get();
        region.setActive(partials.getActive() != null ? partials.getActive() : region.getActive());
        region.setName(partials.getName() != null ? partials.getName() : region.getName());
        return regionRepository.save(region);
    }
}
