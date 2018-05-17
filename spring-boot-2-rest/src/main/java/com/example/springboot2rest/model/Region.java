package com.example.springboot2rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "Regiao")
@ApiModel(value = "Region", description = "A region from Brazil")
public class Region {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database Region ID")
    private Integer id;

    @Column(name = "Nome")
    @NotNull(message = "Name not be null")
    @ApiModelProperty(notes = "The Region name")
    private String name;

    @Column(name = "Ativo")
    @NotNull(message = "Active status not be null")
    @ApiModelProperty(notes = "The Region is active")
    private Boolean active;
    
}
