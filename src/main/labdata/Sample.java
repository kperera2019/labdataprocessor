package com.labdata;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/*
 **sampleId** (String, XML attribute)
- **material** (String)
- **concentration** (double)
- **batch** (String) */
public class Sample {
    String sampleID;
    String materiel;
    double conc;
    String batch;
    public String getSampleID() {
        return sampleID;
    }
    @XmlAttribute
    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }
    public String getMateriel() {
        return materiel;
    }
    @XmlElement
    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }
    public double getConc() {
        return conc;
    }
    @XmlElement
    public void setConc(double conc) {
        this.conc = conc;
    }
    public String getBatch() {
        return batch;
    }
    @XmlElement
    public void setBatch(String batch) {
        this.batch = batch;
    }
}
