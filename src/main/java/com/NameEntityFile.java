package com;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class NameEntityFile {
    private String fileName;
    private String[] nameEntities;

    @XmlAttribute(name="fileName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @XmlElementWrapper
    @XmlElement(name="NameEntity")
    public String[] getNameEntities() {
        return nameEntities;
    }

    public void setNameEntities(String[] nameEntities) {
        this.nameEntities = nameEntities;
    }
}