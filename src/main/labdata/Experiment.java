package com.labdata;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/*
- **experimentId** (String, required, XML attribute)
- **title** (String)
- **researcher** (String)
- **dateCreated** (String, ISO format)
- **status** (String: "raw", "processed", "archived")
 */
@XmlRootElement
public class Experiment {
    
    String experimentID;
    String title;
    String researcher;
    String dateCreated;
    String status;

    public String getExperimentID() {
        return experimentID;
    }
    @XmlAttribute
    public void setExperimentID(String experimentID) { //unique id -> attribute
        this.experimentID = experimentID;
        System.out.println("Set experimentID to " + experimentID);
    }

    public String getTitle() {
        return title;
    }
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }
    public String getResearcher() {
        return researcher;
    }
    @XmlElement
    public void setResearcher(String researcher) {
        this.researcher = researcher;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    @XmlElement
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getStatus() {
        return status;
    }
    @XmlElement
    public void setStatus(String status) {
        this.status = status;
    }
}
