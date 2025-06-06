package com.KnuckleSandwiches.FoodServices.Toppings;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToppingCategories {
    public static final Map<String, List<String>> premiumToppings = Map.of(
            "Meats", List.of("40 days dry aged 72 oz a5 wagyu tomahawk steak", "steak", "bacon", "pastrami", "roast beef", "kandied bacon", "beef patty", "al pastor", "turkey", "bbq pork", "salami", "smoked salmon", "prosciutto", "chicken breast", "capicola", "pepperoni", "pulled pork", "beef brisket", "meatballs", "falafel"),
            "Cheeses", List.of("cheddar", "swiss", "provolone", "pepper jack", "mozzarella", "burrata", "feta", "raclette", "brie", "cream cheese", "queso fresco"),
            "Specials", List.of("guacamole", "avocado", "caramelized onions", "sunny side up egg", "fried egg", "foie gras", "italian white truffle")
    );

    public static final Map<String, List<String>> regularToppings = Map.of(
            "Vegetables", List.of("lettuce", "tomato", "kale", "red onion", "cucumber", "pickles", "pickled jalapenos", "pickled banana peppers", "spinach", "arugula", "micro greens", "pickled carrot and daikon", "roasted bell peppers"),
            "Sauces", List.of("kickin avocado lime ranch", "southpaw sriracha ranch", "roundhouse ranch", "slap ya mama sauce hot sauce", "head kick hot honey", "black belt bbq sauce", "black garlic aioli", "chipotle aioli", "mayo", "kewpie mayo", "honey mustard", "dijon mustard", "red wine vinegar", "olive oil", "blast double balsamic vinaigrette", "liver shot pâté", "tahini sauce", "italian dressing"),
            "Sides", List.of("au jus", "tomato soup", "cup of beans", "siberian sturgeon caviar", "52 oysters", "coleslaw")
    );

    public static boolean isPremium(String category) {
        return premiumToppings.containsKey(category);
    }

    public static List<String> getAllCategories() {
        return List.of("Meats", "Cheeses", "Specials", "Vegetables", "Sauces", "Sides", "done");
    }

    public static List<String> getToppingsByCategory(String category) {
        if (premiumToppings.containsKey(category)) {
            return premiumToppings.get(category);
        }
        if (regularToppings.containsKey(category)) {
            return regularToppings.get(category);
        }
        throw new IllegalArgumentException("Invalid topping category: " + category);
    }

    public static String getCategoryForTopping(String toppingName) {
        // Check premium toppings first
        for (Map.Entry<String, List<String>> entry : premiumToppings.entrySet()) {
            if (entry.getValue().contains(toppingName)) {
                return entry.getKey();
            }
        }

        // Check regular toppings if not found in premium
        for (Map.Entry<String, List<String>> entry : regularToppings.entrySet()) {
            if (entry.getValue().contains(toppingName)) {
                return entry.getKey();
            }
        }

        throw new IllegalArgumentException("Topping not found: " + toppingName);
    }

    public static List<String> getAllToppingNames() {
        return Stream.concat(
                premiumToppings.values().stream().flatMap(List::stream),
                regularToppings.values().stream().flatMap(List::stream)
        ).collect(Collectors.toList());
    }
}