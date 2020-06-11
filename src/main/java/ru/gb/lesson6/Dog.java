package ru.gb.lesson6;

import java.util.Random;

public class Dog extends Animal {
    private static int counter = 0;

    public Dog(String name) {
        super(name, 500, 10, 0.4);

        counter++;
    }

    @Override
    public void info() {
        System.out.print("Пес ");
        super.info();
    }

    public static void printAmount() {
        System.out.println("Создано собак: " + counter);
    }
}
