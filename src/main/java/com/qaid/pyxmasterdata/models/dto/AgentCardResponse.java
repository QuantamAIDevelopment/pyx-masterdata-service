package com.qaid.pyxmasterdata.models.dto;

import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
public class AgentCardResponse {
    private String id;
    private String name;
    private String description;
    private String category;
    private String industry;
    private String icon;
    private String price;
    private double rating;
    private int reviews;
    private boolean isNew;
    private List<String> tags;
    private Set<String> features;
    private String sampleOutput;
    private Set<String> integrations;
    private boolean isActive;
    private String setupTime;
    private int activeUsers;
    private String security;
    private List<HowItWorksStepResponse> howitWorks;
} 