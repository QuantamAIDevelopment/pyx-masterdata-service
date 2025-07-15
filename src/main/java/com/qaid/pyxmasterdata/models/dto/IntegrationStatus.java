package com.qaid.pyxmasterdata.models.dto;

import lombok.Data;

@Data
public class IntegrationStatus {
    private String name;
    private String status; // e.g., "Available", "Coming Soon"
} 