package com.morozov.warrantywebsystem.web.claim;

import com.morozov.warrantywebsystem.AbstractControllerTest;
import com.morozov.warrantywebsystem.repository.ClaimRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.morozov.warrantywebsystem.web.claim.ClaimTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClaimRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ClaimRestController.REST_URL + '/';

    @Autowired
    private ClaimRepository claimRepository;



    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLAIM1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CLAIM_MATCHER.contentJson(claim1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLAIM1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotOwn() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLAIM2_ID))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADVISER_MAIL)
    void getNotOwnForAdviser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLAIM1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CLAIM_MATCHER.contentJson(claim1));
    }

    @Test
    void delete() {
    }
}