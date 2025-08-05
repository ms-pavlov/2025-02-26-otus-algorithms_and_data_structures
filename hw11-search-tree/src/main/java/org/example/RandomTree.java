package org.example;

import java.util.Random;

public class RandomTree extends SplayTree {
    private final static Random RANDOM = new Random();
    private final static int BOUND = 100;
    private final double probability;

    public RandomTree(double probability) {
        this.probability = probability;
    }


    @Override
    public void insert(int key) {
        if (((double) RANDOM.nextInt(BOUND) /BOUND) < probability) {
            super.splayInsert(key);
        } else {
            super.insert(key, root);
        }
    }
}
