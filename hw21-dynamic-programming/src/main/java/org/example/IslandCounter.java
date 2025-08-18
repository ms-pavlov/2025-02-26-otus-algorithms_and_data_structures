package org.example;

import java.util.Scanner;

public class IslandCounter {
    // Метод для обхода острова и "пометки" его
    private static void markIsland(int[][] matrix, int row, int col) {
        // Проверяем границы матрицы
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix.length) {
            return;
        }

        // Если текущая клетка не является частью острова
        if (matrix[row][col] == 0) {
            return;
        }

        // Помечаем клетку как посещенную
        matrix[row][col] = 0;

        // Рекурсивно обрабатываем соседние клетки
        markIsland(matrix, row + 1, col);  // вниз
        markIsland(matrix, row - 1, col);  // вверх
        markIsland(matrix, row, col + 1);  // вправо
        markIsland(matrix, row, col - 1);  // влево
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем размер матрицы
        int n = scanner.nextInt();

        // Создаем и заполняем матрицу
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int islandCount = 0;

        // Ищем острова
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    islandCount++;
                    markIsland(matrix, i, j);
                }
            }
        }

        System.out.println(islandCount);
    }
}