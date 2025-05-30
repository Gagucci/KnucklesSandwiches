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


    /**
     * Generates a receipt file using BufferedWriter
     *
     * @param order The order to generate receipt for
     * @return The path to the generated receipt file
     */

    private String generateReceiptFilename(LocalDateTime orderDate) {
        return orderDate.format(fileNameFormatter) + ".txt";
    }

    public <T extends Priceable> String generateReceipt(Order<T> order) {
        createReceiptsDirectory();
        String filename = generateReceiptFilename(order.getOrderDate());
        File receiptFile = new File(receipts, filename);

        // Use FileOutputStream to get access to FileDescriptor.sync()
        try (FileOutputStream fos = new FileOutputStream(receiptFile);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter writer = new BufferedWriter(osw)) {

            writer.write(generateReceiptHeader(order.getOrderDate()));
            writer.newLine();

            writer.write(generateItemsSection(order.getItems()));
            writer.newLine();

            writer.write(generateReceiptFooter(order.calculateTotal()));
            writer.newLine();

            // Ensure all data is written to disk
            fos.getFD().sync();
            // Close the writer to flush and release resources
            writer.flush();

            return receiptFile.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Displays the receipt to console (for testing/debugging)
     *
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
            sb.append("%-85s    No items in order\n");
        } else {

            items.forEach(item -> {
                // Add item line
                if (!(item instanceof Sandwich))  {
                    sb.append(String.format("    %-70s $%10.2f\n", item.toString(), item.calculatePrice()));
                }

               // Add topping details if this is a sandwich
                    if (item instanceof Sandwich) {
                        Sandwich sandwich = (Sandwich) item;
                        String sandwichTitle = sandwich.isCustom()
                                ? "Custom Sandwich"
                                : ((SignatureSandwich) sandwich).getSignatureName();

                        sb.append(String.format("    %-70s $%10.2f\n", sandwichTitle, sandwich.calculatePrice()));

                        sandwich.getToppings().forEach(topping -> sb.append(String.format("      %-65s%-15s\n",
                                topping.getName(),
                                topping.isExtra() ? " (Extra)" : "")));
                    }
            });
        }

        sb.append("========================================================================================");
        return sb.toString();
    }


    private String generateReceiptFooter(double total) {
        return String.format(
                        "  SUBTOTAL:                     $%.2f\n" +
                        "  TAX (7%%):                     $%.2f\n" +
                        "  TOTAL:                        $%.2f\n" +
                        "----------------------------------------\n" +
                        "  Thank you for choosing Knuckles!\n" +
                        "  We hope to see you again soon!\n" +
                        "========================================",
                total,
                total * 0.07, // Assuming 7% tax
                total * 1.07
        );
    }


}