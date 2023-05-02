package com.example.hw16nosqldb.integration_test;

import com.example.hw16nosqldb.person_dto.NewPersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)  // дозволяэ створювати бин mockMvc
@SpringBootTest() // запускає тестовий environment
@AutoConfigureMockMvc   // дозволяє використовувати Mvc і автоконфігурувати
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc; // для виклику ендпоінту з заданими параментрами і перевірки відпові


    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(15)));

    }

    @Test
    void createObjectTest() throws Exception {
        NewPersonDto newPersonDto = NewPersonDto.builder()
                .firstName("Helga")
                .lastName("Smirnova")
                .email("Smirnova@gmail.com")
                .age(69)
                .married(true)
                .build();
        final ResultActions resultActions = mockMvc.perform(post("/api/persons")
                .content(asJsonString(newPersonDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Helga"))
                .andExpect(jsonPath("$.lastName").value("Smirnova"))
                .andExpect(jsonPath("$.email").value("Smirnova@gmail.com"))
                .andExpect(jsonPath("$.age").value(69))
                .andExpect(jsonPath("$.married").value(true));
    }

    @Test
    void createTestBadRequestForCreation() throws Exception {
        mockMvc.perform(post("/api/persons"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getObjectByFirstNameTest() throws Exception {
        mockMvc.perform(get("/api/persons/firstName")
                        .param("firstName", "Heleg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Heleg"))
                .andExpect(jsonPath("$[0].lastName").value("Ivanov"))
                .andExpect(jsonPath("$[0].email").value("ivanov@gmail.com"))
                .andExpect(jsonPath("$[0].age").value(15))
                .andExpect(jsonPath("$[0].married").value(false))
                .andExpect(jsonPath("$").value(hasSize(1)));

    }


    @Test
    void getObjectByLastNameTest() throws Exception {
        mockMvc.perform(get("/api/persons/lastName")
                        .param("lastName", "Ivanov")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Heleg"))
                .andExpect(jsonPath("$[0].lastName").value("Ivanov"))
                .andExpect(jsonPath("$[0].email").value("ivanov@gmail.com"))
                .andExpect(jsonPath("$[0].age").value(15))
                .andExpect(jsonPath("$[0].married").value(false));
    }


    @Test
    void getObjectByEmailTest() throws Exception {
        mockMvc.perform(get("/api/persons/email")
                        .param("email", "ivanov@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Heleg"))
                .andExpect(jsonPath("$.lastName").value("Ivanov"))
                .andExpect(jsonPath("$.email").value("ivanov@gmail.com"))
                .andExpect(jsonPath("$.age").value(15))
                .andExpect(jsonPath("$.married").value(false));
    }

    @Test
    void createTestBadRequestForGettingEmail() throws Exception {
        mockMvc.perform(get("/api/persons/email"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getObjectByAgeTest() throws Exception {
        mockMvc.perform(get("/api/persons/age")
                        .param("age", "76")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Tom"))
                .andExpect(jsonPath("$[0].lastName").value("Salov"))
                .andExpect(jsonPath("$[0].email").value("salov@gmail.com"))
                .andExpect(jsonPath("$[0].age").value(77))
                .andExpect(jsonPath("$[0].married").value(false));
    }

    @Test
    void getObjectByMarriageStatus() throws Exception {
        mockMvc.perform(get("/api/persons/marriage")
                        .param("married", "true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Olga"))
                .andExpect(jsonPath("$[0].lastName").value("Steve"))
                .andExpect(jsonPath("$[0].email").value("Steve@gmail.com"))
                .andExpect(jsonPath("$[0].married").value(true));
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

