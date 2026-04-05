package com.bank;

import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class DataDrivenTest {

    // ============ CSV TESTS ============
    static Stream<Arguments> csvDataProvider() throws Exception {
        List<Arguments> args = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("src/test/resources/test_data.csv"));
        reader.readNext(); // skip header
        String[] line;
        while ((line = reader.readNext()) != null) {
            args.add(Arguments.of(line[0], line[1], Double.parseDouble(line[2]), Boolean.parseBoolean(line[3])));
        }
        reader.close();
        return args.stream();
    }

    @ParameterizedTest
    @MethodSource("csvDataProvider")
    @Tag("fast")
    void should_ValidateAmount_when_CSVDataLoaded(String accountId, String ownerName, double balance, boolean expectedValid) {
        AccountValidator validator = new AccountValidator();
        boolean actual = validator.isValidAmount(balance);
        assertEquals(expectedValid, actual, "Failed for: " + accountId);
    }

    // ============ EXCEL TESTS ============
    static Stream<Arguments> excelValidDataProvider() throws Exception {
        List<Arguments> args = new ArrayList<>();
        FileInputStream fis = new FileInputStream("src/test/resources/test_data.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Valid");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            args.add(Arguments.of(
                    row.getCell(0).getNumericCellValue(),
                    row.getCell(1).getNumericCellValue(),
                    (int) row.getCell(2).getNumericCellValue()
            ));
        }
        workbook.close();
        fis.close();
        return args.stream();
    }

    @ParameterizedTest
    @MethodSource("excelValidDataProvider")
    @Tag("slow")
    void should_CalculatePositivePayment_when_ValidExcelData(double principal, double rate, int months) {
        LoanCalculator calc = new LoanCalculator();
        double payment = calc.calculateMonthlyPayment(principal, rate, months);
        assertTrue(payment > 0, "Payment should be positive");
    }

    static Stream<Arguments> excelInvalidDataProvider() throws Exception {
        List<Arguments> args = new ArrayList<>();
        FileInputStream fis = new FileInputStream("src/test/resources/test_data.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Invalid");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            args.add(Arguments.of(
                    row.getCell(0).getNumericCellValue(),
                    row.getCell(1).getNumericCellValue(),
                    (int) row.getCell(2).getNumericCellValue()
            ));
        }
        workbook.close();
        fis.close();
        return args.stream();
    }

    @ParameterizedTest
    @MethodSource("excelInvalidDataProvider")
    @Tag("fast")
    void should_ThrowException_when_InvalidExcelData(double principal, double rate, int months) {
        LoanCalculator calc = new LoanCalculator();
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateMonthlyPayment(principal, rate, months));
    }

    static Stream<Arguments> excelEdgeDataProvider() throws Exception {
        List<Arguments> args = new ArrayList<>();
        FileInputStream fis = new FileInputStream("src/test/resources/test_data.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Edge");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            args.add(Arguments.of(
                    row.getCell(0).getNumericCellValue(),
                    row.getCell(1).getNumericCellValue(),
                    (int) row.getCell(2).getNumericCellValue()
            ));
        }
        workbook.close();
        fis.close();
        return args.stream();
    }

    @ParameterizedTest
    @MethodSource("excelEdgeDataProvider")
    @Tag("slow")
    void should_HandleEdgeCases_when_BoundaryExcelData(double principal, double rate, int months) {
        LoanCalculator calc = new LoanCalculator();
        double payment = calc.calculateMonthlyPayment(principal, rate, months);
        assertTrue(payment >= 0);
    }

    static Stream<Arguments> excelStressDataProvider() throws Exception {
        List<Arguments> args = new ArrayList<>();
        FileInputStream fis = new FileInputStream("src/test/resources/test_data.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Stress");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            args.add(Arguments.of(
                    row.getCell(0).getNumericCellValue(),
                    row.getCell(1).getNumericCellValue(),
                    (int) row.getCell(2).getNumericCellValue()
            ));
        }
        workbook.close();
        fis.close();
        return args.stream();
    }

    @ParameterizedTest
    @MethodSource("excelStressDataProvider")
    @Tag("integration")
    void should_HandleLargeAmounts_when_StressExcelData(double principal, double rate, int months) {
        LoanCalculator calc = new LoanCalculator();
        double payment = calc.calculateMonthlyPayment(principal, rate, months);
        assertTrue(payment > 0);
    }
}