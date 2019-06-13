package com.hackathon.triage.apiCaller;

import com.hackathon.triage.domain.Issue;
import com.hackathon.triage.domain.JiraAccount;
import com.hackathon.triage.config.JiraConfig;
import com.hackathon.triage.parser.IssueParser;
import com.hackathon.triage.parser.MaxRecordsParser;
import com.hackathon.triage.scheduler.AbstractBaseScheduler;
import com.hackathon.triage.service.ComponentService;
import com.hackathon.triage.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Component
public class IssueApiCaller extends AbstractBaseScheduler {

    @Autowired
    private IssueService issueService;

    @Autowired
    private ComponentService componentService;

    @Override
    public void execute() {
        RestTemplate restTemplate = new RestTemplate();
        JiraAccount jiraAccount = JiraConfig.getJiraAccountObject();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(jiraAccount.getUserName(), jiraAccount.getPassword());
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", headers);

        System.out.println("Inside Issue api caller ::: ");
        try {
            ResponseEntity<String> result = restTemplate.exchange("https://jira.navis.com/rest/api/2/search?jql=project=10010&maxResults=0",
                    HttpMethod.GET,
                    httpEntity,
                    String.class);

            int totalNoOfRecords = MaxRecordsParser.parseJson(result.getBody());
            int i;
            System.out.println(totalNoOfRecords + " total number of records");

            int limit = totalNoOfRecords / 1000;
            int rem = totalNoOfRecords % 1000;
            IntStream.iterate(0, w -> w + 1000).limit(limit).parallel().forEach(t -> issueService.save(executeIssueApiInBatches(restTemplate, httpEntity, t)));
            System.out.println("Batch Hit to Jira API done :::::::::::");
            issueService.save(executeIssueApiInBatches(restTemplate, httpEntity, totalNoOfRecords - rem + 1));
//            List<Issue> bigList = new ArrayList<>();
            /*for (i = 0; i < totalNoOfRecords; i = i + 1000) {
                List<Issue> issueList = executeIssueApiInBatches(restTemplate, httpEntity, i);
                issueService.save(issueList);
                System.out.println("########## Issue List returned " + issueList.size());
//                bigList.addAll(issueList);
//                System.out.println("BIG LIST SIZE :::: " + bigList.size());
//                issueService.save(issueList);
            }
//            List<Issue> issueList = executeIssueApiInBatches(restTemplate, httpEntity, i);
            issueService.save(executeIssueApiInBatches(restTemplate, httpEntity, i));*/
//            bigList.addAll(issueList);

//            System.out.println("FINAL BIG LIST SIZE :::::: " + bigList.size());
//            issueService.save(bigList);

            Map<Integer, List<Issue>> componentIssueMapList = IssueParser.getComponentIssueMapList();

            List<com.hackathon.triage.domain.Component> componentList = componentService.findAll();

            for (com.hackathon.triage.domain.Component c : componentList) {
                if (componentIssueMapList.containsKey(c.getComponentId())) {
                    c.getIssues().addAll(componentIssueMapList.get(c.getComponentId()));
                }
            }
            componentService.save(componentList);

        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    public List<Issue> executeIssueApiInBatches(RestTemplate restTemplate, HttpEntity<String> httpEntity, int n) {

        System.out.println("Hitting Api for Batch ::: :" + n);
        String url = "https://jira.navis.com/rest/api/2/search?jql=project=10010&fields=categoryKey,summary,statusDescription,created,components,status,watches,issuetype,assignee&maxResults=1000&startAt=" + n;
        ResponseEntity<String> result = restTemplate.exchange(url,
                HttpMethod.GET,
                httpEntity,
                String.class);

        return IssueParser.parseJson(result.getBody());
    }
}
