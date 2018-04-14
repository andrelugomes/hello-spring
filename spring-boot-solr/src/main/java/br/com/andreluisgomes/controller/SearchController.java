package br.com.andreluisgomes.controller;

import br.com.andreluisgomes.document.Job;
import br.com.andreluisgomes.repository.JobRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by andre on 23/01/17.
 */
@Api(value = "/search", description = "Serviço REST de Busca com filtros")
@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private JobRepository repository;

    @ApiOperation(value = "Service to find Jobs by text", httpMethod = "GET",
            notes ="Service to find Jobs by text")
    @ApiParam(name = "q",value = "Term of search", required = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Page.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error")})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Job>> byTerm(@RequestParam(value="q") String q,
                                            @PageableDefault(page = 0, size = 10) @ApiIgnore Pageable pageable){
        return new ResponseEntity<Page<Job>>(repository.findByCustomQuery(q, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Service to find Jobs by city name", httpMethod = "GET",
            notes ="Service to find Jobs by city name")
    @ApiParam(name = "city", value = "Name of City", required = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Page.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error")})
    @RequestMapping(value = "/by-city", method = RequestMethod.GET)
    public ResponseEntity<Page<Job>> byCity(@RequestParam(value="city") String city,
                                            @PageableDefault(page = 0, size = 10) @ApiIgnore Pageable pageable){
        return new ResponseEntity<Page<Job>>(repository.findByCidadeContains(city, pageable), HttpStatus.OK);
    }
}
