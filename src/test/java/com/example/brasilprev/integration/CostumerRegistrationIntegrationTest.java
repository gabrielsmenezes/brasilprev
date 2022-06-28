package com.example.brasilprev.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"test"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CostumerRegistrationIntegrationTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String PATH = "/costumer";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSaveACostumerSuccessfullyAndReturnStatus201AndReturnSavedCostumer() throws Exception {
        HashMap<String, String> costumer = createCostumerMap("john Doe", "268.261.660-70", "Avenue Street n 192, USA");
        String costumerJson = this.mapper.writeValueAsString(costumer);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(costumerJson);
        MockHttpServletResponse response = this.mockMvc.perform(request).andReturn().getResponse();

        HashMap returnedCostumer = this.mapper.readValue(response.getContentAsString(), HashMap.class);

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assertions.assertNotNull(returnedCostumer.get("id"));
    }

    private HashMap<String, String> createCostumerMap(String name, String cpf, String address) {
        HashMap<String, String> costumer = new HashMap<>();
        costumer.put("name", name);
        costumer.put("cpf", cpf);
        costumer.put("address", address);
        return costumer;
    }

}
