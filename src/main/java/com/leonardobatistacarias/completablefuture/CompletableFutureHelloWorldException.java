package com.leonardobatistacarias.completablefuture;

import com.leonardobatistacarias.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.leonardobatistacarias.util.CommonUtil.delay;
import static com.leonardobatistacarias.util.CommonUtil.startTimer;
import static com.leonardobatistacarias.util.CommonUtil.timeTaken;
import static com.leonardobatistacarias.util.LoggerUtil.log;

public class CompletableFutureHelloWorldException {
    private HelloWorldService hws;

    public CompletableFutureHelloWorldException(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloWorld_3_async_calls_handle() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync((() -> hws.hello()));
        CompletableFuture<String> world = CompletableFuture.supplyAsync((() -> hws.world()));
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .handle((res, e) -> {
                    if (e != null) {
                        log("Exception is: " + e.getMessage());
                        return "";
                    } else {
                        return res;
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .handle((res, e) -> {
                    if (e != null) {
                        log("Exception after world is: " + e.getMessage());
                        return "";
                    } else {
                        return res;
                    }
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return helloWorld;
    }

    public String helloWorld_3_async_calls_exceptionally() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync((() -> hws.hello()));
        CompletableFuture<String> world = CompletableFuture.supplyAsync((() -> hws.world()));
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .exceptionally(e -> {
                    log("Exception is: " + e.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w)
                .exceptionally(e -> {
                    log("Exception after world is: " + e.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return helloWorld;
    }

    public String helloWorld_3_async_calls_whenhandle() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync((() -> hws.hello()));
        CompletableFuture<String> world = CompletableFuture.supplyAsync((() -> hws.world()));
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .whenComplete((res, e) -> {
                    if (e != null) {
                        log("Exception is: " + e.getMessage());
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .whenComplete((res, e) -> {
                    if (e != null) {
                        log("Exception after world is: " + e.getMessage());
                    }
                })
                .exceptionally(e -> {
                    log("Exception after thenCombine is: " + e.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return helloWorld;
    }

}
