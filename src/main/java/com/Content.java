package com;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Content {

    private List<SentenceFile> sentenceFiles;
    private NameEntityFile nameEntityFile;

    @XmlElementWrapper
    @XmlElement(name="sentenceFile")
    public List<SentenceFile> getSentenceFiles() {
        return sentenceFiles;
    }

    public void setSentenceFiles(List<SentenceFile> sentenceFiles) {
        this.sentenceFiles = sentenceFiles;
    }

    @XmlElement(name="nameEntityFile")
    public NameEntityFile getNameEntityFile() {
        return nameEntityFile;
    }

    public void setNameEntityFile(NameEntityFile nameEntityFile) {
        this.nameEntityFile = nameEntityFile;
    }
}
