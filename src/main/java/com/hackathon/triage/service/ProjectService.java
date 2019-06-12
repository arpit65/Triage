package com.hackathon.triage.service;

import com.hackathon.triage.Domain.Project;
import com.hackathon.triage.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Service
public class ProjectService implements IService{

    @Autowired
    private ProjectRepository projectRepository;

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void save(List<Project> projects) {
        projectRepository.saveAll(projects);
    }
}
