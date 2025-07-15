package com.qaid.pyxmasterdata.models.dto;

import lombok.Data;

@Data
public class AgentReview {
    private String user;
    private String comment;
    private double rating;
    private String date;
} 