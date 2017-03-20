package com.mc.kushnirenko.task2.animal;

import com.mc.kushnirenko.task2.food.Plant;


public class Hare extends GrassEatingAnimal {

    public Hare(String name) {
        super(name);
    }

    @Override
    public void eat(Plant food) {
        System.out.println("Hare eats " + food.getName());
    }
}
