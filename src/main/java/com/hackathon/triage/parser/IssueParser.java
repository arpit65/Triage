package com.hackathon.triage.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hackathon.triage.domain.Component;
import com.hackathon.triage.domain.Issue;
import com.hackathon.triage.domain.IssueType;
import com.hackathon.triage.domain.Status;
import com.hackathon.triage.domain.StatusCategory;
import com.hackathon.triage.domain.User;
import com.hackathon.triage.util.JsonUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
public class IssueParser {

    private static Map<Integer, List<Issue>> issueMapList = new HashMap<>();

    public static List<Issue> parseJson(String inJson) {
        List<Issue> issueList = new ArrayList<>();
        System.out.println("#### INSIDE ISSUE PARSER ####");
        Map<String, Object> jsonObject = JsonUtil.fromJson(inJson, new TypeReference<Map<String, Object>>() {
        });
        List<Map<String, Object>> issues = (List<Map<String, Object>>) jsonObject.get("issues");
        for(Map<String, Object> issue : issues) {
            int issueId = Integer.parseInt(String.valueOf(issue.get("developerId")));
            String argoNumber = (String) issue.get("key");
            String description = (String) issue.get("description");
            Map<String, Object> fields = (Map<String, Object>) issue.get("fields");
            String summary = (String) fields.get("summary");
            Map<String, Object> assignee = (Map<String, Object>) issue.get("assignee");
            String userName = (String) assignee.get("name");
            String email = (String) assignee.get("email");
            User user = new User(userName, email, Collections.emptyList());

            Map<String, Object> status = (Map<String, Object>) issue.get("status");
            String statusName = (String) status.get("name");
            int statusId = Integer.parseInt(String.valueOf(status.get("developerId")));
            String statusDescription = (String) status.get("description");
            Map<String, Object> category = (Map<String, Object>) status.get("statusCategory");
            String categoryKey = (String) category.get("key");
            String categoryName = (String) category.get("name");
            int categoryId = Integer.parseInt(String.valueOf(category.get("developerId")));
            StatusCategory statusCategory = new StatusCategory(categoryId, categoryKey, categoryName);
            Status newStatus = new Status(statusId, statusName, statusDescription, statusCategory);

            Map<String, Object> watches = (Map<String, Object>) issue.get("watches");
            int watchCount = (int) watches.get("watchCount");

            Map<String, Object> issueType = (Map<String, Object>) issue.get("issuetype");
            String issueTypeName = (String) issueType.get("name");
            IssueType issueTypeEnum = IssueType.valueOf(issueTypeName);
            Map<String, Object> priority = (Map<String, Object>) issue.get("priority");
            int priorityId = Integer.parseInt(String.valueOf(priority.get("developerId")));

            System.out.println("Inside Issue Parser .....  reached here.....");

            Issue issueObject = new Issue();
            issueObject.setIssueId(issueId);
            issueObject.setArgoNumber(argoNumber);
            issueObject.setSummary(summary);
            issueObject.setDescription(description);
            issueObject.setAssignee(user);
            issueObject.setPriority(priorityId);
            issueObject.setWatchCount(watchCount);
            issueObject.setType(issueTypeEnum);
            issueObject.setStatus(newStatus);
            issueObject.setComponents(Collections.emptyList());

            System.out.println("Issue Object  ::: -> " + issueObject);
//            issueList.add(issueObject);
//            List<Component> componentList = new ArrayList<>();
            List<Map<String, Object>> components = (List<Map<String, Object>>) fields.get("components");

            for(Map<String, Object> component : components) {
                int id = Integer.parseInt(String.valueOf(component.get("developerId")));
                String componentName = (String) component.get("name");

                Component componentObject = new Component();
                componentObject.setComponentId(id);
                componentObject.setComponentName(componentName);
                componentObject.getIssues().add(issueObject);

                System.out.println("Component Object ::: "+ componentObject);

//                componentList.add(componentObject);

                if(issueMapList.containsKey(id)) {
                    issueMapList.get(id).add(issueObject);
                } else {
                    issueMapList.put(id, new ArrayList<>());
                    issueMapList.get(id).add(issueObject);
                }
                issueObject.getComponents().add(componentObject);
                System.out.println("New Issue Object ::: " + issueObject);
            }
            issueList.add(issueObject);
            System.out.println("Issue Object List :::: " + issueObject);
        }
        return issueList;
    }

    public static Map<Integer, List<Issue>> getComponentIssueMapList() {
        return issueMapList;
    }
}
