package com.KnuckleSandwiches.FoodServices.Items;

import com.KnuckleSandwiches.FoodServices.Toppings.Topping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sandwich extends MenuItem {

    private String BreadType;
    private String Size;
    private boolean isToasted;
    private List<Topping> toppings = new ArrayList<>();

    private static final List<String> breadTypes = List.of("White", "Wheat", "Sourdough", "Rye");
    private static final List<String> sizes = List.of("4", "8", "12");

    public Sandwich(String breadType, String size) {
        if (!breadTypes.contains(breadType.toLowerCase())) {
            throw new IllegalArgumentException("Invalid bread type. Choose from: " + breadTypes);
        }
        if (!sizes.contains(size)) {
            throw new IllegalArgumentException("Invalid size. Choose from: " + sizes);
        }
        this.BreadType = breadType;
        this.Size = size;
        this.isToasted = false; // Default value
        this.toppings = new ArrayList<>();
    }

    public String getBreadType() {
        return BreadType;
    }

    public String getSize() {
        return Size;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    public void setIsToasted(boolean isToasted) {
        this.isToasted = isToasted;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    private double getBaseBreadPrice() {
        return switch (Size) {
            case "4\"" -> 5.50;
            case "8\"" -> 7.00;
            case "12\"" -> 8.50;
            default -> 0;
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


}
