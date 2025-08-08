package org.example;

public class TrieImpl<T> implements Trie<T> {

    private final TrieNode<T> root;

    public TrieImpl() {
        root = new TrieNode<>();
    }

    @Override
    public void insert(String word, T value) {
        TrieNode<T> node = root;

        for (char currentChar : word.toCharArray()) {
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode<>());
            }
            node = node.get(currentChar);
        }
        node.setValue(value);
    }

    @Override
    public boolean search(String word) {
        TrieNode<T> node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    @Override
    public T get(String word) {
        TrieNode<T> node = searchPrefix(word);
        return node != null? node.getValue():  null;
    }

    @Override
    public boolean startsWith(String prefix) {
        TrieNode<T> node = searchPrefix(prefix);
        return node != null;
    }

    @Override
    public void delete(String prefix) {
        TrieNode<T> node = searchPrefix(prefix);
        if(node != null) {
            node.setValue(null);
        }
    }

    private TrieNode<T> searchPrefix(String word) {
        TrieNode<T> pointer = root;
        for (char currentChar : word.toCharArray()) {
            if (!pointer.containsKey(currentChar)) {
                return null;
            }
            pointer = pointer.get(currentChar);
        }
        return pointer;
    }

}
