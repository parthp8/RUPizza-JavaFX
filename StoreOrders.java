package com.pizza;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the store's orders.
 * @author Jeeva Ramasamy, Parth Patel
 */
public class StoreOrders {
    private static ArrayList<Order> orders = new ArrayList<>();
    private static int nextOrderNumber = 1;

    /**
     * Adds an order to the store's orders
     * @param order
     */
    public static void addOrder(Order order) {
        orders.add(order);
        nextOrderNumber++;
    }

    /**
     * Returns list of orders
     * @return orders
     */
    public static ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Returns the next order number
     * @return nextOrderNumber
     */
    public static int getNextOrderNum() {
        return nextOrderNumber;
    }

    /**
     * Exports the orders to a file
     */
    public static void export(File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Order order : orders) {
                writer.write(order.toString() + "\n");
            }
        } catch (IOException e) {
            throw new IOException(
                    "Error exporting orders to file", e);
        }
    }
}
