package com.hackathon.triage.excelreader;

import com.hackathon.triage.excelreader.domain.ExcelComponent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Component
public class ExcelReader {

    @Value("${expertise.file}")
    private String FILE_NAME;
    private Map<String, ExcelComponent> allComponents;
    private Map<Integer, String> orderOfComponents;
    private Map<Integer, String> orderOfTopics;

    public ExcelReader() {
        allComponents = new HashMap<>();
        orderOfComponents = new HashMap<>();
        orderOfTopics = new HashMap<>();
    }

    public Map<String, ExcelComponent> loadExcelValues() {

        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            int count = 0;
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //This is for the first row to to get all the component and topic names
                if(count==0) {
                    if (cellIterator.next().getStringCellValue().equals("Topics")) {

                        while (cellIterator.hasNext()) {
                            String componentNameAndTopicName = cellIterator.next().getStringCellValue();
                            if (componentNameAndTopicName.isEmpty()) {
                                continue;
                            }
                            String[] arrs = componentNameAndTopicName.split("\\W+");
                            String componentName;
                            String topicName;
                            if (arrs.length == 1) {
                                topicName = arrs[0];
                                componentName = arrs[0];
                            } else {
                                componentName = arrs[0];
                                topicName = arrs[1];
                            }
                            if (allComponents.isEmpty()) {
                                allComponents.put(componentName, new ExcelComponent(componentName, topicName));
                            } else {
                                if (allComponents.containsKey(componentName)) {
                                    allComponents.get(componentName).addTopics(topicName);
                                    orderOfComponents.put(count, componentName);
                                    orderOfTopics.put(count, topicName);
                                } else {
                                    allComponents.put(componentName, new ExcelComponent(componentName, topicName));
                                    orderOfComponents.put(count, componentName);
                                    orderOfTopics.put(count, topicName);
                                }
                            }
                            count++;
                        }
                    }
                }else {
                int countForInserting = 0;
                String developerName = " ";
                while (cellIterator.hasNext() && countForInserting < count) {
                    Cell currentCell = cellIterator.next();
                    if(countForInserting==0 && currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
                        continue;
                    }
                    if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                        developerName = currentCell.getStringCellValue();
                        countForInserting++;
                        continue;
                    }

                    if (currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
                        allComponents.get(orderOfComponents.get(countForInserting)).updateTopicPoints(orderOfTopics.get(countForInserting), developerName, 0);
                        countForInserting++;
                        continue;
                    } else {
                        if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            allComponents.get(orderOfComponents.get(countForInserting)).updateTopicPoints(orderOfTopics.get(countForInserting), developerName, (int) currentCell.getNumericCellValue()*10);
                            countForInserting++;
                            continue;
                        }
                    }
                }
            }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allComponents;
//        System.out.println(allComponents.get("A4").getTopics());
    }
}
