package com.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private String date;

    public StringToLocalDateConverter(String date) {
        this.date = date;
    }

    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern(date));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("invalidate date format. Please use this pattern \"" + date + "\"");
        }
    }
}
