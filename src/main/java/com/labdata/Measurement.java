package com.labdata;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/*
**type** (String: "temperature", "pressure", "wavelength", etc.)
- **value** (double)
- **unit** (String: "C", "Pa", "nm", etc.)
- **timestamp** (String, ISO format) */
@XmlRootElement
public class Measurement {
    String msmttype;
    String msmtval;
    String unit;
    String timestamp;
    public String getMsmttype() {
        return msmttype;
    }
    
    @XmlElement
    public void setMsmttype(String msmttype) {
        this.msmttype = msmttype;
    }

    public String getMsmtval() {
        return msmtval;
    }   

    @XmlElement
    public void setMsmtval(String msmtval) {
        this.msmtval = msmtval;
    }

    public String getUnit() {
        return unit;
    }   

    @XmlElement
    public void setUnit(String unit) {
        this.unit = unit;
    }  

    public String getTimestamp() {
        return timestamp;
    }   

    @XmlElement
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
