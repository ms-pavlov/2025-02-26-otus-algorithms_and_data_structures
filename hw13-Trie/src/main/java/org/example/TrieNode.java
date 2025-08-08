package org.example;

import lombok.Getter;
import lombok.Setter;

public class TrieNode<V> {
    private final static int R = 26;

    private final TrieNode<?>[] children;

    @Getter
    @Setter
    private V value;


    public TrieNode() {
        this.children = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return children[ch - 'a'] != null;
    }

    public TrieNode<V> get(char ch) {
        return (TrieNode<V>) children[ch - 'a'];
    }

    public void put(char ch, TrieNode<V> node) {
        children[ch - 'a'] = node;
    }

    public boolean isEnd() {
        return null != value;
    }

}
