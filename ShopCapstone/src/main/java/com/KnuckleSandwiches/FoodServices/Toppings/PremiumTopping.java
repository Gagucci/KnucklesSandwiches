package com.KnuckleSandwiches.FoodServices.Toppings;

public class PremiumTopping extends Topping {
    private double basePrice;
    private double extraPrice;


    public PremiumTopping(String name, double basePrice, double extraPrice) {
        super(name);
        this.basePrice = basePrice;
        this.extraPrice = extraPrice;
    }


    public double getBasePrice() { return basePrice; }
    public double getExtraPrice() { return extraPrice; }

    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public void setExtraPrice(double extraPrice) { this.extraPrice = extraPrice; }


    @Override
    public double calculateCost(String size) {
        double cost = switch (size) {
            case "4\"" -> basePrice;
            case "8\"" -> basePrice * 2;
            case "12\"" -> basePrice * 3;
            default -> 0;
        };

        // Add extra charge if applicable
        if (isExtra()) {
            switch(size) {
                case "4\"":
                    cost += extraPrice;
                    break;
                case "8\"":
                    cost += extraPrice * 2;
                    break;
                case "12\"":
                    cost += extraPrice * 3;
                    break;
            }
        }

        return cost;
    }

}

