package org.example.ReadersAndParsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonToStringConverter {
    public String convert(URL url){
        try {
            StringBuilder response = new StringBuilder();
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(connection.getInputStream()));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                response.append(line);
            }
            return response.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
