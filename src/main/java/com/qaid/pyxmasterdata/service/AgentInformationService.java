package com.qaid.pyxmasterdata.service;

import com.qaid.pyxmasterdata.models.dto.AgentInfoRequest;
import com.qaid.pyxmasterdata.models.dto.AgentInfoResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardDetailResponse;
import com.qaid.pyxmasterdata.models.dto.AgentCardResponse;
import com.qaid.pyxmasterdata.models.dto.HowItWorksStepResponse;
import com.qaid.pyxmasterdata.models.entity.AgentInformation;
import com.qaid.pyxmasterdata.models.entity.Tag;
import com.qaid.pyxmasterdata.models.entity.Feature;
import com.qaid.pyxmasterdata.models.entity.Integration;
import com.qaid.pyxmasterdata.models.entity.HowItWorksStep;
import com.qaid.pyxmasterdata.repository.AgentInformationRepository;
import com.qaid.pyxmasterdata.repository.HowItWorksStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgentInformationService {
    @Autowired
    private AgentInformationRepository repository;
    @Autowired
    private HowItWorksStepRepository howItWorksStepRepository;

    public Page<AgentInfoResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public Optional<AgentInfoResponse> getById(String id) {
        return repository.findById(id).map(this::toResponse);
    }

    public Optional<AgentCardDetailResponse> getAgentDetail(String id) {
        return repository.findById(id).map(agent -> {
            AgentCardDetailResponse dto = new AgentCardDetailResponse();
            dto.setId(agent.getId());
            dto.setName(agent.getName());
            dto.setIcon(agent.getIcon());
            dto.setPrice(agent.getPrice());
            dto.setRating(agent.getRating());
            dto.setReviews(agent.getReviews());
            dto.setDescription(agent.getDescription());
            dto.setTags(agent.getTags());
            dto.setCategory(agent.getCategory());
            dto.setIndustry(agent.getIndustry());
            dto.setNew(agent.isNew());
            dto.setPremium(agent.isPremium());
            dto.setActive(agent.isActive());
            dto.setSampleOutput(agent.getSampleOutput());
            dto.setFeatures(agent.getFeatures());
            dto.setIntegrations(agent.getIntegrations());
            // Fetch howItWorks steps
            List<HowItWorksStep> steps = howItWorksStepRepository.findByAgentIdOrderByStepOrderAsc(agent.getId());
            List<com.qaid.pyxmasterdata.models.dto.HowItWorksStep> stepDtos = steps.stream().map(step -> {
                com.qaid.pyxmasterdata.models.dto.HowItWorksStep dtoStep = new com.qaid.pyxmasterdata.models.dto.HowItWorksStep();
                dtoStep.setStepNumber(step.getStepOrder());
                dtoStep.setTitle(step.getTitle());
                dtoStep.setDescription(step.getDescription());
                return dtoStep;
            }).toList();
            dto.setHowItWorks(stepDtos);
            // Set other fields as needed (keyFeatures, reviewsList, etc.)
            // These can be filled in when you have the data sources
            return dto;
        });
    }

    public List<AgentCardResponse> getAllAgentCards() {
        List<AgentInformation> agents = repository.findAll();
        return agents.stream().map(agent -> {
            AgentCardResponse dto = new AgentCardResponse();
            dto.setId(agent.getId());
            dto.setName(agent.getName());
            dto.setDescription(agent.getDescription());
            dto.setCategory(agent.getCategory());
            dto.setIndustry(agent.getIndustry());
            dto.setIcon(agent.getIcon());
            dto.setPrice(agent.getPrice());
            dto.setRating(agent.getRating());
            dto.setReviews(agent.getReviews());
            dto.setNew(agent.isNew());
            dto.setTags(agent.getTags() != null ? agent.getTags().stream().map(Tag::getValue).toList() : null);
            dto.setFeatures(agent.getFeatures() != null ? agent.getFeatures().stream().map(Feature::getValue).collect(java.util.stream.Collectors.toSet()) : null);
            dto.setIntegrations(agent.getIntegrations() != null ? agent.getIntegrations().stream().map(Integration::getValue).collect(java.util.stream.Collectors.toSet()) : null);
            dto.setSampleOutput(agent.getSampleOutput());
            // Fetch howitWorks steps
            List<HowItWorksStep> steps = howItWorksStepRepository.findByAgentIdOrderByStepOrderAsc(agent.getId());
            List<HowItWorksStepResponse> stepDtos = steps.stream().map(step -> {
                HowItWorksStepResponse s = new HowItWorksStepResponse();
                s.setId(step.getStepOrder());
                s.setTitle(step.getTitle());
                s.setDescription(step.getDescription());
                return s;
            }).toList();
            dto.setHowitWorks(stepDtos);
            return dto;
        }).toList();
    }

    @Transactional
    public AgentInfoResponse create(AgentInfoRequest request) {
        AgentInformation entity = fromRequest(request);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        AgentInformation saved = repository.save(entity);
        return toResponse(saved);
    }

    @Transactional
    public Optional<AgentInfoResponse> update(String id, AgentInfoRequest request) {
        return repository.findById(id).map(existing -> {
            updateEntity(existing, request);
            existing.setUpdatedAt(LocalDateTime.now());
            AgentInformation saved = repository.save(existing);
            return toResponse(saved);
        });
    }

    @Transactional
    public boolean delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private AgentInformation fromRequest(AgentInfoRequest req) {
        return AgentInformation.builder()
                .id(req.getId())
                .name(req.getName())
                .description(req.getDescription())
                .category(req.getCategory())
                .industry(req.getIndustry())
                .icon(req.getIcon())
                .price(req.getPrice())
                .rating(req.getRating() != null ? req.getRating() : 0)
                .reviews(req.getReviews() != null ? req.getReviews() : 0)
                .isNew(req.getIsNew() != null ? req.getIsNew() : false)
                .isPremium(req.getIsPremium() != null ? req.getIsPremium() : false)
                .isActive(req.getIsActive() != null ? req.getIsActive() : false)
                .setupTime(req.getSetupTime())
                .activeUsers(req.getActiveUsers() != null ? req.getActiveUsers() : 0)
                .security(req.getSecurity())
                .sampleOutput(req.getSampleOutput())
                .tags(toTagList(req.getTags()))
                .features(req.getFeatures())
                .integrations(req.getIntegrations())
                .build();
    }

    private void updateEntity(AgentInformation entity, AgentInfoRequest req) {
        if (req.getName() != null) entity.setName(req.getName());
        if (req.getDescription() != null) entity.setDescription(req.getDescription());
        if (req.getCategory() != null) entity.setCategory(req.getCategory());
        if (req.getIndustry() != null) entity.setIndustry(req.getIndustry());
        if (req.getIcon() != null) entity.setIcon(req.getIcon());
        if (req.getPrice() != null) entity.setPrice(req.getPrice());
        if (req.getRating() != null) entity.setRating(req.getRating());
        if (req.getReviews() != null) entity.setReviews(req.getReviews());
        if (req.getIsNew() != null) entity.setNew(req.getIsNew());
        if (req.getIsPremium() != null) entity.setPremium(req.getIsPremium());
        if (req.getIsActive() != null) entity.setActive(req.getIsActive());
        if (req.getSetupTime() != null) entity.setSetupTime(req.getSetupTime());
        if (req.getActiveUsers() != null) entity.setActiveUsers(req.getActiveUsers());
        if (req.getSecurity() != null) entity.setSecurity(req.getSecurity());
        if (req.getSampleOutput() != null) entity.setSampleOutput(req.getSampleOutput());
        if (req.getTags() != null) entity.setTags(toTagList(req.getTags()));
        if (req.getFeatures() != null) entity.setFeatures(req.getFeatures());
        if (req.getIntegrations() != null) entity.setIntegrations(req.getIntegrations());
    }

    private AgentInfoResponse toResponse(AgentInformation entity) {
        AgentInfoResponse resp = new AgentInfoResponse();
        resp.setId(entity.getId());
        resp.setName(entity.getName());
        resp.setDescription(entity.getDescription());
        resp.setCategory(entity.getCategory());
        resp.setIndustry(entity.getIndustry());
        resp.setIcon(entity.getIcon());
        resp.setPrice(entity.getPrice());
        resp.setRating(entity.getRating());
        resp.setReviews(entity.getReviews());
        resp.setNew(entity.isNew());
        resp.setPremium(entity.isPremium());
        resp.setActive(entity.isActive());
        resp.setSetupTime(entity.getSetupTime());
        resp.setActiveUsers(entity.getActiveUsers());
        resp.setSecurity(entity.getSecurity());
        resp.setSampleOutput(entity.getSampleOutput());
        // Do not set tags, features, integrations for paginated endpoint
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        resp.setUpdatedBy(entity.getUpdatedBy());
        return resp;
    }

    private List<Tag> toTagList(List<Tag> tags) {
        return tags;
    }
    private List<Feature> toFeatureList(List<Feature> features) {
        return features;
    }
    private List<Integration> toIntegrationList(List<Integration> integrations) {
        return integrations;
    }
} 