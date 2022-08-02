package com.example.hiberdeployed.config;

import com.example.hiberdeployed.model.StringToEnumConverter;
import com.example.hiberdeployed.model.StringToEnumConverterDepartments;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // конфигурационный класс, до запуска спринга
public class WebConfig implements WebMvcConfigurer { //
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverterDepartments());
    }


}