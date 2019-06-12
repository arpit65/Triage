package com.hackathon.triage.excelreader.domain;

import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
@Entity
public class DeveloperPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int developerId;
    String developerName;
    int topicPoints;

    public DeveloperPoints(String developer, int score) {
        this.developerName = developer;
        this.topicPoints = score;
    }
}
