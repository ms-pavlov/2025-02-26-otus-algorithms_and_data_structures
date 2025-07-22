package org.example;

import java.math.BigInteger;

public class RecursiveFibonacci implements Fibonacci{

    @Override
    public BigInteger get(Integer number) {
        if (0 == number) {
            return BigInteger.ZERO;
        }
        if (1 == number || 2 == number) {
            return BigInteger.ONE;
        }
        return get(number - 1).add(get(number - 2));
    }
}
