package com.KnuckleSandwiches.FoodServices;

import com.KnuckleSandwiches.FoodServices.Items.Sandwich;
import com.KnuckleSandwiches.Interfaces.Priceable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Order<T extends Priceable> {

    private final LocalDateTime orderDate;
    private final List<T> items;

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public LocalDateTime getOrderDate() { return orderDate; }
    public List<T> getItems() { return new ArrayList<>(items); }

    /**
     * Adds an item to the order
     *
     * @param item - the item to add
     */
    public void addItem(T item) {
        items.add(item);
    }


    /**
     * Calculates the total price of all items in the order
     *
     * @return total price
     */
    public double calculateTotal() {
        return items.stream()
                .mapToDouble(Priceable::calculatePrice)
                .sum();
    }


    /**
     * Gets a detailed description of all items in the order
     *
     * @return formatted order details
     */
    public String getOrderDetails() {
        String itemsDetails = items.stream()
                .map(item -> {
                    String details = "- " + item.toString() +
                            ": $" + String.format("%.2f", item.calculatePrice());

                    // Special handling for sandwiches
                    if (item instanceof Sandwich) {
                        Sandwich sandwich = (Sandwich) item;
                        details += "\n" + sandwich.getToppings().stream()
                                .map(t -> "  * " + t.getName() +
                                        (t.isExtra() ? " (Extra)" : ""))
                                .collect(Collectors.joining("\n"));
                    }

                    return details;
                })
                .collect(Collectors.joining("\n"));

        return String.format(
                "Order Date: %s\nItems:\n%s\nTotal: $%.2f",
                orderDate,
                itemsDetails,
                calculateTotal()
        );
    }

}