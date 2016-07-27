package com;

import java.util.List;

/**
 * Finds name entitites from string content.
 */
public interface INameEntityFinder {
    /**
     * Finds name entities in content from a noun list.
     *
     * @param nouns - A list of nouns.
     * @param content - Content to search for nouns.
     * @return {List<String>} - A list of matched nouns.
     * @method
     */
    List<String> findNameEntitiesInContent(String[] nouns, String content);
}
