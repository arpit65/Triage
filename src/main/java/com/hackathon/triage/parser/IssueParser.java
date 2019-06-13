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

//    private static Map<Integer, List<Issue>> issueMapList = new HashMap<>();
    private static Map<Integer, List<Issue>> issueMapList = new HashMap<>();

    public static List<Issue> parseJson(String inJson) {
        List<Issue> issueList = new ArrayList<>();
        Map<String, Object> jsonObject = JsonUtil.fromJson(inJson, new TypeReference<Map<String, Object>>() {
        });
        List<Map<String, Object>> issues = (List<Map<String, Object>>) jsonObject.get("issues");
        for(Map<String, Object> issue : issues) {
            int issueId = Integer.parseInt(String.valueOf(issue.get("id")));
            String argoNumber = (String) issue.get("key");
            String description = (String) issue.get("description");
            Map<String, Object> fields = (Map<String, Object>) issue.get("fields");
            String summary;
            if (fields.isEmpty()) {
                summary = "";
            } else {
                summary = (String) fields.get("summary");
            }
            Map<String, Object> assignee = (Map<String, Object>) issue.get("assignee");
            String userName;
            String email;
            if (assignee == null) {
                userName = null;
                email = null;
            } else {
                userName = (String) assignee.get("name");
                email = (String) assignee.get("email");
            }
            User user = new User(userName, email, Collections.emptyList());

            Map<String, Object> status = (Map<String, Object>) issue.get("status");
            String statusName;
            int statusId;
            String statusDescription;
            if (status == null) {
                statusName = null;
                statusId = 0;
                statusDescription = null;
            } else {
                statusName = (String) status.get("name");
                statusId = Integer.parseInt(String.valueOf(status.get("id")));
                statusDescription = (String) status.get("description");
            }
            String categoryKey;
            String categoryName;
            int categoryId;
            if (status != null) {
                Map<String, Object> category = (Map<String, Object>) status.get("statusCategory");
                if (category == null) {
                    categoryId = 0;
                    categoryKey = null;
                    categoryName = null;
                } else {
                    categoryKey = (String) category.get("key");
                    categoryName = (String) category.get("name");
                    categoryId = Integer.parseInt(String.valueOf(category.get("id")));
                }
            } else {
                categoryKey = null;
                categoryId = 0;
                categoryName = null;
            }

            StatusCategory statusCategory = new StatusCategory(categoryId, categoryKey, categoryName);
            Status newStatus = new Status(statusId, statusName, statusDescription, statusCategory);

            Map<String, Object> watches = (Map<String, Object>) issue.get("watches");
            int watchCount;
            if (watches == null) {
                watchCount = 0;
            } else {
                watchCount = (int) watches.get("watchCount");
            }
            Map<String, Object> issueType = (Map<String, Object>) issue.get("issuetype");
            String issueTypeName;
            IssueType issueTypeEnum;
            if (issueType == null) {
                issueTypeName = null;
                issueTypeEnum = null;
            } else {
                issueTypeName = (String) issueType.get("name");
                issueTypeEnum = IssueType.valueOf(issueTypeName);
            }
            Map<String, Object> priority = (Map<String, Object>) issue.get("priority");
            int priorityId;
            if (priority == null) {
                priorityId = 0;
            } else {
                priorityId = Integer.parseInt(String.valueOf(priority.get("id")));
            }

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

//            System.out.println("Issue Object  ::: -> " + issueObject);
//            issueList.add(issueObject);
            List<Component> componentList = new ArrayList<>();
            List<Map<String, Object>> components = (List<Map<String, Object>>) fields.get("components");
            Component componentObject = new Component();
            if (components.size() == 0) {
                componentObject.setComponentId(0);
                componentObject.setComponentName(null);
                componentObject.setIssues(Collections.emptyList());
            } else {

                for (Map<String, Object> component : components) {
                    int id = Integer.parseInt(String.valueOf(component.get("id")));
                    String componentName = (String) component.get("name");

                    componentObject.setComponentId(id);
                    componentObject.setComponentName(componentName);
                    componentObject.setIssues(Collections.emptyList());


                componentList.add(componentObject);

                    if (issueMapList.containsKey(id)) {
                        issueMapList.get(id).add(issueObject);
                    } else {
                        issueMapList.put(id, new ArrayList<>());
                        issueMapList.get(id).add(issueObject);
                    }
//                    issueObject.getComponents().add(ne    w Component(componentObject));
                    System.out.println(issueObject.getComponents().size());
                }
                issueObject.setComponents(new ArrayList<>(componentList));
            }
            issueList.add(issueObject);
            System.out.println("No of Issues parsed ::: " + issueList.size());
        }
        System.out.println("No of Issues Taken ::: " + issues.size());
        return issueList;
    }

    public static Map<Integer, List<Issue>> getComponentIssueMapList() {
        return issueMapList;
    }
}
