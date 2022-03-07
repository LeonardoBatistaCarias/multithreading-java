package com.leonardobatistacarias.completablefuture;

import com.leonardobatistacarias.domain.Product;
import com.leonardobatistacarias.service.InventoryService;
import com.leonardobatistacarias.service.ProductInfoService;
import com.leonardobatistacarias.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
class ProductServiceCompletableFutureTest {

    private ProductInfoService productInfoService = new ProductInfoService();
    private ReviewService reviewService = new ReviewService();
    InventoryService inventoryService = new InventoryService();

    ProductServiceCompletableFuture productServiceCompletableFuture = new ProductServiceCompletableFuture(productInfoService, reviewService, inventoryService);

    @Test
    void retrieveProductDetails() {

        String productId = "123456789";

        Product product = productServiceCompletableFuture.retrieveProductDetails(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);

    }

    @Test
    void retrieveProductDetails_approach2() {
        String productId = "123456789";
        CompletableFuture<Product> completableFutureProduct= productServiceCompletableFuture.retrieveProductDetails_approach2(productId);

        completableFutureProduct
                .thenAccept((product -> {
                    assertNotNull(product);
                    assertTrue(product.getProductInfo().getProductOptions().size() > 0);
                }))
                .join();
    }

    @Test
    void retrieveProductDetailsWithInventory() {

        String productId = "123456789";

        Product product = productServiceCompletableFuture.retrieveProductDetailsWithInventory(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);

        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });

    }

    @Test
    void retrieveProductDetailsWithInventory_approach2() {

        String productId = "123456789";

        Product product = productServiceCompletableFuture.retrieveProductDetailsWithInventory_approach2(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);

        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });

    }
}