package com.pizza;

/**
 * PizzaMaker class that uses factory pattern to
 * create an instance of subclasses based on the chosen pizza type
 * @author Jeeva Ramasamy, Parth Patel
 */
public class PizzaMaker {

    /**
     * create an instance of subclasses based on the chosen pizza type
     * @param pizzaType
     * @return pizza
     */
    public static Pizza createPizza(String pizzaType) {
        Pizza pizza;
        if (pizzaType.equals(Specialty.DELUXE.toString())) {
            pizza = new Deluxe();
            pizza.setToppings(Specialty.DELUXE.getToppings());
            pizza.setSauce(Specialty.DELUXE.getSauce());
        }
        else if (pizzaType.equals(Specialty.SUPREME.toString())) {
            pizza = new Supreme();
            pizza.setToppings(Specialty.SUPREME.getToppings());
            pizza.setSauce(Specialty.SUPREME.getSauce());
        }
        else if (pizzaType.equals(Specialty.MEATZZA.toString())) {
            pizza = new Meatzza();
            pizza.setToppings(Specialty.MEATZZA.getToppings());
            pizza.setSauce(Specialty.MEATZZA.getSauce());
        }
        else if (pizzaType.equals(Specialty.SEAFOOD.toString())) {
            pizza = new Seafood();
            pizza.setToppings(Specialty.SEAFOOD.getToppings());
            pizza.setSauce(Specialty.SEAFOOD.getSauce());
        }
        else if (pizzaType.equals(Specialty.PEPPERONI.toString())) {
            pizza = new Pepperoni();
            pizza.setToppings(Specialty.PEPPERONI.getToppings());
            pizza.setSauce(Specialty.PEPPERONI.getSauce());
        }
        else {
            pizza = new BuildYourOwn();
        }
        return pizza;
    }
}
