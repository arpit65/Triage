package com.hackathon.triage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
public class Expertise {
    ComponentEnum area;
    int points;
}
