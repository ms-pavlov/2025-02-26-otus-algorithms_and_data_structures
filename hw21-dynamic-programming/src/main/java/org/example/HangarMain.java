package org.example;

import java.util.Scanner;

public class HangarMain {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите размеры поля");
        // Считываем размеры матрицы
        String[] dimensions = scanner.nextLine().split(" ");
        int N = Integer.parseInt(dimensions[0]);
        int M = Integer.parseInt(dimensions[1]);
        System.out.println("Введите количество построек");
        // Считываем количество построек
        int T = Integer.parseInt(scanner.nextLine());

        Hangar hangar = new Hangar(N, M, T);
        System.out.println(hangar);
        System.out.println("Площадь = " + hangar.getMaxArea());
    }

}
