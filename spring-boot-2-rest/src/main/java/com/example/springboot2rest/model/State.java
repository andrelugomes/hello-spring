package com.example.springboot2rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Estado")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Codigo")
    private Integer cod;

    @Column(name = "Nome")
    private String name;

    @Column(name = "Uf")
    private String uf;

    @ManyToOne
    @JoinColumn(name = "Regiao")
    private Region region;

}
