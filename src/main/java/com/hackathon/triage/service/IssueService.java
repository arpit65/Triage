package com.hackathon.triage.service;

import com.hackathon.triage.domain.Issue;
import com.hackathon.triage.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Service
public class IssueService implements IService {

    @Autowired
    private IssueRepository issueRepository;

    public void save(Issue issue) {
        issueRepository.save(issue);
    }

    public void save(List<Issue> issues) {
        System.out.println("##### SAVING ISSUE LIST::::");
        issueRepository.saveAll(issues);
    }
}
