package com.pizza;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BuildYourOwnTest {

    /**
     * Test case #1
     * Tests price of a small pizza with 3 toppings (minimum) and
     * no extra sauce or cheese
     */
    @org.junit.Test
    public void testPriceSmall() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.SAUSAGE);
        toppingList.add(Topping.PINEAPPLE);
        toppingList.add(Topping.PEPPERONI);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.TOMATO);
        assertEquals(pizza.price(), 8.99, 0);
    }

    /**
     * Test case #2
     * Tests price of a medium pizza with 3 toppings (minimum) and
     * no extra sauce or cheese
     */
    @org.junit.Test
    public void testPriceMedium() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.CRAB_MEATS);
        toppingList.add(Topping.BEEF);
        toppingList.add(Topping.BLACK_OLIVE);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.MEDIUM);
        pizza.setSauce(Sauce.TOMATO);
        assertEquals(pizza.price(), 10.99, 0);
    }

    /**
     * Test case #3
     * Tests price of a large pizza with 3 toppings (minimum) and
     * no extra sauce or cheese
     */
    @org.junit.Test
    public void testPriceLarge() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.MUSHROOM);
        toppingList.add(Topping.ONION);
        toppingList.add(Topping.GREEN_PEPPER);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.LARGE);
        pizza.setSauce(Sauce.TOMATO);
        assertEquals(pizza.price(), 12.99, 0);
    }

    /**
     * Test case #4
     * Tests price of an extra sauce pizza (small and 3 toppings)
     */
    @org.junit.Test
    public void testPriceExtraSauce() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.MUSHROOM);
        toppingList.add(Topping.PINEAPPLE);
        toppingList.add(Topping.CHICKEN);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.TOMATO);
        pizza.setExtraSauce();
        assertEquals(pizza.price(), 9.99, 0);
    }

    /**
     * Test case #5
     * Tests price of an extra cheese pizza (small and 3 toppings)
     */
    @org.junit.Test
    public void testPriceExtraCheese() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.BEEF);
        toppingList.add(Topping.ONION);
        toppingList.add(Topping.BLACK_OLIVE);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.TOMATO);
        pizza.setExtraCheese();
        assertEquals(pizza.price(), 9.99, 0);
    }

    /**
     * Test case #6
     * Tests price of a small pizza with 4 toppings and
     * no extra sauce or cheese
     */
    @org.junit.Test
    public void testPrice4Toppings() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.SHRIMP);
        toppingList.add(Topping.HAM);
        toppingList.add(Topping.SQUID);
        toppingList.add(Topping.GREEN_PEPPER);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.TOMATO);
        assertEquals(pizza.price(), 10.48, 0);
    }

    /**
     * Test case #7
     * Tests price of a small pizza with 5 toppings and
     * no extra sauce or cheese
     */
    @org.junit.Test
    public void testPrice5Toppings() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.ONION);
        toppingList.add(Topping.BEEF);
        toppingList.add(Topping.CRAB_MEATS);
        toppingList.add(Topping.SAUSAGE);
        toppingList.add(Topping.MUSHROOM);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.TOMATO);
        assertEquals(pizza.price(), 11.97, 0);
    }

    /**
     * Test case #8
     * Tests price of a small pizza with 6 toppings and
     * no extra sauce or cheese
     */
    @org.junit.Test
    public void testPrice6Toppings() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.SQUID);
        toppingList.add(Topping.CHICKEN);
        toppingList.add(Topping.CRAB_MEATS);
        toppingList.add(Topping.PINEAPPLE);
        toppingList.add(Topping.MUSHROOM);
        toppingList.add(Topping.GREEN_PEPPER);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.TOMATO);
        assertEquals(pizza.price(), 13.46, 0);
    }

    /**
     * Test case #9
     * Tests price of a small pizza with 7 toppings (maximum) and
     * no extra sauce or cheese
     */
    @org.junit.Test
    public void testPrice7Toppings() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        toppingList.add(Topping.ONION);
        toppingList.add(Topping.HAM);
        toppingList.add(Topping.SQUID);
        toppingList.add(Topping.SAUSAGE);
        toppingList.add(Topping.MUSHROOM);
        toppingList.add(Topping.BLACK_OLIVE);
        toppingList.add(Topping.GREEN_PEPPER);
        pizza.setToppings(toppingList);
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.TOMATO);
        assertEquals(pizza.price(), 14.95, 0);
    }
}