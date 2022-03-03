package com.leonardobatistacarias.service;

import com.leonardobatistacarias.domain.Review;

import static com.leonardobatistacarias.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
