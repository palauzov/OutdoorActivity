package org.example.ReadersAndParsers;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.poi.ss.usermodel.*;
import org.example.Entities.ForecastCriteria;

public class ExcelReader implements Reader<ForecastCriteria>{


    @Override
    public ForecastCriteria read(String filePath) {
        ForecastCriteria criteria = new ForecastCriteria();
        try {
            FileInputStream file = new FileInputStream(new File(filePath)) ;

            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            String sportsName = sheet.getRow(1).getCell(0).toString();
            int min_temp = (int)Double.parseDouble(sheet.getRow(1).getCell(1).toString());
            int max_temp = (int) Double.parseDouble(sheet.getRow(1).getCell(2).toString());
            int chanceOfRaining = (int)Double.parseDouble(sheet.getRow(1).getCell(3).toString());
            int wind_kph = (int)Double.parseDouble(sheet.getRow(1).getCell(4).toString());

            criteria.setSportsName(sportsName);
            criteria.setChanceOfRain(chanceOfRaining);
            criteria.setMaxTemp(max_temp);
            criteria.setMinTemp(min_temp);
            criteria.setWind_kph(wind_kph);

        } catch (IOException e) {
            System.out.println("File not found");
        }

       return criteria;
    }

    }

