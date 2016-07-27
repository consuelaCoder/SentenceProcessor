package com;

import java.util.LinkedList;
import java.util.List;

/**
 * Finds nouns from string content.
 */
public class NameEntityFinder implements INameEntityFinder {
    /**
     * Finds name entities in content from a noun list.
     *
     * @param nouns - A list of nouns.
     * @param content - Content to search for nouns.
     * @return {List<String>} - A list of matched nouns.
     * @method
     */
    public List<String> findNameEntitiesInContent(String[] nouns, String content) {
        List<String> matchingNouns = new LinkedList<String>();

        for (String noun : nouns) {
            if (content.contains(noun)) {
                matchingNouns.add(noun);
            }
        }

        return matchingNouns;
    }
}
