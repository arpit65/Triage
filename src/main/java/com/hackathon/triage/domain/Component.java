package com.hackathon.triage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Component {

    @Id
    private int componentId;

    private String componentName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "component_issue_table",
            joinColumns = @JoinColumn(name = "component_id", referencedColumnName = "componentId"),
            inverseJoinColumns = @JoinColumn(name = "issue_id", referencedColumnName = "issueId"))
    private List<Issue> issues;

    public Component(Component c) {
        System.out.println("Creating new component with someother component");
        this.componentId = c.getComponentId();
        this.componentName = c.getComponentName();
        this.issues = c.getIssues();
        System.out.println("End of new component");
    }
}
