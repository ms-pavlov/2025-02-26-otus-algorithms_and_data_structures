package ru.otus.dsl.scaner;

import org.springframework.stereotype.Component;
import ru.otus.model.Node;
import ru.otus.model.Token;
import ru.otus.model.enums.KeyWords;

import java.util.*;

@Component
public class Aho implements Search {

    private Node root;

    protected Aho() {
        this.root = new Node("");
        KeyWords.getKeyWords().forEach(this::addPattern);
        buildLinks();
    }

    @Override
    public List<Token> scan(String text) {
        List<Token> matches = new ArrayList<>();
        Node node = root;
        int begin = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (node == root) {
                begin = i;
            }
            while ((node != null) && (!node.child.containsKey(c))) {
                node = node.suffixLink;
                if (node != null) {
                    begin = i - node.prefix.length();
                }
            }
            if (node == null) {
                node = root;
                continue;
            }
            node = node.child.get(c);
            if (node.isFinal)
                matches.add(new Token(begin, i, text.substring(begin, i + 1)));
            Node finals = node.FinalLink;
            while (finals != null) {
                matches.add(new Token(i + 1 - finals.prefix.length(), i, finals.prefix));
                finals = finals.FinalLink;
            }
        }
        return matches;
    }


    private void addPattern(String mask) {
        Node node = root;
        for (char c : mask.toCharArray()) {
            if (!node.child.containsKey(c)) {
                node.child.put(c, new Node(node.prefix + c));
            }
            node = node.child.get(c);
        }
        node.isFinal = true;
    }

    private void buildLinks() {
        Queue<Node> queue = new LinkedList<>();
        for (Node node : root.child.values()) {
            queue.offer(node);
            node.suffixLink = root;
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (char c : node.child.keySet()) {
                Node child = node.child.get(c);
                queue.offer(child);
                Node suffixLink = node.suffixLink;
                while ((suffixLink != null) &&
                        (!suffixLink.child.containsKey(c)))
                    suffixLink = suffixLink.suffixLink;
                if (suffixLink == null)
                    child.suffixLink = root;
                else
                    child.suffixLink = suffixLink.child.get(c);

                if (child.suffixLink.isFinal)
                    child.FinalLink = child.suffixLink;
                else
                    child.FinalLink = child.suffixLink.FinalLink;
            }
        }
    }


}
