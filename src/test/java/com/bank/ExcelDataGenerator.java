package com.bank;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelDataGenerator {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        // Sheet 1: Valid
        Sheet valid = workbook.createSheet("Valid");
        String[] headers = {"principal", "annualRate", "months", "expectedPayment"};
        Row h1 = valid.createRow(0);
        for (int i = 0; i < headers.length; i++) h1.createCell(i).setCellValue(headers[i]);
        Object[][] validData = {
                {100000.0, 12.0, 12, "positive"},
                {50000.0, 10.0, 24, "positive"},
                {200000.0, 8.0, 36, "positive"},
                {75000.0, 15.0, 12, "positive"},
                {150000.0, 11.0, 48, "positive"}
        };
        for (int i = 0; i < validData.length; i++) {
            Row row = valid.createRow(i + 1);
            row.createCell(0).setCellValue((Double) validData[i][0]);
            row.createCell(1).setCellValue((Double) validData[i][1]);
            row.createCell(2).setCellValue((Integer) validData[i][2]);
            row.createCell(3).setCellValue((String) validData[i][3]);
        }

        // Sheet 2: Invalid
        Sheet invalid = workbook.createSheet("Invalid");
        Row h2 = invalid.createRow(0);
        for (int i = 0; i < headers.length; i++) h2.createCell(i).setCellValue(headers[i]);
        Object[][] invalidData = {
                {-1000.0, 12.0, 12, "exception"},
                {0.0, 10.0, 24, "exception"},
                {50000.0, -5.0, 12, "exception"},
                {50000.0, 10.0, 0, "exception"},
                {50000.0, 10.0, -1, "exception"}
        };
        for (int i = 0; i < invalidData.length; i++) {
            Row row = invalid.createRow(i + 1);
            row.createCell(0).setCellValue((Double) invalidData[i][0]);
            row.createCell(1).setCellValue((Double) invalidData[i][1]);
            row.createCell(2).setCellValue((Integer) invalidData[i][2]);
            row.createCell(3).setCellValue((String) invalidData[i][3]);
        }

        // Sheet 3: Edge
        Sheet edge = workbook.createSheet("Edge");
        Row h3 = edge.createRow(0);
        for (int i = 0; i < headers.length; i++) h3.createCell(i).setCellValue(headers[i]);
        Object[][] edgeData = {
                {0.01, 0.0, 1, "positive"},
                {1000000.0, 0.0, 360, "positive"},
                {100.0, 100.0, 1, "positive"},
                {999999.99, 12.0, 12, "positive"},
                {1.0, 0.1, 1, "positive"}
        };
        for (int i = 0; i < edgeData.length; i++) {
            Row row = edge.createRow(i + 1);
            row.createCell(0).setCellValue((Double) edgeData[i][0]);
            row.createCell(1).setCellValue((Double) edgeData[i][1]);
            row.createCell(2).setCellValue((Integer) edgeData[i][2]);
            row.createCell(3).setCellValue((String) edgeData[i][3]);
        }

        // Sheet 4: Stress
        Sheet stress = workbook.createSheet("Stress");
        Row h4 = stress.createRow(0);
        for (int i = 0; i < headers.length; i++) h4.createCell(i).setCellValue(headers[i]);
        Object[][] stressData = {
                {9999999.0, 25.0, 360, "positive"},
                {5000000.0, 20.0, 240, "positive"},
                {1000000.0, 18.0, 180, "positive"},
                {8000000.0, 15.0, 120, "positive"},
                {7500000.0, 22.0, 300, "positive"}
        };
        for (int i = 0; i < stressData.length; i++) {
            Row row = stress.createRow(i + 1);
            row.createCell(0).setCellValue((Double) stressData[i][0]);
            row.createCell(1).setCellValue((Double) stressData[i][1]);
            row.createCell(2).setCellValue((Integer) stressData[i][2]);
            row.createCell(3).setCellValue((String) stressData[i][3]);
        }

        FileOutputStream out = new FileOutputStream(
                "src/test/resources/test_data.xlsx");
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println("Excel file generated!");
    }
}