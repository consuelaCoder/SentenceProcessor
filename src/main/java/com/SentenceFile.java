package com;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class SentenceFile {
    private String fileName;
    private List<SentenceMatch> sentenceMatches;

    @XmlAttribute(name="fileName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @XmlElementWrapper
    @XmlElement(name="SentenceMatch")
    public List<SentenceMatch> getSentenceMatches() {
        return sentenceMatches;
    }

    public void setSentenceMatches(List<SentenceMatch> sentenceMatches) {
        this.sentenceMatches = sentenceMatches;
    }
}