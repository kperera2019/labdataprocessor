package com.labdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabDataController {

    private final DataProcessor processor;

    public LabDataController(DataProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/process")
    public String processExperiment() {
        processor.exportExperiment();
        Result res = processor.importExperiment("experimentEXP123.xml");
        processor.enrichObject(res);
        return "Processed experiment " + res.getExperimentId();
    }

    @GetMapping("/status")
    public String status() {
        return "Lab Data Processor is running at "  + java.time.LocalDateTime.now();
    }
}