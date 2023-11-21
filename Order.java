package com.pizza;

import java.util.ArrayList;

/**
 * Represents an order of pizzas.
 * @author Jeeva Ramasamy, Parth Patel
 */
public class Order {
    private int orderNumber;
    private double orderTotal;
    private ArrayList<Pizza> pizzas;

    /**
     * Constructor for Order
     * @param orderNumber
     * @param orderTotal
     * @param pizzas
     */
    public Order(int orderNumber, double orderTotal,
                 ArrayList<Pizza> pizzas) {
        this.orderNumber = orderNumber;
        this.orderTotal = orderTotal;
        this.pizzas = pizzas;
    }

    /**
     * Returns the order number.
     * @return orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Returns the order total.
     * @return orderTotal
     */
    public double getOrderTotal() {
        return orderTotal;
    }

    /**
     * Returns the pizzas in the order.
     * @return pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Returns a string representation of the order.
     * @return string
     */
    @Override
    public String toString() {
        String result =  "Order Number: " + orderNumber + "\n";
        result += "Order Total: " + orderTotal + "\nPizzas:";
        for (Pizza pizza : pizzas) {
            result += "\n\t" + pizza.toString();
        }
        return result;
    }
}
