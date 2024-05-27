package org.example.ReadersAndParsers;

public interface Reader<T> {

     T read(String responseBody);
}
