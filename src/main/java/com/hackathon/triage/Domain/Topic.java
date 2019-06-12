package com.hackathon.triage.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.Map;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    private int id;

    private String topicName;

    private Map<User, Integer> topicExpertise;
}
