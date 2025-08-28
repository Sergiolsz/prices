package com.app.prices.infrastructure.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.app.prices.infrastructure.database.entity.PriceEntity;
import com.app.prices.infrastructure.database.repository.PriceRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    private static final String ENDPOINT = "/api/v1/prices";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository priceRepository;

    // --- Tests de endpoint ---
    @Test
    @DisplayName("Test 1: 2020-06-14 10:00")
    void test1() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    @DisplayName("Test 2: 2020-06-14 16:00")
    void test2() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.priceList").value(2));
    }

    @Test
    @DisplayName("Test 3: 2020-06-14 21:00")
    void test3() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    @DisplayName("Test 4: 2020-06-15 10:00")
    void test4() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("applicationDate", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.priceList").value(3));
    }

    @Test
    @DisplayName("Test 5: 2020-06-16 21:00")
    void test5() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.priceList").value(4));
    }

    // --- Test de comprobación de datos cargados ---
    @Test
    @DisplayName("Comprobación: H2 tiene los 4 registros cargados")
    void testDatabaseHasFourPrices() {
        List<PriceEntity> allPrices = priceRepository.findAll();
        assertThat(allPrices).hasSize(4);

        allPrices.forEach(price -> {
            System.out.println("Loaded price: " + price.getPriceList() + " - " + price.getPrice());
        });
    }
}