package com.KnuckleSandwiches.FoodServices.Items;

import java.util.List;
import java.util.Map;

import com.KnuckleSandwiches.FoodServices.Toppings.PremiumTopping;
import com.KnuckleSandwiches.FoodServices.Toppings.RegularTopping;
import com.KnuckleSandwiches.FoodServices.Toppings.Topping;
import com.KnuckleSandwiches.FoodServices.Toppings.ToppingCategories;

public class SignatureSandwich extends Sandwich {
    private final String signatureName;

    public static final Map<String, List<String>> SIGNATURE_SANDWICHES = Map.of(
            "Right Hook", List.of("Italian Herb", "salami", "pepperoni", "provolone", "lettuce", "tomato", "red onion", "Italian Dressing"),
            "The Armbar-b-q", List.of("Pretzel Roll", "pulled pork", "black belt bbq sauce", "coleslaw"),
            "Fight Club", List.of("Dutch Crunch", "roast beef", "kickin avocado lime ranch", "arugula", "tomato"),
            "Eat The Rich", List.of("Ciabatta", "40 days dry aged 72 oz a5 wagyu tomahawk steak", "prosciutto", "capicola", "burrata", "raclette", "foie gras", "italian white truffle", "black garlic aioli", "siberian sturgeon caviar", "52 oysters"),
            "TKO", List.of("Asiago Jalapeno", "turkey", "tomato", "kale", "kandied bacon", "kickin avocado lime ranch", "red onions")
    );

    public SignatureSandwich(String name, String size) {
        super(SIGNATURE_SANDWICHES.get(name).get(0), size);
        this.signatureName = name;
        initializeToppings();
        setIsToasted(true); // All signature sandwiches are toasted
    }

    public String getSignatureName() {
        return signatureName;
    }

    private void initializeToppings() {
        List<String> recipe = SIGNATURE_SANDWICHES.get(signatureName);
        // Start from index 1 (skip bread type)
        for (int i = 1; i < recipe.size(); i++) {
            String toppingName = recipe.get(i);
            String category = ToppingCategories.getCategoryForTopping(toppingName);

            if (ToppingCategories.isPremium(category)) {
                this.addTopping(new PremiumTopping(toppingName, category,
                        getBasePriceForCategory(category),
                        getExtraPriceForCategory(category)));
            } else {
                this.addTopping(new RegularTopping(toppingName, category));
            }
        }
    }

    private double getBasePriceForCategory(String category) {
        return switch(category) {
            case "Meats" -> 1.00;
            case "Cheeses" -> 0.75;
            case "Specials" -> 1.50;
            default -> 0;
        };
    }

    private double getExtraPriceForCategory(String category) {
        return switch(category) {
            case "Meats" -> 0.50;
            case "Cheeses" -> 0.30;
            case "Specials" -> 0.75;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s %s Sandwich with %s",
                getSize(),
                signatureName,
                getBreadType(),
                isToasted() ? "Toasted" : "",
                getToppings().stream()
                        .map(Topping::getName)
                        .collect(java.util.stream.Collectors.joining(", ")));
    }
}