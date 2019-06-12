package com.hackathon.triage.repository;

import com.hackathon.triage.domain.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Repository
public interface ComponentRepository extends JpaRepository<Component, Integer> {
}
