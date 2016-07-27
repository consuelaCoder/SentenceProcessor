package com;

import java.io.IOException;
import java.util.List;

/**
 * Handles functionality to parse a sentence.
 */
public interface ISentenceParser {
    /**
     * Extracts basic sentences from a file.
     *
     * @param fileName - File where sentences will be extracted.
     * @return {List<String>} - A list of sentences.
     * @throws {IOException}
     * @method
     */
    List<String> getSentencesFromFile(String fileName) throws IOException;
}
