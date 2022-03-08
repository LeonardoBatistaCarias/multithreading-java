package com.leonardobatistacarias.apiclient;

import com.leonardobatistacarias.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoviesClientTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();

    MoviesClient moviesClient = new MoviesClient(webClient);

    @BeforeEach
    void resetStopWatch() {
        CommonUtil.stopWatchReset();
    }

    @RepeatedTest(10)
    void retrieveMovie() {
        CommonUtil.startTimer();
        var movieInfoId = 1L;

        var movie = moviesClient.retrieveMovie(movieInfoId);

        System.out.println("movie: " + movie);

        CommonUtil.timeTaken();

        assert movie != null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @RepeatedTest(10)
    void retrieveMovie_CF() {
        CommonUtil.startTimer();
        var movieInfoId = 1L;

        var movie = moviesClient.retrieveMovie_CF(movieInfoId).join();

        System.out.println("movie: " + movie);

        CommonUtil.timeTaken();

        assert movie != null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @RepeatedTest(10)
    void retrieveMovies() {
        CommonUtil.startTimer();
        var movieInfoIds = List.of(1L, 2L, 3L, 4L);

        var movies = moviesClient.retrieveMovies(movieInfoIds);

        System.out.println("movie: " + movies);

        CommonUtil.timeTaken();

        assert movies != null;
        assert movies.size() == 4;
    }

    @RepeatedTest(10)
    void retrieveMovies_CF() {
        CommonUtil.startTimer();
        var movieInfoIds = List.of(1L, 2L, 3L, 4L);

        var movies = moviesClient.retrieveMovies_CF(movieInfoIds);

        System.out.println("movie: " + movies);

        CommonUtil.timeTaken();

        assert movies != null;
        assert movies.size() == 4;
    }
}