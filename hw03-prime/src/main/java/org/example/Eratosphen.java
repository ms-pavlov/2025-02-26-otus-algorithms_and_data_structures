package org.example;

public class Eratosphen implements Prime {
    @Override
    public long getCount(int number) {
        boolean[] prime = new boolean[number + 1];
        int count = 0;
        for (int i = 2; i <= number; i++) {
            if (!prime[i]) {
                count++;
                for (long j = (long) i * i; j <= number; j += i) {
                    prime[(int) j] = true;
                }
            }
        }
        return count;

    }
}
