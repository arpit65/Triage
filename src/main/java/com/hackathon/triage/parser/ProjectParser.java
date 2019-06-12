package com.hackathon.triage.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hackathon.triage.Domain.Component;
import com.hackathon.triage.Domain.Project;
import com.hackathon.triage.util.JsonUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
public class ProjectParser {

    public static Project parseJson(String inJson) {
        Map<String, Object> jsonObject = JsonUtil.fromJson(inJson, new TypeReference<Map<String, Object>>() {
        });
        int id = Integer.parseInt(String.valueOf(jsonObject.get("id")));
        String description = (String) jsonObject.get("description");

        String name = (String) jsonObject.get("name");

        List<Map<String, Object>> components = (List<Map<String, Object>>) jsonObject.get("components");
        List<Component> componentList = new ArrayList<>();
        for (Map<String, Object> component : components) {
            int componentId = Integer.parseInt(String.valueOf(component.get("id")));
            String componentName = (String) component.get("name");
            Component tempComponent = new Component(componentId, componentName, Collections.emptyList());
            componentList.add(tempComponent);
        }

        return new Project(id, name, description, componentList);
    }
}
