package com.rewards.reward_api.controller;


import com.rewards.reward_api.dto.RewardResponse;
import com.rewards.reward_api.service.RewardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {RewardController}.
 *
 * This class tests the API endpoints exposed by the RewardController.
 * It uses MockMvc and Mockito to simulate web requests and mock service responses,
 * ensuring that the controller logic behaves correctly across various scenarios.
 *
 *
 * Key scenarios tested:
 *     Successful reward retrieval
 *     Invalid date range input
 *     Missing required parameters
 *
 *
 * All tests simulate asynchronous behavior to match the controller's async response model
 *
 */
@WebMvcTest(RewardController.class)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RewardService rewardService;

    /**
     * Test case to validate successful retrieval of customer rewards
     * with correct input parameters and a mocked service response.
     *
     * @throws Exception if an error occurs while performing the request
     */
    @Test
    void testGetCustomerRewardsSuccess() throws Exception {
        RewardResponse response = new RewardResponse(
                "1", "Dinesh", 100, 2,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 3, 31),
                Map.of("January", 50, "February", 50)
        );

        Mockito.when(rewardService.calculateRewards(Mockito.eq("1"), Mockito.any(), Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(response));

        var mvcResult =  mockMvc.perform(get("/api/rewards")
                        .param("customerId", "1")
                        .param("from", "2024-01-01")
                        .param("to", "2024-03-31")
                        .accept(MediaType.APPLICATION_JSON))
//                        .andDo(print())
                        .andExpect(request().asyncStarted())
                        .andReturn();

        // Assert: complete async and check response body
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId").value("1"))
                .andExpect(jsonPath("$.customerName").value("Dinesh"))
                .andExpect(jsonPath("$.totalPoints").value(100))
                .andExpect(jsonPath("$.transactionCount").value(2))
                .andExpect(jsonPath("$.monthlyBreakdown.January").value(50))
                .andExpect(jsonPath("$.monthlyBreakdown.February").value(50));
    }


    /**
     * Test case to verify that the API returns a 400 Bad Request
     * when the 'from' date is later than the 'to' date.
     *
     * @throws Exception if an error occurs while performing the request
     */
    @Test
    void testInvalidDateRange() throws Exception {
        mockMvc.perform(get("/api/rewards")
                        .param("customerId", "1")
                        .param("from", "2024-04-01")
                        .param("to", "2024-03-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test case to verify that the API returns a 400 Bad Request
     * when the required 'customerId' parameter is missing.
     *
     * @throws Exception if an error occurs while performing the request
     */
    @Test
    void testMissingCustomerId() throws Exception {
        mockMvc.perform(get("/api/rewards")
                        .param("from", "2024-01-01")
                        .param("to", "2024-03-31"))
                .andExpect(status().isBadRequest());
    }

}
