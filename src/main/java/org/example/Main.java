package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int summa;
        int raznost;
        int proizved;
        double chastnoe;

        System.out.println("Введите первое число:");
        int first_number = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int second_number = new Scanner(System.in).nextInt();

        summa = first_number + second_number;
        raznost = first_number - second_number;
        proizved = first_number * second_number;
        chastnoe = (double)first_number / second_number;

        System.out.println("Сумма = " + summa);
        System.out.println("Разность = " + raznost);
        System.out.println("Произведение = " + proizved);
        System.out.println("Частное = " + chastnoe);


    }
}