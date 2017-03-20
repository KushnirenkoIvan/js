package com.mc.kushnirenko.task2.animal;


public abstract class PredatorAnimal extends Animal{

    public PredatorAnimal(String name) {
        super(name);
    }

    public abstract void eat(GrassEatingAnimal food);

}
