package com.KnuckleSandwiches.FoodServices.Items;

import com.KnuckleSandwiches.FoodServices.Toppings.Topping;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Sandwich extends MenuItem {

    private String BreadType;
    private String Size;
    private boolean isToasted;
    private List<Topping> toppings = new ArrayList<>();

    public Sandwich(double price, String breadType, String size, boolean isToasted, List<Topping> toppings) {
        super(price);
        BreadType = breadType;
        Size = size;
        this.isToasted = isToasted;
        this.toppings = toppings;
    }

    public String getBreadType() { return BreadType; }
    public String getSize() { return Size; }
    public boolean isToasted() { return isToasted; }
    public List<Topping> getToppings() { return toppings; }

    public void setBreadType(String breadType) { BreadType = breadType; }
    public void setSize(String size) { Size = size; }
    public void setToasted(boolean toasted) { isToasted = toasted; }
    public void setToppings(List<Topping> toppings) {
        if (toppings == null || toppings.isEmpty()) {
            throw new IllegalArgumentException("Toppings cannot be null or empty");
        }
        this.toppings = toppings;
    }


    @Override
    public double getPrice() {
        return super.getPrice();
    }


    @Override
    public double calculatePrice() {
        double totalPrice = getPrice();
        for (Topping topping : toppings) {
            totalPrice += topping.calculatePrice();
        }
        return totalPrice;
    }
}
