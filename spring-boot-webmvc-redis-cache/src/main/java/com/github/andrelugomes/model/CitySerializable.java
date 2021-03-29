package com.github.andrelugomes.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Municipio")
public class CitySerializable implements Serializable {

    @Id
    @Column(name="Codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    @Column(name="Nome")
    private String name;

    @Column(name="Uf")
    private Integer uf;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUf() {
        return uf;
    }

    public void setUf(Integer uf) {
        this.uf = uf;
    }
}