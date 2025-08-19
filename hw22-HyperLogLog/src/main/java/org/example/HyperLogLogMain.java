package org.example;

import com.google.common.hash.Hashing;

import java.io.IOException;

public class HyperLogLogMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Хэш - sha256");
        Test sha256 = new Tester(Hashing.sha256());
        sha256.run();

        System.out.println("Хэш - sha384");
        Test sha384 = new Tester(Hashing.sha384());
        sha384.run();

        System.out.println("Хэш - sha512");
        Test sha512 = new Tester(Hashing.sha512());
        sha512.run();

        System.out.println("Хэш - murmur3_128");
        Test murmur3_128 = new Tester(Hashing.murmur3_128());
        murmur3_128.run();

        System.out.println("Хэш - sipHash24");
        Test sipHash24 = new Tester(Hashing.sipHash24());
        sipHash24.run();
    }
}