package com.example.springboot2rest.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegionDTO {

    @NotNull
    private String name;
    
    @NotNull
    private Boolean active;
    
}
