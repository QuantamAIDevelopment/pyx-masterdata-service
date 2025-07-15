package com.qaid.pyxmasterdata.repository;

import com.qaid.pyxmasterdata.models.entity.HowItWorksStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HowItWorksStepRepository extends JpaRepository<HowItWorksStep, Long> {
    List<HowItWorksStep> findByAgentIdOrderByStepOrderAsc(String agentId);
} 