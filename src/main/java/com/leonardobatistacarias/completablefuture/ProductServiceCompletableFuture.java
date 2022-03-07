package com.leonardobatistacarias.completablefuture;

import com.leonardobatistacarias.domain.Product;
import com.leonardobatistacarias.domain.ProductInfo;
import com.leonardobatistacarias.domain.Review;
import com.leonardobatistacarias.service.ProductInfoService;
import com.leonardobatistacarias.service.ReviewService;

import java.util.concurrent.CompletableFuture;

import static com.leonardobatistacarias.util.CommonUtil.stopWatch;
import static com.leonardobatistacarias.util.LoggerUtil.log;

public class ProductServiceCompletableFuture {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> cfProductInfo = CompletableFuture
                .supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> cfReview = CompletableFuture
                .supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = cfProductInfo
                .thenCombine(cfReview, (productInfo, reivew) -> new Product(productId, productInfo,reivew))
                .join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

    public CompletableFuture<Product> retrieveProductDetails_approach2(String productId) {
        CompletableFuture<ProductInfo> cfProductInfo = CompletableFuture
                .supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> cfReview = CompletableFuture
                .supplyAsync(() -> reviewService.retrieveReviews(productId));

        return cfProductInfo
                .thenCombine(cfReview, (productInfo, reivew) -> new Product(productId, productInfo,reivew));
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceCompletableFuture productService = new ProductServiceCompletableFuture(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
