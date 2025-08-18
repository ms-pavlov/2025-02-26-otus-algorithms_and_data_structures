package ru.otus.model;

import lombok.Getter;
import lombok.Setter;
import ru.otus.model.enums.SyntaxElementType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class SyntaxNode {

    private String element;
    private SyntaxElementType type;
    private List<SyntaxNode> nodes;

    public SyntaxNode(SyntaxElementType type) {
        this.nodes = new ArrayList<>();
        this.type = type;
    }

    public boolean add(SyntaxNode subNode) {
        if(null == subNode) {
            return false;
        }
        return nodes.add(subNode);
    }

    public boolean addAll(Collection<SyntaxNode> subNode) {
        if(null == subNode) {
            return false;
        }
        return nodes.addAll(subNode);
    }
}
