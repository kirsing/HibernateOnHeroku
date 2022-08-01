package com.example.hiberdeployed.model;

import com.example.hiberdeployed.exception.DayException;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Day> {


    @Override
    public Day convert(String source) {
        try {
            return Day.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DayException(source + " NOT SUPPORTED");
        }
    }
}