package com.KnuckleSandwiches.FoodServices.Items;

import com.KnuckleSandwiches.FoodServices.Toppings.Topping;

import java.util.List;

public class SignatureSandwich extends Sandwich{
    public SignatureSandwich(double price, String breadType, String size, boolean isToasted, List<Topping> toppings) {
        super(price, breadType, size, isToasted, toppings);
    }
}
