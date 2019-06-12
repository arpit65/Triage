package com.hackathon.triage.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    @Id
    private int issueId;

    private String argoNumber;

    private String summary;

    @Column(updatable = false, insertable = false)
    private String description;

    @Embedded
    private User assignee;

    private int priority;

    private int watchCount;

    private IssueType type;

    @Embedded
    private Status status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "component_issue_table",
            inverseJoinColumns = @JoinColumn(name = "component_id", referencedColumnName = "componentId"),
            joinColumns = @JoinColumn(name = "issue_id", referencedColumnName = "issueId"))
    private List<Component> components;
}
