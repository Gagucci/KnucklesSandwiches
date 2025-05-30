package com.KnuckleSandwiches.FoodServices.Items;

import java.util.List;

public class Chip extends MenuItem {

    private final String type;
    private static final double basePrice = 1.50;
    public static final List<String> validTypes = List.of("Potato", "BBQ", "Salt & Vinegar", "Veggie", "Tortilla", "Cheese", "Sour Cream & Onion", "Kettle Cooked", "Baked", "Pita Chips");

    public String getType() { return type; }

    public Chip(double price, String type) {
        if (!validTypes.contains(type)) {
            throw new IllegalArgumentException("Invalid chip type: " + type);
        }
        this.type = type;
    }


    public static List<String> getValidTypes() {
        return validTypes;
    }

    public static double getBasePrice() {
        return basePrice;
    }


    @Override
    public String toString() {
        return type + " Chips";
    }


    @Override
    public double calculatePrice() {
        return basePrice;
    }

}
