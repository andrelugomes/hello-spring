package com.github.andrelugomes.model

import org.springframework.data.annotation.PersistenceConstructor
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "Municipio")
data class City(
    @Id
    @Column(name="Codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var code: Int,

    @Column(name="Nome")
    var name: String,

    @Column(name="Uf")
    var uf: Int
): Serializable {
    constructor(): this(0,"", 0) //for serialization
}