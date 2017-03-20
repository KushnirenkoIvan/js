package com.mc.kushnirenko.task2.animal;

import com.mc.kushnirenko.task2.food.Plant;


public class Cow extends GrassEatingAnimal {

    public Cow(String name) {
        super(name);
    }

    @Override
    public void eat(Plant food) {
        System.out.println("Cow eats: " + food.getName());
    }
}
