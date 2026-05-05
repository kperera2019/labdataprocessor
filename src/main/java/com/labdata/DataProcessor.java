package com.labdata;

import java.util.List;
import java.io.File;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;



@Service
public class DataProcessor {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        processor.exportExperiment();
        Result res =processor.importExperiment("experimentEXP123.xml");
        processor.enrichObject(res);
    }
        
    public void exportExperiment() {
        System.out.println("Button pushed; exporting experiment...");
        // Create an Experiment object
        Experiment exp = new Experiment();
        exp.setExperimentID("EXP123");
        exp.setTitle("Test Experiment");
        exp.setResearcher("Dr. Smith");
        exp.setDateCreated("2024-06-01T10:00:00Z");
        exp.setStatus("raw");
        // Marshal the Experiment object to XML
        try {
            String xmlFilePath = "experiment" + exp.getExperimentID() + ".xml";
            JAXBContext context = JAXBContext.newInstance(Experiment.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(exp, new File(xmlFilePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public Result importExperiment(String xmlFilePath) {

        try {
            JAXBContext context = JAXBContext.newInstance(Experiment.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Experiment exp = (Experiment) unmarshaller.unmarshal(new File(xmlFilePath));
            // Process the imported Experiment object as needed
            Result res = new Result();
            res.setExperimentId(exp.getExperimentID());
            res.setNotes("Experiment imported successfully.");
            res.setSamples(null);
            res.setMeasurements(null);
            System.out.println("Imported Experiment ID: " + exp.getExperimentID());
            System.out.println("Title: " + exp.getTitle());
            System.out.println("Researcher: " + exp.getResearcher());
            System.out.println("Date Created: " + exp.getDateCreated());
            System.out.println("Status: " + exp.getStatus());
            Result result = res;
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void enrichObject(Result res) {
        res.setProcessedAt(java.time.Instant.now().toString());
        res.setNotes("Experiment processed successfully.");
        // Add dummy samples and measurements for demonstration
        res.setSamples(null);
        if (res.getMeasurements() == null) {
            res.setMeasurements(null);
        } else {
            List<Measurement> msmt = res.getMeasurements();
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
            System.out.println(min+","+max+","+mean+","+stdDev);
            double[] stats = new double[]{mean, stdDev, min, max};
            res.setCalculatedStats(stats);
        }

        String newexp = res.getExperimentId() + "_processed.xml";
        Experiment expset = new Experiment();
        expset.setExperimentID(newexp);
        expset.setTitle("Test Experiment");
        expset.setResearcher("Dr. Smith");
        expset.setDateCreated(java.time.Instant.now().toString());
        expset.setStatus("processed");


        System.out.println("Exporting enriched Result to: " + newexp);
        String newxmlFilePath = "experiment" + res.getExperimentId() + "_processed.xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Experiment.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(expset, new File(newxmlFilePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
        
    
}
