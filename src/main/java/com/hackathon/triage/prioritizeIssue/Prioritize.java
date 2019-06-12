package com.hackathon.triage.prioritizeIssue;

import com.hackathon.triage.domain.Issue;

import java.util.Comparator;
import java.util.List;

public class Prioritize implements PrioritizeIssue {

    private final int maxPriority = 5;
    @Override
    public void prioritize(List<Issue> issues) {
        Comparator<Issue> comparator = Comparator.comparingInt(this::priorityPoints);
        issues.sort(comparator);
    }

    private int priorityPoints(Issue issue) {
        int score = 0;
        score+=(maxPriority-issue.getPriority())*5;
        score+=issue.getWatchCount()*3;
//        score+=issue.getCommentedUsers().size()*2;
        return score;
    }

}
