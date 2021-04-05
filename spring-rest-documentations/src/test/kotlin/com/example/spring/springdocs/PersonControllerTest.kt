package com.example.spring.springdocs

import com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(PersonController::class)
class PersonControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `Should get a person by ID`() {
        mockMvc.get("/person/{id}", 123).andExpect {
            status {
                isOk()
            }
        }.andExpect {
            MockMvcResultMatchers.jsonPath("name", CoreMatchers.`is`("Person Name"))
        }.andDo {
            MockMvcResultHandlers.print()
        }.andExpect {
            OpenApiValidationMatchers.openApi().isValid("http://localhost:8080/api-docs")
        }
    }
}