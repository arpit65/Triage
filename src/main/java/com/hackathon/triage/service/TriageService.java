package com.hackathon.triage.service;

import com.hackathon.triage.domain.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Service
public class TriageService {


    @Autowired
    ServiceApiCaller apiCaller;

    /**
     * ARGO number is int in our application without the Keyword 'ARGO-'
     */
    public List<String> returnBestPersonToTriageThisIssue(String argoNumber) {
        Issue issue = apiCaller.getIssueWithArgoNumber(argoNumber);
        //todo himanshu's api
        return Collections.emptyList();
    }

    public Issue getIssueByNumber(String argoNumber) {
        return apiCaller.getIssueWithArgoNumber(argoNumber);
    }
}
