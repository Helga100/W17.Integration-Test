package com.example.hw16nosqldb.integration_test;

import com.example.hw16nosqldb.integration_test.config.PersonPopulatorConfiguration;
import com.example.hw16nosqldb.person_dto.NewPersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@Import(PersonPopulatorConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    void cleanUpDatabase() {
        mongoTemplate.getDb().drop();
    }

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));

    }

    @Test
    void createObjectTest() throws Exception {
        NewPersonDto newPersonDto = NewPersonDto.builder()
                .firstName("Elena")
                .lastName("Petrova")
                .age(67)
                .email("petrova@gmail.com")
                .married(true)
                .build();
        final ResultActions resultActions = mockMvc.perform(post("/api/persons")
                .content(asJsonString(newPersonDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Elena"))
                .andExpect(jsonPath("$.lastName").value("Petrova"))
                .andExpect(jsonPath("$.age").value(67))
                .andExpect(jsonPath("$.email").value("petrova@gmail.com"))
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
                        .param("firstName", "Elena")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Elena"))
                .andExpect(jsonPath("$[0].lastName").value("Petrova"))
                .andExpect(jsonPath("$[0].age").value(67))
                .andExpect(jsonPath("$[0].email").value("petrova@gmail.com"))
                .andExpect(jsonPath("$[0].married").value(true));
    }


    @Test
    void getObjectByLastNameTest() throws Exception {
        mockMvc.perform(get("/api/persons/lastName")
                        .param("lastName", "Petrova")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Elena"))
                .andExpect(jsonPath("$[0].lastName").value("Petrova"))
                .andExpect(jsonPath("$[0].age").value(67))
                .andExpect(jsonPath("$[0].email").value("petrova@gmail.com"))
                .andExpect(jsonPath("$[0].married").value(true));
    }


    @Test
    void getObjectByEmailTest() throws Exception {
        mockMvc.perform(get("/api/persons/email")
                        .param("email", "petrova@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Elena"))
                .andExpect(jsonPath("$.lastName").value("Petrova"))
                .andExpect(jsonPath("$.age").value(67))
                .andExpect(jsonPath("$.email").value("petrova@gmail.com"))
                .andExpect(jsonPath("$.married").value(true));
    }

    @Test
    void createTestBadRequestForGettingEmail() throws Exception {
        mockMvc.perform(get("/api/persons/email"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getObjectByAgeTest() throws Exception {
        mockMvc.perform(get("/api/persons/age")
                        .param("age", "65")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Elena"))
                .andExpect(jsonPath("$[0].lastName").value("Petrova"))
                .andExpect(jsonPath("$[0].age").value(67))
                .andExpect(jsonPath("$[0].email").value("petrova@gmail.com"))
                .andExpect(jsonPath("$[0].married").value(true));
    }

    @Test
    void getObjectByMarriageStatus() throws Exception {
        mockMvc.perform(get("/api/persons/marriage")
                        .param("married", "true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Elena"))
                .andExpect(jsonPath("$[0].lastName").value("Petrova"))
                .andExpect(jsonPath("$[0].age").value(67))
                .andExpect(jsonPath("$[0].email").value("petrova@gmail.com"))
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

