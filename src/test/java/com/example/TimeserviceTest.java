package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import java.util.Map;

@MicronautTest
class TimeserviceTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testTimeEndpoint() {
        HttpRequest<Object> request = HttpRequest.GET("/time");
        HttpResponse<Map> response = client.toBlocking().exchange(request, Map.class);

        Assertions.assertEquals(HttpStatus.OK, response.status());
        Map body = response.body();
        Assertions.assertNotNull(body);
        Assertions.assertEquals("micronaut-time-provider", body.get("service"));
        Assertions.assertNotNull(body.get("now"));
    }

}