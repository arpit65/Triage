package com.hackathon.triage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Data
@AllArgsConstructor
public class JiraAccount {

    private String userName;

    private String password;
}
