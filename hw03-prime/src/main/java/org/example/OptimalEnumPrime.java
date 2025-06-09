package org.example;

public class OptimalEnumPrime implements Prime {
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
        if (n == 2) {
            return true;
        }
        if ((n % 2) == 0) {
            return false;
        }
        long sqrt = (long) Math.sqrt(n);

        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
