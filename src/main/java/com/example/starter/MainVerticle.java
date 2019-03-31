package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.Properties;

public class MainVerticle extends AbstractVerticle {


    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Properties prop = new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        Object webPort = prop.get("webPort");
        int webport = Integer.valueOf((String) (webPort));
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        }).listen(webport, http -> {
            if (http.succeeded()) {
                startFuture.complete();
                System.out.println("HTTP server started on port " + webPort);
            } else {
                startFuture.fail(http.cause());
            }
        });
    }

}
