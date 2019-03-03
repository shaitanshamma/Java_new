package ru.geekbrains.lesson_3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ваша задача угадать число:");
        int range = 100;
        int number = (int)(Math.random() * range);
        while (true){
            System.out.println("Угадайте чилсо от 0 до " + range);
            int new_numb = scanner.nextInt();
            if (number == new_numb){
                System.out.println("Ай красавчик!");
                break;
            } else if (new_numb < number){
                System.out.println("Загаданное число больше");
            } else {
                System.out.println("Загаданное число меньше");
            }
        } scanner.close();
    }
}
