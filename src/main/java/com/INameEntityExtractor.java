package com;

import java.io.IOException;
import java.util.List;

/**
 * Handles functionality to extract nouns.
 */
public interface INameEntityExtractor {
    /**
     * Extracts name entities from a file.
     *
     * @param fileName - File where proper nouns will be extracted.
     * @return {String[]} - An array of sentences.
     * @throws {IOException}
     * @method
     */
    String[] extractNameEntitiesFromFile(String fileName) throws IOException;
}
