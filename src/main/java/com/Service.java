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
        INameEntityFinder nounFinder = new NameEntityFinder();
        INameEntityExtractor nounExtractor = new NameEntityExtractor(new ResourceReader());
        String[] nameEntities = nounExtractor.extractNameEntitiesFromFile("NER.txt");
        JAXBContext jc = JAXBContext.newInstance(Content.class);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        String[] fileNames = new String[] { "d01", "d02", "d03", "d04", "d05", "d06", "d07", "d08", "d09", "d10" };
        Content content = new Content();
        List<SentenceFile> files = new LinkedList<>();

        for (String fileName : fileNames) {
            String sentenceFileName = fileName + ".txt";
            List<String> sentences = sentenceParser.getSentencesFromFile(sentenceFileName);
            List<SentenceMatch> sentenceMatches = new LinkedList<SentenceMatch>();

            for (String sentence : sentences) {
                SentenceMatch sentenceMatch = new SentenceMatch();
                List<String> matchingNouns = nounFinder.findNameEntitiesInContent(nameEntities, sentence);
                sentenceMatch.setSentenceMatchingNouns(matchingNouns);
                sentenceMatch.setSentence(sentence);
                sentenceMatches.add(sentenceMatch);
            }

            SentenceFile file = new SentenceFile();
            file.setSentenceMatches(sentenceMatches);
            file.setFileName(sentenceFileName);
            files.add(file);
        }

        NameEntityFile nameEntityFile = new NameEntityFile();
        nameEntityFile.setFileName("NER.txt");
        nameEntityFile.setNameEntities(nameEntities);
        content.setNameEntityFile(nameEntityFile);
        content.setSentenceFiles(files);

        marshaller.marshal(content, System.out);
    }
}
