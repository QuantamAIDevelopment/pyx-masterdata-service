package com.qaid.pyxmasterdata.models.dto;

import java.util.List;
import java.util.Set;

import com.qaid.pyxmasterdata.models.entity.Feature;
import com.qaid.pyxmasterdata.models.entity.Integration;
import com.qaid.pyxmasterdata.models.entity.Tag;
import lombok.Data;

@Data
public class AgentInfoRequest {
    private String id;
    private String name;
    private String description;
    private String category;
    private String industry;
    private String icon;
    private String price;
    private Double rating;
    private Integer reviews;
    private Boolean isNew;
    private Boolean isPremium;
    private Boolean isActive;
    private String setupTime;
    private Integer activeUsers;
    private String security;
    private String sampleOutput;
    private List<Tag> tags;
    private Set<Feature> features;
    private Set<Integration> integrations;
}
