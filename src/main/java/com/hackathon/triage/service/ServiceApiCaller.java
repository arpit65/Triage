package com.hackathon.triage.service;

import com.hackathon.triage.domain.Issue;
import com.hackathon.triage.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ServiceApiCaller {

    @Autowired
    IssueRepository repository;

    RestTemplate restTemplate = new RestTemplate();
    public Issue getIssueWithArgoNumber(String argoNumber) {
        Optional<Issue> issue = checkIssueInDb(argoNumber);
        if(issue.isPresent()) {
            return issue.get();
        }
        String url = "https://jira.navis.com/rest/api/2/issue/"+ argoNumber;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("${jira.authentication.username}", "${jira.authentication.password}");
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            System.out.println(responseEntity.getBody());
            //todo parse this and return the right object - returning null for now.
            return null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Optional<Issue> checkIssueInDb(String argoNumber) {
        //todo remove 5 and add indexOf function
        Optional<Issue> issue = repository.findById(Integer.parseInt(argoNumber.substring(argoNumber.indexOf(5))));
        return issue;
    }

}
