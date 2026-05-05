# Lab Data Processor - Requirements Specification

## 1. Project Overview

A JAXB-based data ingestion and processing system for research lab experiment data. The system reads experiment metadata and measurements from XML files (legacy instrument output), transforms them via Java objects, enriches them with calculated values, and writes processed data back to XML.

---

## 2. Domain Model

### 2.1 Experiment
- **experimentId** (String, required, XML attribute)
- **title** (String)
- **researcher** (String)
- **dateCreated** (String, ISO format)
- **status** (String: "raw", "processed", "archived")

### 2.2 Sample
- **sampleId** (String, XML attribute)
- **material** (String)
- **concentration** (double)
- **batch** (String)

### 2.3 Measurement
- **type** (String: "temperature", "pressure", "wavelength", etc.)
- **value** (double)
- **unit** (String: "C", "Pa", "nm", etc.)
- **timestamp** (String, ISO format)

### 2.4 Result (Root Element)
- **experimentId** (String)
- **samples** (List<Sample>)
- **measurements** (List<Measurement>)
- **notes** (String, optional)
- **processedAt** (String, added during processing)
- **calculatedStats** (double: mean, stdDev, min, max)

---

## 3. Functional Requirements

### 3.1 Reading
- Load `experiment_NNN.xml` from disk
- Unmarshal to Java `Result` object
- Validate required fields exist
- Handle missing optional fields gracefully

### 3.2 Processing
- Calculate statistics (mean, stdDev, min, max) from measurements
- Add processing timestamp (current date/time)
- Update experiment status to "processed"
- Log what data was enriched

### 3.3 Writing
- Marshal enriched `Result` object back to XML
- Save as `experiment_NNN_processed.xml`
- Format output with proper indentation
- Preserve data types

### 3.4 Batch Processing (Future)
- Process multiple XML files in sequence
- Generate summary report
- Handle failures per file without stopping entire batch

---

## 4. Technical Requirements

### 4.1 Build & Dependencies
- Maven-based build
- Jakarta XML Binding (jakarta.xml.bind-api + jaxb-impl)
- Java 11+ compiler target
- Single entry point via `mvn exec:java`

### 4.2 Project Structure
```
lab-data-processor/
├── src/main/java/com/labdata/
│   ├── Result.java
│   ├── Sample.java
│   ├── Measurement.java
│   ├── Experiment.java
│   └── DataProcessor.java (main orchestrator)
├── src/main/resources/
│   └── sample_experiment.xml
├── pom.xml
└── REQUIREMENTS.md
```

### 4.3 Error Handling
- Catch JAXBException and provide meaningful error messages
- Log processing errors without stopping execution
- Return exit codes (0 = success, 1 = failure)

---

## 5. Deliverables

- [ ] Domain classes with JAXB annotations
- [ ] Sample XML input file
- [ ] DataProcessor class with read→process→write workflow
- [ ] Processed XML output file
- [ ] Maven pom.xml configured
- [ ] Executable via `mvn exec:java`

---

## 6. Success Criteria

1. Can read raw experiment XML
2. Successfully converts to Java objects
3. Calculates and adds statistics
4. Writes valid XML with all enriched data
5. Can verify output by reading it back in
