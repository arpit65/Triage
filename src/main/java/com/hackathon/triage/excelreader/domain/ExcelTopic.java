package com.hackathon.triage.excelreader.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ExcelTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int topicId;
    String topicName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "exceltopic_devpoints_id", referencedColumnName = "topicId")
    List<DeveloperPoints> points = new ArrayList<>();

    public ExcelTopic(String topicName) {
        this.topicName = topicName;
    }

    public void addDeveloperPoints(String developerName, int point) {
        points.add(new DeveloperPoints(developerName, point));
    }
}
