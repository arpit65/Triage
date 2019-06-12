package com.hackathon.triage.domain;

public enum IssueType {
    BUG("Bug"),
    STORY("Story"),
    EPIC("Epic"),
    SUBTASK("Sub-task"),
    VERIFY("Verify-In-Version");

    private String type;

    IssueType(String type) {
        this.type = type;
    }
}
