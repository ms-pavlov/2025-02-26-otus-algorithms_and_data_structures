package org.example;

import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

public class Tester implements Test{

    private final int n;
    private final Supplier<Tree> treeSupplier;

    public Tester(int n, Supplier<Tree> treeSupplier) {
        this.n = n;
        this.treeSupplier = treeSupplier;
    }

    @Override
    public void run() {
        Tree randomValueTree = treeSupplier.get();
        Tree incValueTree = treeSupplier.get();
        Random random = new Random();

        long begin;
        long end;

        System.out.println("Случайно заполненное дерева");

        begin = new Date().getTime();
        for(int i = 0; i < n; i++) {
            randomValueTree.insert(random.nextInt());
        }
        end = new Date().getTime();

        System.out.println("Время выполнения для N = "+ n +" вставок " + (end - begin) + "мс");

        begin = new Date().getTime();
        for(int i = 0; i < n /10; i++) {
            randomValueTree.search(random.nextInt());
        }
        end = new Date().getTime();

        System.out.println("Время выполнения для поиска N = "+ n /10 +" чисел " + (end - begin) + "мс");

        begin = new Date().getTime();
        for(int i = 0; i < n /10; i++) {
            randomValueTree.remove(random.nextInt());
        }
        end = new Date().getTime();

        System.out.println("Время выполнения удаления N = "+ n /10 +" чисел " + (end - begin) + "мс");

        System.out.println("Последовательно заполненное дерево");

        begin = new Date().getTime();
        for(int i = 0; i < n; i++) {
            incValueTree.insert(i);
        }
        end = new Date().getTime();

        System.out.println("Время выполнения для N = "+ n +" вставок " + (end - begin) + "мс");

        begin = new Date().getTime();
        for(int i = 0; i < n/10 ; i++) {
            incValueTree.search(random.nextInt(n));
        }
        end = new Date().getTime();

        System.out.println("Время выполнения для поиска N = "+ n/10 +" чисел " + (end - begin) + "мс");

        begin = new Date().getTime();
        for(int i = 0; i < n/10; i++) {
            incValueTree.remove(random.nextInt(n));

        }
        end = new Date().getTime();

        System.out.println("Время выполнения удаления N = "+ n/10 +" чисел " + (end - begin) + "мс");
    }
}
