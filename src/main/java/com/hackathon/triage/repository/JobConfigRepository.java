package com.hackathon.triage.repository;

import com.hackathon.triage.domain.JobConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Repository
public interface JobConfigRepository extends JpaRepository<JobConfig, Integer> {

    JobConfig findByName(String issueName);
}
