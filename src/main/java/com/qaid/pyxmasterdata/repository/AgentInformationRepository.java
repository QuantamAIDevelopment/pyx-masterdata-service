package com.qaid.pyxmasterdata.repository;

import com.qaid.pyxmasterdata.models.entity.AgentInformation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgentInformationRepository extends JpaRepository<AgentInformation, String> {
    @Override
    @EntityGraph(attributePaths = {"tags", "features", "integrations"})
    List<AgentInformation> findAll();

    @EntityGraph(attributePaths = {"tags", "features", "integrations"})
    Optional<AgentInformation> findById(String id);
}
