package com.hackathon.triage.excelreader;

import com.hackathon.triage.excelreader.domain.ExcelComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelReaderRepository extends JpaRepository<ExcelComponent, Integer> {

}
