package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;

public class ReadData {
    Map<String,String> valueMap = new LinkedHashMap<>();
    public Map<String,String> readData(int row, String nameOfExcel) {
        try {
            FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "/" + nameOfExcel + ".xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(file);

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Get specified row and store the values in a map
            Row rowHeader = sheet.getRow(0);
            Row rowValues = sheet.getRow(row);
            Iterator<Cell> cellIteratorValues = rowValues.cellIterator();
            while(cellIteratorValues.hasNext()) {
                Cell cell = cellIteratorValues.next();
                String header = rowHeader.getCell(cell.getColumnIndex()).getStringCellValue();
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case NUMERIC:
                        String value = Double.toString(cell.getNumericCellValue()).replace(".0", "");
                        valueMap.put(header, value);
                        break;
                    case STRING:
                        valueMap.put(header, cell.getStringCellValue());
                        break;
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valueMap;
    }
}
