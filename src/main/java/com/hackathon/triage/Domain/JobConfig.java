package com.hackathon.triage.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobConfig {

    @Id
    private int id;

    private String name;

    private long totalRecords;

    private Date lastModifiedDate;
}
