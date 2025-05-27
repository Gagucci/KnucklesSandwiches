package com.KnuckleSandwiches.FoodServices.Toppings;

import java.util.List;
import java.util.Map;

public class ToppingCategories {

    public static final Map<String, List<String>> premiumToppings = Map.of(
            "Meats", List.of("40 days dry aged 72 oz a5 wagyu tomahawk steak", "steak", "bacon", "pastrami", "roast beef", "beef patty", "al pastor", "turkey", "bbq pork", "salami", "smoked salmon", "prosciutto", "chicken breast", "capicola", "pepperoni", "pulled pork", "beef brisket", "meatballs", "falafel"),
            "Cheeses", List.of("cheddar", "swiss", "provolone", "pepper jack", "mozzarella", "burrata", "feta", "raclette", "brie", "cream cheese", "queso fresco"),
            "Specials", List.of("guacamole", "avocado", "caramelized onions", "sunny side up egg", "fried egg", "foie gras")
    );

    public static final Map<String, List<String>> regularToppings = Map.of(
            "Vegetables", List.of("lettuce", "tomato", "red onion", "cucumber", "pickles", "italian white truffle", "pickled jalapenos", "pickled banana peppers", "spinach", "arugula", "micro greens", "pickled carrot and daikon", "roasted bell peppers"),
            "Sauces", List.of( "kickin avocado lime ranch", "southpaw sriracha ranch", "roundhouse ranch", "slap ya mama sauce hot sauce", "head kick hot honey", "black belt bbq sauce","black garlic aioli", "chipotle aioli", "mayo", "kewpie mayo", "honey mustard", "dijon mustard", "red wine vinegar", "olive oil", "blast double balsamic vinaigrette", "liver shot pâté", "tahini sauce"),
            "Sides", List.of("au jus", "tomato soup", "cup of beans", "siberian sturgeon caviar", "52 oysters")
    );
}
