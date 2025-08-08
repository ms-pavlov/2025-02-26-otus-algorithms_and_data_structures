package org.example;

public class TrieMain {
    public static void main(String[] args) {
        Test test = new Tester(1_000_000, 10, TrieImpl::new);

        test.run();
    }
}