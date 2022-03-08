package com.leonardobatistacarias.completablefuture;

import com.leonardobatistacarias.domain.Product;
import com.leonardobatistacarias.service.InventoryService;
import com.leonardobatistacarias.service.ProductInfoService;
import com.leonardobatistacarias.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletablaFutureExceptionTest {

    @Mock
    private ProductInfoService pisMock;
    @Mock
    private ReviewService rsMock;
    @Mock
    private InventoryService isMock;

    @InjectMocks
    ProductServiceCompletableFuture pscf;

    @Test
    void retrieveProductDetailsWithInventory_approach2() {
        String productId = "ABC123";
        when(pisMock.retrieveProductInfo(any())).thenCallRealMethod();
        when(rsMock.retrieveReviews(any())).thenThrow(new RuntimeException("Exception Occurred"));
        when(isMock.addInventory(any())).thenCallRealMethod();

        Product product = pscf.retrieveProductDetailsWithInventory_approach2(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertNotNull(product.getReview());
        assertEquals(0,product.getReview().getNoOfReviews());
    }

    @Test
    void retrieveProductDetailsWithInventory_productInfoServiceError() {
        String productId = "ABC123";
        when(pisMock.retrieveProductInfo(any())).thenThrow(new RuntimeException("Exception Occurred"));
        when(rsMock.retrieveReviews(any())).thenCallRealMethod();

        Assertions.assertThrows(RuntimeException.class, ()->pscf.retrieveProductDetailsWithInventory_approach2(productId));
    }
}