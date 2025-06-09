package org.example;

import java.math.BigInteger;

public class MatrixPower {

    public static BigInteger[][] pow(BigInteger[][] base, Integer power) {
        if (base.length == 0 || base.length != base[0].length) {
            throw new RuntimeException("Не допустимая матрица");
        }
        if (power == 1) {
            return base;
        }

        BigInteger[][] result = new BigInteger[base.length][base.length];

        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < base.length; j++) {
                if (i != j) {
                    result[i][j] = BigInteger.ZERO;
                } else {
                    result[i][j] = BigInteger.ONE;
                }
            }
        }

        while (power > 0) {
            if (power % 2 == 1) {
                result = multiplication(result, base);
            }
            base = multiplication(base, base);
            power = power / 2;
        }

        return result;
    }

    private static BigInteger[][] multiplication(BigInteger[][] left, BigInteger[][] right) {
        BigInteger[][] result = new BigInteger[left.length][left.length];
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < left.length; j++) {
                BigInteger value = BigInteger.ZERO;
                for (int k = 0; k < left.length; k++) {
                    value = value.add(left[i][k].multiply(right[k][j]));
                }
                result[i][j] = value;
            }
        }
        return result;
    }
}
