package com.qaid.pyxmasterdata.controller;

import com.qaid.pyxmasterdata.models.dto.AgentInfoRequest;
import com.qaid.pyxmasterdata.models.dto.AgentInfoResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardDetailResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardResponse;
import com.qaid.pyxmasterdata.service.AgentInformationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AgentInformationController.class)
public class AgentInformationControllerTest {
    @MockBean
    private AgentInformationService service;

    @InjectMocks
    private AgentInformationController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
    }

    @Test
    void testGetAll() throws Exception {
        Page<AgentInfoResponse> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        when(service.getAll(any())).thenReturn(page);
        mockMvc.perform(get("/api/agents"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        AgentInfoResponse response = new AgentInfoResponse();
        when(service.getById(eq("1"))).thenReturn(Optional.of(response));
        mockMvc.perform(get("/api/agents/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAgentDetail() throws Exception {
        AgentCardDetailResponse response = new AgentCardDetailResponse();
        when(service.getAgentDetail(eq("1"))).thenReturn(Optional.of(response));
        mockMvc.perform(get("/api/agents/1/detail"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllAgentCards() throws Exception {
        when(service.getAllAgentCards()).thenReturn(List.of());
        mockMvc.perform(get("/api/agents/cards"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        AgentInfoRequest request = new AgentInfoRequest();
        AgentInfoResponse response = new AgentInfoResponse();
        when(service.create(any())).thenReturn(response);
        mockMvc.perform(post("/api/agents")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        AgentInfoRequest request = new AgentInfoRequest();
        AgentInfoResponse response = new AgentInfoResponse();
        when(service.update(eq("1"), any())).thenReturn(Optional.of(response));
        mockMvc.perform(patch("/api/agents/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        when(service.delete(eq("1"))).thenReturn(true);
        mockMvc.perform(delete("/api/agents/1"))
                .andExpect(status().isNoContent());
    }
} 