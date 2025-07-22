package org.example;

import java.math.BigInteger;

public class IterativeFibonacci implements Fibonacci{
    @Override
    public BigInteger get(Integer number) {
        if (0 == number) {
            return BigInteger.ZERO;
        }

        BigInteger prev = BigInteger.ZERO;
        BigInteger curr = BigInteger.ONE;

        for(int i = 1; i < number; i++) {
            BigInteger next = curr.add(prev);
            prev = curr;
            curr = next;
        }

        return curr;
    }
}
