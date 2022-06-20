package org.patsimas.e_commerce.strategies;

public class DiscountStrategy implements PromotionStrategy {

    private double totalPrice;

    public DiscountStrategy() {
    }

    public DiscountStrategy(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public double totalPrice() {

        if (totalPrice > 75)
            return totalPrice - (totalPrice * 0.1);

        return totalPrice;
    }
}
