package ru.geekbrains.lesson_3;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Ваша задача угадать число:");
        for (int i = 10; i <= 30; i+=10) playerLevel(i);
        System.out.println("Вы выиграли!!!");
        scanner.close();
    }

    private static void playerLevel(int range) {
        int number = (int) (Math.random() * range);
        while (true) {
            System.out.println("Угадайте чилсо от 0 до " + range);
            int new_numb = scanner.nextInt();
            if (number == new_numb) {
                System.out.println("Ай красавчик!");
                break;
            } else if (new_numb < number) {
                System.out.println("Загаданное число больше");
            } else {
                System.out.println("Загаданное число меньше");
            }
        }
    }
}

