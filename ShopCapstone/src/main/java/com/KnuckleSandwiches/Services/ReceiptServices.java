package com.KnuckleSandwiches.Services;

import com.KnuckleSandwiches.FoodServices.Items.Sandwich;
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


    /**
     * Generates a receipt file using BufferedWriter
     *
     * @param order The order to generate receipt for
     * @return The path to the generated receipt file
     */


public <T extends Priceable> String generateReceipt(Order<T> order) {
     createReceiptsDirectory();
     String filename = generateReceiptFilename(order.getOrderDate());
     File receiptFile = new File(receipts, filename);

     try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
         // Write receipt header
         writer.write(generateReceiptHeader(order.getOrderDate()));
         writer.newLine();

         // Write order items
         writer.write(generateItemsSection(order.getItems()));
         writer.newLine();

         // Write receipt footer
         writer.write(generateReceiptFooter(order.calculateTotal()));
         writer.newLine();

         return receiptFile.getAbsolutePath();
     } catch (IOException e) {
         e.printStackTrace();
         return null; // Return null or handle the error as needed
     }
 }


    /**
     * Displays the receipt to console (for testing/debugging)
     * @param order The order to display
     */
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


    private String generateReceiptFilename(LocalDateTime orderDate) {
        return orderDate.format(fileNameFormatter) + ".txt";
    }


    private String generateReceiptHeader(LocalDateTime orderDate) {
        return String.format(
                "========================================\n" +
                        "            DELI-cious Sandwich Shop    \n" +
                        "            Order Receipt               \n" +
                        "----------------------------------------\n" +
                        "  Order Date: %s\n" +
                        "----------------------------------------",
                orderDate.format(receiptDateFormatter)
        );
    }


    private <T extends Priceable> String generateItemsSection(List<T> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("  ITEMS:\n");

        if (items.isEmpty()) {
            sb.append("    No items in order\n");
        } else {
            items.forEach(item -> {
                // Add item line
                sb.append(String.format("    %-30s $%.2f\n",
                        item.toString(), item.calculatePrice()));

                // Add topping details if this is a sandwich
                if (item instanceof Sandwich) {
                    Sandwich sandwich = (Sandwich) item;
                    sandwich.getToppings().forEach(topping -> {
                        sb.append(String.format("      %s%s\n",
                                topping.getName(),
                                topping.isExtra() ? " (Extra)" : ""));
                    });
                }
            });
        }

        sb.append("----------------------------------------");
        return sb.toString();
    }

    
    private String generateReceiptFooter(double total) {
        return String.format(
                "  SUBTOTAL:                     $%.2f\n" +
                        "  TAX (7%%):                     $%.2f\n" +
                        "  TOTAL:                        $%.2f\n" +
                        "----------------------------------------\n" +
                        "  Thank you for choosing DELI-cious!\n" +
                        "  We hope to see you again soon!\n" +
                        "========================================",
                total,
                total * 0.07, // Assuming 7% tax
                total * 1.07
        );
    }


}