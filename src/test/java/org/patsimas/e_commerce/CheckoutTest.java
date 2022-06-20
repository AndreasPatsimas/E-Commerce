package org.patsimas.e_commerce;

import org.junit.Assert;
import org.junit.Test;
import org.patsimas.e_commerce.model.Product;
import org.patsimas.e_commerce.services.Checkout;
import org.patsimas.e_commerce.services.CheckoutService;
import org.patsimas.e_commerce.strategies.DiscountStrategy;
import org.patsimas.e_commerce.strategies.PromotionStrategy;
import org.patsimas.e_commerce.strategies.WaterBottleStrategy;

import java.util.List;
import java.util.stream.Collectors;

import static org.patsimas.e_commerce.utils.NumericUtils.roundNumberToCentimeters;

public class CheckoutTest {

    private final List<PromotionStrategy> promotionRules = List.of(new WaterBottleStrategy(), new DiscountStrategy());
    private final CheckoutService checkout = new Checkout(promotionRules);

    @Test
    public void scanItems() {

        List<String> items = List.of("001", "001", "002", "003");
        checkout.scan(items);

        Double [] expectedProductPrices = {
                24.95,
                24.95,
                65.00,
                3.99
        };

        Double[] actualProductPrices = checkout.getProducts().stream().map(Product::getPrice).collect(Collectors.toList())
                .toArray(Double[] ::new);

        Assert.assertArrayEquals(expectedProductPrices, actualProductPrices);
    }

    @Test
    public void total() {

        List<String> items = List.of("001", "001", "002", "003");
        checkout.scan(items);

        Double actualTotalPrice = roundNumberToCentimeters(checkout.total());
        Double expectedTotalPrice = 103.47;

        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
    }
}
