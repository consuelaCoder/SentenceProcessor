package com;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Handles functionality to extract nouns.
 */
public class SentenceParser implements ISentenceParser {
    private IResourceReader resourceReader;

    /**
     * Provides a means to create a sentence parser.
     * @constructor
     * @param resourceReader - Reads from a resource file.
     */
    public SentenceParser(IResourceReader resourceReader){

        this.resourceReader = resourceReader;
    }

    /**
     * Extracts basic sentences from a file.
     *
     * @param fileName - File where sentences will be extracted.
     * @return {List<String>} - A list of sentences.
     * @throws {IOException}
     * @method
     */
    public List<String> getSentencesFromFile(String fileName) throws IOException {
        String fileContent = resourceReader.getResourceContent(fileName);
        String[] words = fileContent.split("\\s");

        String currentSentence = "";
        Stack<String> stack = new Stack<String>();
        List<String> sentences = new LinkedList<String>();

        for (int index = 0, wordsAndSymbolsSize = words.length; index < wordsAndSymbolsSize; index++) {
            String word = words[index];
            String nextWord = GetNextWord(index, words);
            ManageQuotesInSentence(stack, word, "\"");
            ManageQuotesInSentence(stack, word, "'");

            if (currentSentence.isEmpty()) {
                currentSentence = word;
            } else if (!word.isEmpty()){
                currentSentence += (" " + word);
            }

            // a sentence is defined by the last characters
            if (stack.size() == 0
                    && !currentSentence.isEmpty()
                    && word.matches("([a-zA-Z0-9'()\"]*)(([.]\"|')|[?]|[.]|[!])$")
                    && (nextWord == "" || nextWord.matches("^[A-Z][a-zA-Z',]+"))) {
                sentences.add(currentSentence);
                currentSentence = "";
            }
        }

        return sentences;
    }

    private String GetNextWord(int currentIndex, String[] wordsAndSymbols) {
        String nextWord = "";

        for (int i = currentIndex + 1; i < wordsAndSymbols.length; i = i + 1) {
            String currentWordOrSymbol = wordsAndSymbols[i];

            if(!currentWordOrSymbol.equals("") && !currentWordOrSymbol.matches("\\s")) {
                nextWord = wordsAndSymbols[i];
                break;
            }
        }

        return nextWord;
    }

    private void ManageQuotesInSentence(Stack<String> stack, String wordAndSymbol, String quote) {
        // possession apostrophe
        if (wordAndSymbol.matches("^([a-zA-Z]+['](s))|([a-zA-Z]+[sS]['])$")
                && (stack.empty() || stack.peek().equals("'"))) {
            if (!stack.empty()) {
                stack.pop();
            }
        } else if (wordAndSymbol.contains(quote) && (stack.empty() || !stack.peek().equals(quote))) {
            stack.push(quote);
        } else if (wordAndSymbol.contains(quote) && !stack.empty()) {
            stack.pop();
        }
    }
}
