package com.labdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class LabDataController {

    private final DataProcessor processor;

    public LabDataController(DataProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/process")
    public String[] processExperiment() {
        //processor.exportExperiment();
        //Result res = processor.importExperiment("experimentEXP123.xml");
        //processor.enrichObject(res);
        //return "Processed experiment " + res.getExperimentId() + " because you asked for it at " + java.time.LocalDateTime.now();
        Experiment[] exps = processor.pipeline();

        Experiment exp1 = exps[0];
        Experiment exp2 = exps[1];
        String res1 = "Experiment 1: ID=" + exp1.getExperimentID() + ", Title=" + exp1.getTitle() + ", Researcher=" + exp1.getResearcher() + ", DateCreated=" + exp1.getDateCreated() + ", Status=" + exp1.getStatus();
        String res2 = "Experiment 2: ID=" + exp2.getExperimentID() + ", Title=" + exp2.getTitle() + ", Researcher=" + exp2.getResearcher() + ", DateCreated=" + exp2.getDateCreated() + ", Status=" + exp2.getStatus();
        return new String[]{"Experiment processed at " + java.time.LocalDateTime.now(), res1, res2};
    }

    @GetMapping("/status")
    public String status() {
        return "Lab Data Processor is running at "  + java.time.LocalDateTime.now();
    }
}