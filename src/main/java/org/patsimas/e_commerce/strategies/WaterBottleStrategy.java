package org.patsimas.e_commerce.strategies;

public class WaterBottleStrategy implements PromotionStrategy {

    private long numberOfWaterBottles;
    private double totalPrice;

    public WaterBottleStrategy() {
    }

    public WaterBottleStrategy(long numberOfWaterBottles, double totalPrice) {
        this.numberOfWaterBottles = numberOfWaterBottles;
        this.totalPrice = totalPrice;
    }

    @Override
    public double totalPrice() {

        if (numberOfWaterBottles >= 2)
            return totalPrice - (numberOfWaterBottles * (24.95 - 22.99));

        return totalPrice;
    }
}
