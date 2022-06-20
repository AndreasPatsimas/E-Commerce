package org.patsimas.e_commerce;

import org.patsimas.e_commerce.services.Checkout;
import org.patsimas.e_commerce.services.CheckoutService;
import org.patsimas.e_commerce.strategies.DiscountStrategy;
import org.patsimas.e_commerce.strategies.PromotionStrategy;
import org.patsimas.e_commerce.strategies.WaterBottleStrategy;

import java.util.List;

import static org.patsimas.e_commerce.utils.NumericUtils.roundNumberToCentimeters;

public class Main {

    public static void main(String[] args) {

        List<PromotionStrategy> promotionRules = List.of(new WaterBottleStrategy(), new DiscountStrategy());
        CheckoutService checkout = new Checkout(promotionRules);

        List<String> items1 = List.of("001", "001", "002", "003");
        checkout.scan(items1);
        System.out.println("Items: " + items1);
        System.out.println("Total Price: £" + roundNumberToCentimeters(checkout.total()) + "\n");

        List<String> items2 = List.of("001", "001", "001");
        checkout.scan(items2);
        System.out.println("Items: " + items2);
        System.out.println("Total Price: £" + roundNumberToCentimeters(checkout.total()) + "\n");

        List<String> items3 = List.of("002", "002", "003");
        checkout.scan(items3);
        System.out.println("Items: " + items3);
        System.out.println("Total Price: £" + roundNumberToCentimeters(checkout.total()) + "\n");
    }
}
