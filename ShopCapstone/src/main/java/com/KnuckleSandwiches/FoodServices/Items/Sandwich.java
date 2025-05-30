package com.KnuckleSandwiches.FoodServices.Items;

import com.KnuckleSandwiches.FoodServices.Toppings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sandwich extends MenuItem {

    private String BreadType;
    private String Size;
    private boolean isToasted;
    private List<Topping> toppings = new ArrayList<>();


    public static final List<String> breadTypes = List.of(
            "Dutch Crunch", "Whole Wheat", "Sourdough", "Italian Herb", "Asiago Jalapeno", "Everything Roll", "French Baguette", "Ciabatta", "Pretzel Roll", "Pita Wrap"
    );
    public static final List<String> sizes = List.of(
            "4\"", "8\"", "12\"", "24\""
    );


    public Sandwich(String breadType, String size) {

        if (!breadTypes.contains(breadType)) {
            System.out.println("Invalid bread type: " + breadType + ". Please choose from: " + breadTypes);
            return; // Exit the constructor to allow the user to retry
        }
        if (!sizes.contains(size)) {
            throw new IllegalArgumentException("Invalid size: " + size + ". Choose from: " + sizes);
        }

        this.BreadType = breadType;
        this.Size = size;
        this.isToasted = false; // Default value
        this.toppings = new ArrayList<>();
    }

    public Sandwich() {
    }


    public String getBreadType() { return BreadType; }
    public String getSize() { return Size; }
    public boolean isToasted() { return isToasted; }
    public List<Topping> getToppings() { return new ArrayList<>(toppings); }

    public void setIsToasted(boolean isToasted) { this.isToasted = isToasted; }
    public void addTopping(Topping topping) { toppings.add(topping); }


    private double getBaseBreadPrice() {
        return switch (Size) {
            case "4\"" -> 5.50;
            case "8\"" -> 7.00;
            case "12\"" -> 8.50;
            case "24\"" -> 15.50;
            default -> 0;
        };
    }


    private double getBasePriceForTopping(String category) {
        return switch(category) {
            case "Meats" -> 1.00;    // Base meat price for 4"
            case "Cheeses" -> 0.75;  // Base cheese price for 4"
            case "Specials" -> 1.50; // Base specials price for 4"
            default -> throw new IllegalArgumentException("Unknown premium category: " + category);
        };
    }


    private double getExtraPriceForTopping(String category) {
        return switch(category) {
            case "Meats" -> 0.50;    // Extra meat price for 4"
            case "Cheeses" -> 0.30;  // Extra cheese price for 4"
            case "Specials" -> 0.75; // Extra specials price for 4"
            default -> throw new IllegalArgumentException("Unknown premium category: " + category);
        };
    }


    @Override
    public double calculatePrice() {
        double total = getBaseBreadPrice() + toppings.stream()
                .mapToDouble(t -> t.calculateCost(Size))
                .sum();

        setPrice(total);
        return getPrice();
    }


    public String getToppingsDescription() {
        return toppings.stream()
                .map(t -> t.getName() + (t.isExtra() ? " (Extra)" : ""))
                .collect(Collectors.joining(", "));
    }


    @Override
    public String toString() {
        return String.format("%s %s Sandwich%s with %s",
                Size,
                BreadType,
                isToasted ? " (Toasted)" : "",
                toppings.isEmpty() ? "no toppings" : getToppingsDescription());
    }


    public void addPremiumTopping(String name, boolean isExtra) {
        // Determine which premium category this topping belongs to
        String category = ToppingCategories.premiumToppings.entrySet().stream()
                .filter(entry -> entry.getValue().contains(name)) // Find category containing this topping
                .map(Map.Entry::getKey) // Get the category name
                .findFirst() // Get first matching category
                .orElseThrow(() -> new IllegalArgumentException("Unknown premium topping: " + name));

        // Create the premium topping with appropriate pricing
        PremiumTopping topping = new PremiumTopping(
                name,
                category,
                getBasePriceForTopping(category),
                getExtraPriceForTopping(category)
        );
        topping.setIsExtra(isExtra);
        toppings.add(topping);
    }


    public void addRegularTopping(String name, String category) {
        toppings.add(new RegularTopping(name, category));
    }


}
