package com.example.springdemocustomerrestful.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    String pattern;

    public StringToLocalDateConverter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            try {
                throw new IllegalAccessException("invalid date  format. Please use this pattern" + pattern + "\"");
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
