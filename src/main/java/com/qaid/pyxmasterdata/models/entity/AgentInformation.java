package com.qaid.pyxmasterdata.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "agent_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentInformation {

    @Id
    private String id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;
    private String industry;

    private String icon; // Store icon class name like "ShoppingCart", or map via frontend

    private String price;

    private double rating;

    private int reviews;

    @Builder.Default
    private boolean isNew = false;

    @Builder.Default
    private boolean isPremium = false;

    @Builder.Default
    private boolean isActive = false;

    private String setupTime;

    private int activeUsers;

    private String security;

    @Column(columnDefinition = "TEXT")
    private String sampleOutput;

    @ElementCollection
    @CollectionTable(name = "agent_information_tags", joinColumns = @JoinColumn(name = "agent_information_id"))
    private List<Tag> tags;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "agent_information_features", joinColumns = @JoinColumn(name = "agent_information_id"))
    private Set<Feature> features;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "agent_information_integrations", joinColumns = @JoinColumn(name = "agent_information_id"))
    private Set<Integration> integrations;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
