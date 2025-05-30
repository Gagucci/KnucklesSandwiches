package com.KnuckleSandwiches.Services;

import com.KnuckleSandwiches.FoodServices.Items.Sandwich;
import com.KnuckleSandwiches.FoodServices.Items.SignatureSandwich;
import com.KnuckleSandwiches.FoodServices.Order;
import com.KnuckleSandwiches.Interfaces.Priceable;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptServices {

    private static final String receipts = "receipts";
    private static final DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private static final DateTimeFormatter receiptDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

    private String generateReceiptFilename(LocalDateTime orderDate) {
        return orderDate.format(fileNameFormatter) + ".txt";
    }

    public <T extends Priceable> String generateReceipt(Order<T> order) {
        createReceiptsDirectory();
        String filename = generateReceiptFilename(order.getOrderDate());
        File receiptFile = new File(receipts, filename);

        try (FileOutputStream fos = new FileOutputStream(receiptFile);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter writer = new BufferedWriter(osw)) {

            writer.write(generateReceiptHeader(order.getOrderDate()));
            writer.newLine();

            writer.write(generateItemsSection(order.getItems()));
            writer.newLine();

            writer.write(generateReceiptFooter(order.calculateTotal()));
            writer.newLine();

            fos.getFD().sync();
            writer.flush();

            return receiptFile.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends Priceable> void displayReceipt(Order<T> order) {
        System.out.println(generateReceiptHeader(order.getOrderDate()));
        System.out.println(generateItemsSection(order.getItems()));
        System.out.println(generateReceiptFooter(order.calculateTotal()));
    }

    private void createReceiptsDirectory() {
        File dir = new File(receipts);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private String generateReceiptHeader(LocalDateTime orderDate) {
        return String.format(
                "========================================================================================\n" +
                        "                                  Knuckle's Sandwiches                                  \n" +
                        "                                     Order Receipt                                      \n" +
                        "----------------------------------------------------------------------------------------\n" +
                        "                          Order Date: %s                                                \n" +
                        "----------------------------------------------------------------------------------------",
                orderDate.format(receiptDateFormatter)
        );
    }

    private <T extends Priceable> String generateItemsSection(List<T> items) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "                                        ITEMS                                           \n" +
                        "----------------------------------------------------------------------------------------\n"
        );

        if (items.isEmpty()) {
            sb.append("    No items in order\n");
        } else {
            items.forEach(item -> {
                if (item instanceof Sandwich) {
                    Sandwich sandwich = (Sandwich) item;
                    String sandwichTitle = (sandwich instanceof SignatureSandwich)
                            ? ((SignatureSandwich) sandwich).getSignatureName()
                            : "Custom Sandwich";

                    // Format sandwich title and price
                    sb.append(String.format("    %-60s $%8.2f\n", sandwichTitle, sandwich.calculatePrice()));

                    // Add toppings
                    if (sandwich.getToppings() != null) {
                        sandwich.getToppings().forEach(topping ->
                                sb.append(String.format("        %-56s%s\n",
                                        topping.getName(),
                                        topping.isExtra() ? "(Extra)" : ""))
                        );
                    }
                } else {
                    // Format other items
                    sb.append(String.format("    %-60s $%8.2f\n", item.toString(), item.calculatePrice()));
                }
            });
        }

        sb.append("----------------------------------------------------------------------------------------");
        return sb.toString();
    }

    private String generateReceiptFooter(double total) {
        double tax = total * 0.07;
        return String.format(
                " SUBTOTAL:                     %48s$%8.2f\n" +
                        " TAX (7%%):                     %48s$%8.2f\n" +
                        " TOTAL:                        %48s$%8.2f\n" +
                        "----------------------------------------------------------------------------------------\n" +
                        "                          Thank you for choosing Knuckles!\n" +
                        "                          We hope to see you again soon!\n" +
                        "========================================================================================",
                "", total,
                "", tax,
                "", total + tax
        );
    }
}