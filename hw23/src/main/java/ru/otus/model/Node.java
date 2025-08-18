package ru.otus.model;

import java.util.HashMap;
import java.util.Map;

public class Node {

    public String prefix;
    public boolean isFinal;
    public Node suffixLink;
    public Node FinalLink;
    public Map<Character, Node> child;

    public Node(String prefix) {
        this.prefix = prefix;
        this.isFinal = false;
        this.suffixLink = null;
        this.FinalLink = null;
        this.child = new HashMap<>();
    }

    @Override
    public String toString() {
        return prefix + (isFinal ? " FINAL" : "");
    }

}
