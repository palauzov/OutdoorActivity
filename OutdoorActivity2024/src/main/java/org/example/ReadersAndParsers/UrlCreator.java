package org.example.ReadersAndParsers;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlCreator {

    public URL create(String link, String privateKey, String city, String days){
          StringBuilder sb = new StringBuilder();
          sb.append(link);
          sb.append(privateKey);
          sb.append("&q=");
          sb.append(city);
          sb.append("&days=");
          sb.append(days);
          sb.append("&aqi=no&alerts=no");
        try {
            return new URL(sb.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
