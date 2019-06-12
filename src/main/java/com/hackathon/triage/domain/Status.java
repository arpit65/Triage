package com.hackathon.triage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    int statusId;
    private String statusName;
    private String statusDescription;
    @Embedded
    private StatusCategory statusCategory;
}
