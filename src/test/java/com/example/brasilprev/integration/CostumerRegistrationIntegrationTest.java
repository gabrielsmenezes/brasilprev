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
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CostumerRegistrationIntegrationTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String PATH = "/customer";

    @Autowired
    private MockMvc mockMvc;

    private HashMap<String, String> createCustomerMap(String name, String cpf, String address) {
        HashMap<String, String> customer = new HashMap<>();
        customer.put("name", name);
        customer.put("cpf", cpf);
        customer.put("address", address);
        customer.put("password", "123456789");
        return customer;
    }

    private void executeTestWithoutRequiredFields(String name, String cpf, String address, String field) throws Exception {
        HashMap<String, String> costumer = createCustomerMap(name, cpf, address);
        String costumerJson = this.mapper.writeValueAsString(costumer);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(costumerJson);
        MockHttpServletResponse response = this.mockMvc.perform(request).andReturn().getResponse();

        List<String> message = this.mapper.readValue(response.getContentAsString(), List.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertTrue(message.stream().anyMatch(msg -> msg.contains(field)));
    }

    @Test
    public void shouldSaveACustomerSuccessfullyAndReturnStatus201AndReturnSavedCustomer() throws Exception {
        HashMap<String, String> costumer = createCustomerMap("John Doe", "268.261.660-70", "Avenue Street n 192, USA");
        String costumerJson = this.mapper.writeValueAsString(costumer);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(costumerJson);
        MockHttpServletResponse response = this.mockMvc.perform(request).andReturn().getResponse();

        HashMap returnedCostumer = this.mapper.readValue(response.getContentAsString(), HashMap.class);

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assertions.assertNotNull(returnedCostumer.get("id"));
    }

    @Test
    public void shouldNotSaveCustomerWithoutNameAndReturn400AndRequiredMessage() throws Exception {
        executeTestWithoutRequiredFields("", "268.261.660-70", "Avenue Street n 192, USA", "name");
    }

    @Test
    public void shouldNotSaveCustomerWithoutCpfAndReturn400AndRequiredMessage() throws Exception {
        executeTestWithoutRequiredFields("John Doe", "", "Avenue Street n 192, USA", "cpf");
    }

    @Test
    public void shouldNotSaveCustomerWithoutAddressAndReturn400AndRequiredMessage() throws Exception {
        executeTestWithoutRequiredFields("John Doe", "268.261.660-70", "", "address");
    }
}
