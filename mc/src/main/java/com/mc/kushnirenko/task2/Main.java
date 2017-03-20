package com.mc.kushnirenko.task2;

import com.mc.kushnirenko.task2.animal.Cow;
import com.mc.kushnirenko.task2.animal.Hare;
import com.mc.kushnirenko.task2.animal.Wolf;
import com.mc.kushnirenko.task2.food.Wheat;

import java.lang.reflect.Constructor;


public class Main {

    public static void main(String[] args) {
        Wheat wheat = new Wheat("Wheat");

        Hare hare = new Hare("Hare");
        Cow cow = new Cow("Cow");
        Wolf wolf = new Wolf("Wolf");

        wolf.eat(cow);
        wolf.eat(hare);
//        wolf.eat(wolf); // Compilation error!
//        wolf.eat(wheat); // Compilation error!

        hare.eat(wheat);
//        hare.eat(hare); // Compilation error!
//        hare.eat(cow); // Compilation error!
//        hare.eat(wolf); // Compilation error!

        cow.eat(wheat);
//        cow.eat(hare); // Compilation error!
//        cow.eat(wolf); // Compilation error!
//        cow.eat(cow); // Compilation error!

        Class clazz = cow.getClass();
        Constructor constructor = clazz.getEnclosingConstructor();

        System.out.println(constructor.getName());
    }
}
