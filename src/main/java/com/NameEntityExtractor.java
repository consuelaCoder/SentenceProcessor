package com;

import java.io.IOException;

/**
 * Handles functionality to extract nouns.
 */
public class NameEntityExtractor implements INameEntityExtractor {

    private IResourceReader resourceReader;

    /**
     * Provides a means to create a noun extractor.
     * @constructor
     * @param resourceReader - Reads from a resource file.
     */
    public NameEntityExtractor(IResourceReader resourceReader) {

        this.resourceReader = resourceReader;
    }

    /**
     * Extracts name entities from a file.
     *
     * @param fileName - File where sentences will be extracted.
     * @return {String[]} - An array of sentences.
     * @throws {IOException}
     * @method
     */
    public String[] extractNameEntitiesFromFile(String fileName) throws IOException {
        String contentFile = resourceReader.getResourceContent(fileName);

        return contentFile.split("\\n");
    }
}
