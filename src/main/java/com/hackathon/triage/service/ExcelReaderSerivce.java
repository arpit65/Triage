package com.hackathon.triage.service;

import com.hackathon.triage.excelreader.ExcelReader;
import com.hackathon.triage.excelreader.ExcelReaderRepository;
import com.hackathon.triage.excelreader.domain.ExcelComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ExcelReaderSerivce implements IExcelReader {

    @Autowired
    private ExcelReaderRepository repo;
    @Autowired
    private ExcelReader reader;

    @PostConstruct
    public void initialize() {
        System.out.println("Loading Excel file into DB ::::::::");
        loadExcelValuesIntoDb();
    }

    @Override
    public void loadExcelValuesIntoDb() {
        Map<String, ExcelComponent> excelValues = reader.loadExcelValues();
        List<ExcelComponent> collect = excelValues.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        repo.saveAll(collect);
    }
}
