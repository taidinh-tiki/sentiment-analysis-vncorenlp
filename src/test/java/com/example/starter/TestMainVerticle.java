package com.example.starter;

import io.vertx.core.Vertx;
import io.vertx.junit5.Timeout;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {

    @BeforeEach
    void deployVerticle(Vertx vertx, VertxTestContext testContext) {
        vertx.deployVerticle(new MainVerticle(), testContext.succeeding(id -> testContext.completeNow()));
    }

    @Test
    @DisplayName("Should start a Web Server on port 15000")
    @Timeout(value = 10, timeUnit = TimeUnit.SECONDS)
    void start_http_server(Vertx vertx, VertxTestContext testContext) throws Throwable {
        Properties prop = new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        Object webPort = prop.get("webPort");
        Logger logger = LoggerFactory.getLogger(TestMainVerticle.class);
        logger.info(webPort.toString());
        vertx.createHttpClient().getNow(Integer.parseInt(webPort.toString()), "localhost", "/", response -> testContext.verify(() -> {
            assertTrue(response.statusCode() == 200);
            response.handler(body -> {
                assertEquals(body.toString(), "Hello from Vert.x!");
                testContext.completeNow();
            });
        }));
    }

}
