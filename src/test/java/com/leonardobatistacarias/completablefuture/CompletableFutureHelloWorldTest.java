package com.leonardobatistacarias.completablefuture;

import com.leonardobatistacarias.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.leonardobatistacarias.util.CommonUtil.startTimer;
import static com.leonardobatistacarias.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompletableFutureHelloWorldTest {

    HelloWorldService helloWorldService = new HelloWorldService();
    CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(helloWorldService);

    @Test
    void helloWorld() {

        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld();

        completableFuture.thenAccept(s -> {
            assertEquals("HELLO WORLD", s);
        });
    }

    @Test
    void helloWorld_multiple_async_calls() {
        String helloWorld = completableFutureHelloWorld.helloWorld_multiple_async_calls();

        assertEquals("HELLO WORLD!", helloWorld);
    }

    @Test
    void helloWorld_3_async_calls() {
        String helloWorld = completableFutureHelloWorld.helloWorld_3_async_calls();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);

    }

    @Test
    void helloWorld_4_async_calls() {
        String helloWorld = completableFutureHelloWorld.helloWorld_4_async_calls();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! MY NAME IS LEO!", helloWorld);

    }

    @Test
    void helloWorld_async_calls_anyOf() {
        String helloWorld = completableFutureHelloWorld.anyOf();

        assertEquals("Hello World", helloWorld);

    }

    @Test
    void helloWorld_thenCompose() {
        startTimer();
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld_thenCompose();

        completableFuture.thenAccept(s -> {
            assertEquals("hello world!", s);
        })
        .join();
        timeTaken();
    }
}