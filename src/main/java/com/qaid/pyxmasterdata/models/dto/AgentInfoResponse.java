package com.qaid.pyxmasterdata.models.dto;

import java.util.List;
import java.util.Set;

import com.qaid.pyxmasterdata.models.entity.Feature;
import com.qaid.pyxmasterdata.models.entity.Integration;
import com.qaid.pyxmasterdata.models.entity.Tag;
import lombok.Data;

@Data
public class AgentInfoResponse {
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
    private boolean isPremium;
    private boolean isActive;
    private String setupTime;
    private int activeUsers;
    private String security;
    private String sampleOutput;
    // Removed tags, features, integrations to avoid LazyInitializationException in paginated endpoint
    private String updatedBy;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
