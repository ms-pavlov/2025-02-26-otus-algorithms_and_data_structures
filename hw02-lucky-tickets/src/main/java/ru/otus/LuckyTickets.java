package ru.otus;

import java.math.BigInteger;
import java.util.Arrays;

public class LuckyTickets {

    public BigInteger getLuckyTicketsCount(int n) {
        BigInteger[] digitSumVariantCount = getDigitSumVariantCount(getBeginDigitSumVariantCount(), 1, n);

        BigInteger result = BigInteger.ZERO;

        for (BigInteger item : digitSumVariantCount) {
            result = result.add(item.multiply(item));
        }

        return result;
    }

    private BigInteger[] getDigitSumVariantCount(BigInteger[] prevDigitSumVariantCount, int iteration, int n) {
        if (iteration == n) {
            return prevDigitSumVariantCount;
        }

        iteration++;

        BigInteger[] currDigitSumVariantCount = new BigInteger[9 * iteration + 1];

        BigInteger sum = BigInteger.ZERO;

        for (int i = 0, j = 9 * iteration; i <= j; i++, j--) {
            if (i - 10 >= 0) {
                sum = sum.subtract(prevDigitSumVariantCount[i - 10]);
            }
            sum = sum.add(prevDigitSumVariantCount[i]);
            currDigitSumVariantCount[i] = sum;
            currDigitSumVariantCount[j] = sum;
        }

        return getDigitSumVariantCount(currDigitSumVariantCount, iteration, n);
    }

    private BigInteger[] getBeginDigitSumVariantCount() {
        BigInteger[] currDigitSumVariantCount = new BigInteger[10];
        Arrays.fill(currDigitSumVariantCount, BigInteger.ONE);
        return currDigitSumVariantCount;
    }

}