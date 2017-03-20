package com.mc.kushnirenko.task2.animal;

import com.mc.kushnirenko.task2.food.Plant;


public abstract class GrassEatingAnimal extends Animal{

    public GrassEatingAnimal(String name) {
        super(name);
    }

    public abstract void eat(Plant food);

}
