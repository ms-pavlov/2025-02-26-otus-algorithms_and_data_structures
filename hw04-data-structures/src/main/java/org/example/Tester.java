package org.example;

import org.example.model.IArray;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Tester implements Test{

    private final static Random RANDOM = ThreadLocalRandom.current();

    private final ArrayFactory arrayFactory;
    private final int count;

    public Tester(ArrayFactory arrayFactory, int count) {
        this.arrayFactory = arrayFactory;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.printf("Конструктор: %d, Добавление в конец: %d, Произвольное добавление: %d, " +
                        "Получение элемента: %d, Удаление: %d, Размер: %d%n",
                testConstructor(),
                testAddArray(),
                testIndexAddArray(),
                testGetArray(),
                testRemoveArray(),
                testSizeArray());
    }

    private long testConstructor() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i ++) {
            arrayFactory.create();
        }
        return System.currentTimeMillis() - start;
    }

    private long testAddArray() {
        IArray<Long> data = arrayFactory.create();

        long start = System.currentTimeMillis();

        for (long i = 0; i < count; i ++) {
            data.add(RANDOM.nextLong());
        }

        return System.currentTimeMillis() - start;
    }

    private long testIndexAddArray() {
        IArray<Long> data = arrayFactory.create();

        data.add(RANDOM.nextLong());

        long start = System.currentTimeMillis();

        for (int i = 1; i < count; i ++) {
            data.add(RANDOM.nextLong(), RANDOM.nextInt(0, i));
        }

        return System.currentTimeMillis() - start;
    }

    private long testGetArray() {
        IArray<Long> data = arrayFactory.create();

        for (long i = 0; i < count; i ++) {
            data.add(RANDOM.nextLong());
        }

        long start = System.currentTimeMillis();

        for (long i = 0; i < count; i ++) {
            data.get(RANDOM.nextInt(0, count));
        }

        return System.currentTimeMillis() - start;
    }

    private long testSizeArray() {
        IArray<Long> data = arrayFactory.create();

        for (long i = 0; i < count; i ++) {
            data.add(RANDOM.nextLong());
        }

        long start = System.currentTimeMillis();

        for (long i = 0; i < count; i ++) {
            data.size();
        }

        return System.currentTimeMillis() - start;
    }

    private long testRemoveArray() {
        IArray<Long> data = arrayFactory.create();

        for (long i = 0; i < count; i ++) {
            data.add(RANDOM.nextLong());
        }

        long start = System.currentTimeMillis();

        for (long i = 0; i < count; i ++) {
            data.size();
        }

        return System.currentTimeMillis() - start;
    }
}
