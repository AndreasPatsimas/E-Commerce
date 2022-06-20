package org.patsimas.e_commerce.services;

import org.patsimas.e_commerce.model.Product;
import org.patsimas.e_commerce.repositories.ProductRepository;
import org.patsimas.e_commerce.strategies.DiscountStrategy;
import org.patsimas.e_commerce.strategies.PromotionStrategy;
import org.patsimas.e_commerce.strategies.WaterBottleStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class Checkout implements CheckoutService {

    private final ProductRepository productRepository = ProductRepository.getInstance();

    private List<PromotionStrategy> promotionRules;
    private List<Product> products;
    private boolean hasWaterBottleStrategy;
    private boolean hasDiscountStrategy;

    public Checkout(List<PromotionStrategy> promotionRules) {
        this.promotionRules = promotionRules;
        hasWaterBottleStrategy = promotionRules.stream()
                .anyMatch(promotionRule -> promotionRule instanceof WaterBottleStrategy);
        hasDiscountStrategy = promotionRules.stream()
                .anyMatch(promotionRule -> promotionRule instanceof DiscountStrategy);
    }

    public void scan(List<String> items) {

        products = items.stream()
                .map(productRepository::fetchProductById)
                .collect(Collectors.toList());
    }

    public List<Product> getProducts() {
        return products;
    }

    public double total() {

        double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();

        if (hasWaterBottleStrategy) {

            long numberOfWaterBottles = products.stream().filter(product -> product.getId().equals("001")).count();
            PromotionStrategy promotionStrategy = new WaterBottleStrategy(numberOfWaterBottles, totalPrice);
            totalPrice = promotionStrategy.totalPrice();
        }

        if (hasDiscountStrategy) {

            PromotionStrategy promotionStrategy = new DiscountStrategy(totalPrice);
            totalPrice = promotionStrategy.totalPrice();
        }

        return totalPrice;
    }
}
