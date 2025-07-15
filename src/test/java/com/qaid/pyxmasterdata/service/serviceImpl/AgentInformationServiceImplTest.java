package com.qaid.pyxmasterdata.service.serviceImpl;

import com.qaid.pyxmasterdata.models.dto.AgentInfoRequest;
import com.qaid.pyxmasterdata.models.dto.AgentInfoResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardDetailResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardResponse;
import com.qaid.pyxmasterdata.models.entity.AgentInformation;
import com.qaid.pyxmasterdata.repository.AgentInformationRepository;
import com.qaid.pyxmasterdata.repository.HowItWorksStepRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class AgentInformationServiceImplTest {
    @Mock
    private AgentInformationRepository repository;
    @Mock
    private HowItWorksStepRepository howItWorksStepRepository;
    @InjectMocks
    private AgentInformationServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Page<AgentInformation> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        when(repository.findAll(any(PageRequest.class))).thenReturn(page);
        Page<AgentInfoResponse> result = service.getAll(PageRequest.of(0, 10));
        assertNotNull(result);
    }

    @Test
    void testGetById() {
        AgentInformation agent = new AgentInformation();
        when(repository.findById(eq("1"))).thenReturn(Optional.of(agent));
        Optional<AgentInfoResponse> result = service.getById("1");
        assertTrue(result.isPresent());
    }

    @Test
    void testGetAgentDetail() {
        AgentInformation agent = new AgentInformation();
        when(repository.findById(eq("1"))).thenReturn(Optional.of(agent));
        Optional<AgentCardDetailResponse> result = service.getAgentDetail("1");
        assertTrue(result.isPresent());
    }

    @Test
    void testGetAllAgentCards() {
        when(repository.findAll()).thenReturn(List.of());
        List<AgentCardResponse> result = service.getAllAgentCards();
        assertNotNull(result);
    }

    @Test
    void testCreate() {
        AgentInformation agent = new AgentInformation();
        when(repository.save(any())).thenReturn(agent);
        AgentInfoRequest request = new AgentInfoRequest();
        AgentInfoResponse result = service.create(request);
        assertNotNull(result);
    }

    @Test
    void testUpdate() {
        AgentInformation agent = new AgentInformation();
        when(repository.findById(eq("1"))).thenReturn(Optional.of(agent));
        when(repository.save(any())).thenReturn(agent);
        AgentInfoRequest request = new AgentInfoRequest();
        Optional<AgentInfoResponse> result = service.update("1", request);
        assertTrue(result.isPresent());
    }

    @Test
    void testDelete() {
        when(repository.existsById(eq("1"))).thenReturn(true);
        boolean result = service.delete("1");
        assertTrue(result);
    }
} 