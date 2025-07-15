package com.qaid.pyxmasterdata.service;

import com.qaid.pyxmasterdata.models.dto.AgentInfoRequest;
import com.qaid.pyxmasterdata.models.dto.AgentInfoResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardDetailResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AgentInformationService {
    Page<AgentInfoResponse> getAll(Pageable pageable);
    Optional<AgentInfoResponse> getById(String id);
    Optional<AgentCardDetailResponse> getAgentDetail(String id);
    List<AgentCardResponse> getAllAgentCards();
    AgentInfoResponse create(AgentInfoRequest request);
    Optional<AgentInfoResponse> update(String id, AgentInfoRequest request);
    boolean delete(String id);
}
