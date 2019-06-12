package com.hackathon.triage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String name;
    String email;
    @ElementCollection
    List<Expertise> areaOfExpertise;
}
