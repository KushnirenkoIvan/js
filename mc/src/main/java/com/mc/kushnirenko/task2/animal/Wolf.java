package com.mc.kushnirenko.task2.animal;


public class Wolf extends PredatorAnimal {

    public Wolf(String name) {
        super(name);
    }

    @Override
    public void eat(GrassEatingAnimal food) {
        System.out.println("Wolf eats " + food.getName());
    }
}
