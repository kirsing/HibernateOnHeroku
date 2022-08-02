package com.example.hiberdeployed.model;
import com.example.hiberdeployed.exception.DayException;
import com.example.hiberdeployed.exception.DepartmentException;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverterDepartments implements Converter<String, Department>{
    @Override
    public Department convert(String source) {
        try {
            return Department.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DepartmentException(source + " NOT SUPPORTED");
        }
    }
}

