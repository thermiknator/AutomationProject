package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadData {
    Map<String,String> valueMap = new LinkedHashMap<>();
    public void readData() {
        try {
            FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "/Testdata.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(file);

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Get specified row and store the values in a map
            Row rowHeader = sheet.getRow(0);
            Row rowValues = sheet.getRow(1);
            Iterator<Cell> cellIteratorValues = rowValues.cellIterator();
            while(cellIteratorValues.hasNext()) {
                Cell cell = cellIteratorValues.next();
                String header = rowHeader.getCell(cell.getColumnIndex()).getStringCellValue();
                System.out.println(header);
                System.out.println(cell.getStringCellValue());
                valueMap.put(header, cell.getStringCellValue());
                System.out.println(valueMap.toString());
            }
            /*
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
                System.out.println("");
            }*/
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
