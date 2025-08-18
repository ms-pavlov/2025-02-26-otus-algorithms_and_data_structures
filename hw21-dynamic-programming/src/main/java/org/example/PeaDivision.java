package org.example;

import java.util.Scanner;

public class PeaDivision {

    // Метод для нахождения НОД (наибольшего общего делителя)
    private static int gcd(int a, int b) {
        int multiplier = 1;
        while (b != a) {
            if ((b & 1) == 0) {
                if ((a & 1) == 0) {
                    b = b >> 1;
                    a = a >> 1;
                    multiplier = multiplier << 1;
                } else {
                    b = b >> 1;
                }
            } else {
                if ((a & 1) == 0) {
                    a = a >> 1;
                } else {
                    if (b > a) {
                        b = (b - a) >> 1;
                    } else {
                        a = (a - b) >> 1;
                    }
                }
            }

        }
        return multiplier * a;
    }

    // Метод для сокращения дроби
    private static String simplifyFraction(int numerator, int denominator) {
        int commonDivisor = gcd(numerator, denominator);
        int simplifiedNum = numerator / commonDivisor;
        int simplifiedDen = denominator / commonDivisor;
        return simplifiedNum + "/" + simplifiedDen;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем входную строку
        System.out.print("Введите данные в формате a/b+c/d: ");
        String input = scanner.nextLine();

        // Разбиваем строку на части
        String[] parts = input.split("\\+");
        String[] fraction1 = parts[0].split("/");
        String[] fraction2 = parts[1].split("/");

        // Преобразуем строки в числа
        int a = Integer.parseInt(fraction1[0]);
        int b = Integer.parseInt(fraction1[1]);
        int c = Integer.parseInt(fraction2[0]);
        int d = Integer.parseInt(fraction2[1]);

        // Складываем дроби
        int numerator = a * d + c * b;
        int denominator = b * d;

        // Выводим результат
        System.out.println("Результат: " + simplifyFraction(numerator, denominator));
    }
}