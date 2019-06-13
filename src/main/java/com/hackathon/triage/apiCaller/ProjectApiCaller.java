package com.hackathon.triage.apiCaller;

import com.hackathon.triage.domain.JiraAccount;
import com.hackathon.triage.domain.Project;
import com.hackathon.triage.config.JiraConfig;
import com.hackathon.triage.parser.ProjectParser;
import com.hackathon.triage.scheduler.AbstractBaseScheduler;
import com.hackathon.triage.service.ComponentService;
import com.hackathon.triage.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Component
public class ProjectApiCaller extends AbstractBaseScheduler {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ComponentService componentService;


    @Override
    public void execute() {
        RestTemplate restTemplate = new RestTemplate();
        JiraAccount jiraConfig = JiraConfig.getJiraAccountObject();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(jiraConfig.getUserName(), jiraConfig.getPassword());
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", headers);

        System.out.println("Inside Project API caller ::: ");

        try {
            ResponseEntity<String> result = restTemplate.exchange("https://jira.navis.com/rest/api/2/project/10010",
                    HttpMethod.GET,
                    httpEntity,
                    String.class);

            Project project = ProjectParser.parseJson(result.getBody());
//            System.out.println("Returned JSON ::::: " +project);
            projectService.save(project);
//            componentService.save(project.getComponents());
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
