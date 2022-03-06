package com.leonardobatistacarias.service;

import com.leonardobatistacarias.domain.checkout.Cart;
import com.leonardobatistacarias.domain.checkout.CheckoutResponse;
import com.leonardobatistacarias.domain.checkout.CheckoutStatus;
import com.leonardobatistacarias.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void checkout() {

        Cart cart = DataSet.createCart(6);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }

    @Test
    void modify_parallelism() {

        Cart cart = DataSet.createCart(100);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
        //assertTrue(checkoutResponse.getFinalRate() > 0);
    }

}