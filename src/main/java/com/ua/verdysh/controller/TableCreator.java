package com.ua.verdysh.controller;

import com.ua.verdysh.model.Advertisement;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableCreator {
    private static final String FILE_NAME = "auto.xls";
    private static final String SHEET_NAME = "auto";

    public void createTable(List<Advertisement> advertisements) {
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            createRow(advertisements, sheet);
            writeToFile(workbook);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createRow(List<Advertisement> advertisements, Sheet sheet) {
        for (int count = 0; count < advertisements.size(); count++) {
            Advertisement advertisement = advertisements.get(count);
            Row row = sheet.createRow(count);
            row.setHeight((short) -1);

            List<String> advertisementFields = new ArrayList<>(Arrays.asList(advertisement.getUrl(), advertisement.getPriceUsd(),
                    advertisement.getPriceUah(), advertisement.getHeading(), advertisement.getCity(),
                    advertisement.getSeller(), advertisement.getPhoto().toString(), advertisement.getDescription()));

            advertisementFields.forEach(v -> createCell(row, advertisementFields.indexOf(v), v));
        }
    }

    private void createCell(Row row, int number, String text) {
        Cell cellUrl = row.createCell(number);
        cellUrl.setCellValue(text);
    }

    private void writeToFile(Workbook workbook) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            workbook.write(fos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
