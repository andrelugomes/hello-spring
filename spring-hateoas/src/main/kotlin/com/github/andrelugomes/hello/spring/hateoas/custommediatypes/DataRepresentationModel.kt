package com.github.andrelugomes.hello.spring.hateoas.custommediatypes

import org.springframework.hateoas.RepresentationModel

class DataRepresentationModel<T>(val data: T) : RepresentationModel<DataRepresentationModel<T>>() {

    companion object {
        fun <T> by(content: T): DataRepresentationModel<T> {
            return DataRepresentationModel(content)
        }
    }
}
