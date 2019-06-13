package com.hackathon.triage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    private int id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "pro")
    private List<Component> components;
}
