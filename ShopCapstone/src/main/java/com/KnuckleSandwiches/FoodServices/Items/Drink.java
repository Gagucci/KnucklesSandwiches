package com.KnuckleSandwiches.FoodServices.Items;

import java.util.List;
import java.util.Map;


public class Drink extends MenuItem {

    private final String size;
    private final String flavor;


    public String getSize() { return size; }
    public String getFlavor() { return flavor; }


    public static final List<String> validSizes = List.of("Small", "Medium", "Large");
    public static final List<String> validFlavors = List.of(
            "Fruit PUNCH", "Orange Juice", "Apple Juice", "Lemonade", "Iced Tea", "Cola", "Sprite", "Root Beer", "Dr.Pepper", "Ginger Ale", "Water"
    );


    private static final Map<String, Double> sizePrices = Map.of(
            "Small", 2.00,
            "Medium", 2.50,
            "Large", 3.00
    );


    public Drink(String size, String flavor) {
        if (!validSizes.contains(size)) {
            throw new IllegalArgumentException("Invalid drink size: " + size);
        }
        if (!validFlavors.contains(flavor)) {
            throw new IllegalArgumentException("Invalid drink flavor: " + flavor);
        }

        this.size = size;
        this.flavor = flavor;
    }


    @Override
    public double calculatePrice() { return sizePrices.get(size); } // Price based on size


    @Override
    public String toString() { return size + " " + flavor + " Drink"; }


    public static List<String> getAvailableSizes() {
        return validSizes;
    }


    public static List<String> getAvailableFlavors() {
        return validFlavors;
    }
}