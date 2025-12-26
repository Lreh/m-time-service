package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
public class TimeController {

    @Get("/time")
    public Map<String, String> index() {
        return Map.of(
                "now", ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME),
                "service", "micronaut-time-provider"
        );
    }

    @Get("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}