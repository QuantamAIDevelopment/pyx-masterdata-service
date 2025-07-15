package com.qaid.pyxmasterdata.controller;

import com.qaid.pyxmasterdata.models.dto.AgentInfoRequest;
import com.qaid.pyxmasterdata.models.dto.AgentInfoResponse;
import com.qaid.pyxmasterdata.service.AgentInformationService;
import com.qaid.pyxmasterdata.service.serviceImpl.AgentInformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.qaid.pyxmasterdata.models.dto.AgentCardDetailResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardResponse;

@RestController
@RequestMapping("/api/agents")
public class AgentInformationController {
    @Autowired
    private AgentInformationService service;

    @GetMapping
    public Page<AgentInfoResponse> getAll(@PageableDefault(size = 10) Pageable pageable) {
        // Remove any sort parameter from pageable
        Pageable noSortPageable = Pageable.ofSize(pageable.getPageSize()).withPage(pageable.getPageNumber());
        return service.getAll(noSortPageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentInfoResponse> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<AgentCardDetailResponse> getAgentDetail(@PathVariable String id) {
        return service.getAgentDetail(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cards")
    public List<AgentCardResponse> getAllAgentCards() {
        return service.getAllAgentCards();
    }

    @PostMapping
    public ResponseEntity<AgentInfoResponse> create(@RequestBody AgentInfoRequest request) {
        AgentInfoResponse created = service.create(request);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AgentInfoResponse> update(@PathVariable String id, @RequestBody AgentInfoRequest request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
} 