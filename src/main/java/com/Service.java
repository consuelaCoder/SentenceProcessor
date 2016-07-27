package com;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Provides a entry point for the sentence processor.
 */
public class Service {
    public static void main(String[] args) throws Exception {
        // ToDo: use IOC container
        ISentenceParser sentenceParser = new SentenceParser(new ResourceReader());
        JAXBContext jc = JAXBContext.newInstance(Content.class);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Content content = new Content();
        List<SentenceFile> files = new LinkedList<>();

        String sentenceFileName = "nlp_data.txt";
        List<String> sentences = sentenceParser.getSentencesFromFile(sentenceFileName);
        List<SentenceMatch> sentenceMatches = new LinkedList<SentenceMatch>();

        for (String sentence : sentences) {
            SentenceMatch sentenceMatch = new SentenceMatch();
            sentenceMatch.setSentence(sentence);
            sentenceMatches.add(sentenceMatch);
        }

        SentenceFile file = new SentenceFile();
        file.setSentenceMatches(sentenceMatches);
        file.setFileName(sentenceFileName);
        files.add(file);

        content.setSentenceFiles(files);

        marshaller.marshal(content, System.out);
    }
}
