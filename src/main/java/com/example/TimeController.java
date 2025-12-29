package com.example;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TimeController {

    private static final Logger LOG = LoggerFactory.getLogger(TimeController.class);

    @Value("${environment:default}")
    String environment;

    @Value("${logLevel:INFO}")
    String logLevel;

    @Get("/time")
    public Map<String, String> index() {
        LOG.info("""
                Request received for /time endpoint
                kubectl port-forward svc/m-time-service 8050:8080
                localhost:8050/time""");

        return Map.of(
                "now", ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME),
                "service", "micronaut-time-provider",
                "environment", environment,
                "logLevel", logLevel
        );
    }

    @Get("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}