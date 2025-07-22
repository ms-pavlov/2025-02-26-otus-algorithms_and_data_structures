package org.example;

import java.math.BigInteger;

public class MatrixFibonacci implements Fibonacci{
    @Override
    public BigInteger get(Integer number) {
        if (0 == number) {
            return BigInteger.ZERO;
        }
        if (1 == number || 2 == number) {
            return BigInteger.ONE;
        }

        BigInteger[][] result = MatrixPower.pow(new BigInteger[][]{{BigInteger.ONE, BigInteger.ONE},{BigInteger.ONE, BigInteger.ZERO}},
                number - 1);


        return result[0][0];
    }


}
