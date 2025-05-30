package com.KnuckleSandwiches.FoodServices.Items;

import java.util.List;
import java.util.Map;

public class SignatureSandwich extends Sandwich{

    private final String signatureName;

    public static final Map<String, List<String>> signatureSandwiches = Map.of(
        "Right Hook", List.of("Italian Herb", "Toasted", "salami", "pepperoni", "provolone", "lettuce", "tomato", "red onion", "Italian Dressing"),
        "The Armbar-b-q", List.of("Pretzel Roll", "Toasted", "pulled pork", "black belt bbq sauce", "coleslaw"),
        "Fight Club", List.of("Dutch Crunch", "Toasted", "roast beef", "kickin avocado lime ranch", "arugula", "tomato"),
        "Eat The Rich", List.of("Ciabatta", "Toasted", "40 days dry aged 72 oz a5 wagyu tomahawk steak", "prosciutto", "capicola", "burrata", "raclette", "foie gras", "italian white truffle", "black garlic aioli", "siberian sturgeon caviar", "52 oysters")
        "TKO", List.of("Asiago Jalapeno","Toasted", "turkey", "tomato", "kale", "kandied bacon", "kickin avocado lime ranch", "red onions")
    );
}
