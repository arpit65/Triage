package com.hackathon.triage.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.tokensregex.TokenSequenceMatcher;
import edu.stanford.nlp.ling.tokensregex.TokenSequencePattern;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class NlpProcessor {

    public static List<String> getNouns(String inDescription) {
        Properties properties = new Properties();

        properties.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

        Annotation annotation = pipeline.process(inDescription);

        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        List<String> output = new ArrayList<>();

        String regex = "([{pos:/NN|NNS|NNP/}])"; //extracting Nouns

        for (CoreMap sentence : sentences) {

            List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);

            TokenSequencePattern tspattern = TokenSequencePattern.compile(regex);

            TokenSequenceMatcher tsmatcher = tspattern.getMatcher(tokens);

            while (tsmatcher.find()) {

                output.add(tsmatcher.group());

            }

        }
        return output;
    }

}
