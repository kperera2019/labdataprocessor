package com.labdata;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
//import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/*
- **experimentId** (String)
- **samples** (List<Sample>)
- **measurements** (List<Measurement>)
- **notes** (String, optional)
- **processedAt** (String, added during processing)
- **calculatedStats** (double: mean, stdDev, min, max) */
public class Result {
    String experimentId;
    List<Sample> samples;
    List<Measurement> measurements;
    String notes;
    String processedAt;
    double[] calculatedStats;
    public String getExperimentId() {
        return experimentId;
    }
    @XmlAttribute
    public void setExperimentId(String experimentId) {
        this.experimentId = experimentId;
    }
    public List<Sample> getSamples() {
        return samples;
    }
    @XmlElement
    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }
    public List<Measurement> getMeasurements() {
        return measurements;
    }
    @XmlElement
    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
    public String getNotes() {
        return notes;
    }
    @XmlElement
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getProcessedAt() {
        return processedAt;
    }
    @XmlElement
    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }

    public double[] getCalculatedStats() {
        List<Measurement> msmt = this.getMeasurements();
        double sum = 0.0;
        for (Measurement m : msmt) {
            sum += Double.parseDouble(m.getMsmtval());
        }
        double mean = sum / msmt.size();
        double variance = 0.0;
        for (Measurement m : msmt) {
            variance += Math.pow(Double.parseDouble(m.getMsmtval()) - mean, 2);
        }
        variance /= msmt.size();
        double stdDev = Math.sqrt(variance);
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (Measurement m : msmt) {
            double val = Double.parseDouble(m.getMsmtval());
            if (val < min) {
                min = val;
            }
            if (val > max) {
                max = val;
            }
        }
        return new double[]{mean, stdDev, min, max};
    }

    @XmlElement
    public void setCalculatedStats(double[] calculatedStats) {

        this.calculatedStats = calculatedStats;
    }
}
