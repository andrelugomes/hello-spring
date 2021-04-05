package com.example.spring.rest.docs

import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters

import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest
class UserControllerTest: RestDocumentation() {

    @Test
    fun `Should get a user by ID`() {

        mockMvc.perform(get("/users/{id}", 123))
            .andExpect(status().isOk)
            .andExpect(jsonPath("id", `is`(123)))
            .andExpect(jsonPath("name", `is`("Full Name")))
            .andDo(print())
            .andDo(document("users/{class-name}/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
			        parameterWithName("id").description("The User's ID")
                ),
                responseFields(
                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("The User's ID"),
                    fieldWithPath("name").type(JsonFieldType.STRING).description("The User's full name")
                )
            ))

    }
}