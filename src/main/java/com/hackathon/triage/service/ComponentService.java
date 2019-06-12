package com.hackathon.triage.service;

import com.hackathon.triage.Domain.Component;
import com.hackathon.triage.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    public void save(Component component) {
        componentRepository.save(component);
    }

    public void save(List<Component> components) {
        componentRepository.saveAll(components);
    }

    public List<Component> findAll() {
        return componentRepository.findAll();
    }
}
