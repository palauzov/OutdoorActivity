package org.example;

import org.example.Entities.ForecastCriteria;
import org.example.ReadersAndParsers.ExcelReader;
import org.example.ReadersAndParsers.ForecastReader;
import org.example.ReadersAndParsers.JsonToStringConverter;
import org.example.Entities.Forecast;
import org.example.ReadersAndParsers.UrlCreator;
import org.example.Services.EmailService;
import org.example.Services.Service;

import javax.print.DocFlavor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
   private static final Scanner scanner = new Scanner(System.in);

    private static final JsonToStringConverter converter = new JsonToStringConverter();
    private static final ForecastReader forecastReader = new ForecastReader();

    private static final UrlCreator urlCreator = new UrlCreator();
    private static final String link = "http://api.weatherapi.com/v1/forecast.json?key=";
    private static final EmailService emailService = new EmailService();
    private static final Service service = new Service();
    private static final ExcelReader excelReader = new ExcelReader();
    private static final String excelFilePath = "C:\\Users\\User\\Documents\\folder\\SportForecast.xlsx";

    public static void main(String[] args) {

        String city = scanner.nextLine();
        String daysOfForecast = scanner.nextLine();
        URL url = urlCreator.create(link, System.getenv("PRIVATE_KEY"), city, daysOfForecast);


       String jsonResponseBody = converter.convert(url);

       ForecastCriteria criteria = excelReader.read(excelFilePath);

       List<List<Forecast>> days = forecastReader.read(jsonResponseBody);

       List<Forecast> hoursWithSun = service.removeNightHours(days);

       List<Forecast> hoursForPlay = service.bestHoursForPlayFilter(hoursWithSun, criteria);

       String text = service.printBestHours(hoursForPlay, criteria);

      // String emailReceiver = scanner.nextLine();

       emailService.sendEmail(text, "mitko.palauzov@abv.bg");

    }
}