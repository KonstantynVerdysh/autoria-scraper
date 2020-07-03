package com.ua.verdysh.controller;

import com.ua.verdysh.model.CarAdvertisement;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TableCreator {
    public void createTable(List<CarAdvertisement> advertisements, String fileName) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("auto");
        sheet.setDefaultColumnWidth(10000);

        for (int count = 0; count < advertisements.size(); count++) {
            CarAdvertisement advertisement = advertisements.get(count);
            Row row = sheet.createRow(count);

            Cell cellUrl = row.createCell(0);
            cellUrl.setCellValue(advertisement.getUrl());

            Cell cellPriceUsd = row.createCell(1);
            cellPriceUsd.setCellValue(advertisement.getPriceUsd());

            Cell cellPriceUah = row.createCell(2);
            cellPriceUah.setCellValue(advertisement.getPriceUah());

            Cell cellHeading = row.createCell(3);
            cellHeading.setCellValue(advertisement.getHeading());

            Cell cellCity = row.createCell(4);
            cellCity.setCellValue(advertisement.getCity());

            Cell cellSeller = row.createCell(5);
            cellSeller.setCellValue(advertisement.getSeller());

            Cell cellPhoto = row.createCell(6);
            cellPhoto.setCellValue(advertisement.getPhoto().toString());

            Cell cellDescription = row.createCell(7);
            cellDescription.setCellValue(advertisement.getDescription());

            Cell cellId = row.createCell(8);
            cellId.setCellValue(advertisement.getId());
        }

        createFile(wb, fileName);
    }

    private void createFile(Workbook wb, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            wb.write(fos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
