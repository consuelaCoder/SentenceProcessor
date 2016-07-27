package com;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class SentenceMatch {
    private String sentence;
    private List<String> sentenceMatchingNouns;

    @XmlElement(name="sentence")
    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @XmlElementWrapper
    @XmlElement(name="sentenceMatchingNoun")
    public List<String> getSentenceMatchingNouns() {
        return sentenceMatchingNouns;
    }

    public void setSentenceMatchingNouns(List<String> sentenceMatchingNouns) {

        this.sentenceMatchingNouns = sentenceMatchingNouns;
    }
}
