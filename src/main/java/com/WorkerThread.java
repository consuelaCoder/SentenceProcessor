package com;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Represents a thread to parse a file for sentences and matching nouns.
 */
public class WorkerThread implements Callable<SentenceFile> {
    private ISentenceParser sentenceParser;
    private String[] nameEntities;
    private INameEntityFinder nounFinder;
    private String fileName;

    /**
     * Provides a means to create a sentence parser.
     * @constructor
     * @param sentenceParser - Sentence parser.
     * @param nameEntities - Name entities.
     * @param nounFinder - Noun finder.
     * @param fileName - File name.
     */
    public WorkerThread(
            ISentenceParser sentenceParser,
            String[] nameEntities,
            INameEntityFinder nounFinder,
            String fileName) {
        this.sentenceParser = sentenceParser;
        this.nameEntities = nameEntities;
        this.nounFinder = nounFinder;
        this.fileName = fileName;
    }

    /**
     * Extracts basic sentences from a file.
     * @return {File} - A file containing sentences with their noun matches.
     * @throws {Exception}
     * @method
     */
    public SentenceFile call() throws Exception {
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

        return file;
    }
}
