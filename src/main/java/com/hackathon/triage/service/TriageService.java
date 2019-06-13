package com.hackathon.triage.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Service
public class TriageService {

    /**
     * ARGO number is int in our application without the Keyword 'ARGO-'
     */
    public List<String> returnBestPersonToTriageThisIssue(String argoNumber) {
        return Collections.emptyList();
    }
}
