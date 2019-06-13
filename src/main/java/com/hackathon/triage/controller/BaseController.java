package com.hackathon.triage.controller;

import com.hackathon.triage.service.TriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@RestController
@RequestMapping("/triage")
public class BaseController {

    @Autowired
    private TriageService triageService;

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public List<String> returnBestPersonToTriageThisIssue(@RequestBody String argoNumber) {
        return triageService.returnBestPersonToTriageThisIssue(argoNumber);
    }
}
