package com.hackathon.triage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCategory {

    private int categoryId;

    private String categoryKey;

    private String categoryName;
}
