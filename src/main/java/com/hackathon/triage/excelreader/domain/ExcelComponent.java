package com.hackathon.triage.excelreader.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ExcelComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int componentId;
    String Component;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "excel_component_id", referencedColumnName = "componentId")
    List<ExcelTopic> topics = new ArrayList<>();

    public ExcelComponent(String component, String topic) {
        Component = component;
        topics.add(new ExcelTopic(topic));
    }

    public void addTopics(String topic) {
        topics.add(new ExcelTopic(topic));
    }

    public void updateTopicPoints(String topicName, String developerName, int points) {
        ExcelTopic excelTopic = null;
        for (ExcelTopic et : topics) {
            if (et.getTopicName().equals(topicName)) {
                excelTopic = et;
            }
        }
        excelTopic.addDeveloperPoints(developerName, points);
    }
}
