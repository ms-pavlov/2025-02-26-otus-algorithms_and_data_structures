package org.example;

public class EnumPrime implements Prime{
    @Override
    public long getCount(int number) {
        long result = 0;
        for (int i = 2; i <= number; i++) {
            if (isPrime(i))
                result++;

        }

        return result;
    }

    private boolean isPrime(long n) {
        for(int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
