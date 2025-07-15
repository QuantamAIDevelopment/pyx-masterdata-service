package com.qaid.pyxmasterdata.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "how_it_works_steps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HowItWorksStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_information_id", nullable = false)
    private AgentInformation agent;

    private String title;
    private String description;
    private int stepOrder;
} 