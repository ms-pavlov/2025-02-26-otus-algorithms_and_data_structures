package org.example;

public interface Trie<T> {

    void insert(String word, T value);

    boolean search(String word);

    T get(String word);

    boolean startsWith(String prefix);

    void delete(String prefix);
}
