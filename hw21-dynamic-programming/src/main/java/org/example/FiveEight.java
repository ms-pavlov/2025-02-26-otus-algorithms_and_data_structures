package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class FiveEight {

    public static long countValidNumbers(int n) {
        // Базовый случай
        if (n == 1) return 2;
        if (n == 2) return 4;

        // Массив для хранения промежуточных результатов
        int[][] dp = new int[n + 1][4];

        // Инициализация базовых случаев

        Arrays.fill(dp[2], 1);

        // Заполнение массива с учетом условия
        for (int i = 3; i <= n; i++) {
            // Для каждой позиции считаем количество вариантов
            dp[i][0] = dp[i - 1][2];
            dp[i][1] = dp[i - 1][0] + dp[i - 1][2];
            dp[i][2] = dp[i - 1][1] + dp[i - 1][3];
            dp[i][3] = dp[i - 1][1];
        }

        return Arrays.stream(dp[n]).sum();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем входную строку
        System.out.print("Введите длину числа N: ");
        int input = scanner.nextInt();
        // Выводим результат
        System.out.println("Результат: " + countValidNumbers(input));
    }
}
