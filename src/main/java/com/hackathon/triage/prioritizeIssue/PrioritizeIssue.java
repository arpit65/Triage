package com.hackathon.triage.prioritizeIssue;

import com.hackathon.triage.domain.Issue;

import java.util.List;

public interface PrioritizeIssue {
    void prioritize(List<Issue> issues);
}
