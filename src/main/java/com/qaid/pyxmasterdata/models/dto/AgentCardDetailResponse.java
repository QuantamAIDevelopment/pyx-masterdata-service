package com.qaid.pyxmasterdata.models.dto;

import com.qaid.pyxmasterdata.models.entity.Tag;
import lombok.Data;
import java.util.List;
import java.util.Set;
import com.qaid.pyxmasterdata.models.entity.Feature;
import com.qaid.pyxmasterdata.models.entity.Integration;

@Data
public class AgentCardDetailResponse {
    private String id;
    private String name;
    private String icon;
    private String price;
    private double rating;
    private int reviews;
    private String description;
    private List<Tag> tags;
    private String category;
    private String industry;
    private boolean isNew;
    private boolean isPremium;
    private boolean isActive;
    private String sampleOutput;
    private List<HowItWorksStep> howItWorks;
    private Set<Feature> features;
    private Set<Integration> integrations;
    private List<AgentReview> reviewsList;
} 