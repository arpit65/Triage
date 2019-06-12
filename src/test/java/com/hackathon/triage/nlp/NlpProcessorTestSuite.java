package com.hackathon.triage.nlp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NlpProcessorTestSuite {

    @Autowired
    private NlpProcessor nlpProcessor;
    @Test
    public void checkTest() {}


    @Test
    public void testGetNounsMethod() {
        String str = "Rail Splitting will only consider column one for splitting jobs into a Reach Stacker block, column two fails to deck (only for 40ft)";
        List<String> output = NlpProcessor.getNouns(str);
        System.out.println(output);
    }
}
