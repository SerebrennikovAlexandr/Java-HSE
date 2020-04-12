package tree;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.function.Function;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableNodeTest {

    @Test
    void getValue() {
        Function<ImmutableNode<Integer>, Collection<? extends Node<Integer>>> constructor =
                intNode -> new ArrayList<Node<Integer>>();

        ImmutableNode<Integer> intNode = new ImmutableNode<>(3, null, constructor);
        assertEquals(intNode.getValue(), 3);

        ImmutableNode<Double> doubleNode = new ImmutableNode<>(2.05, null);
        assertEquals(doubleNode.getValue(), 2.05);
    }

    @Test
    void getParent() {
        Function<ImmutableNode<Integer>, Collection<? extends Node<Integer>>> constructor =
                intNode -> new ArrayList<Node<Integer>>();

        ImmutableNode<Integer> intParent = new ImmutableNode<>(null, null, constructor);
        ImmutableNode<Integer> intChild = new ImmutableNode<>(2, intParent, constructor);

        assertEquals(intChild.getParent(), intParent);
    }

    @Test
    void getChildren() {
        Function<ImmutableNode<Integer>, Collection<? extends Node<Integer>>> constructor =
                intNode -> {
                    ArrayList<Node<Integer>> list = new ArrayList<>();
                    list.add(new ImmutableNode<>(0, intNode));
                    list.add(new ImmutableNode<>(1, intNode));
                    return list;
                };

        ImmutableNode<Integer> intNode = new ImmutableNode<Integer>(-1, null, constructor);

        assert(intNode.getChildren().size() == 2);
        assertEquals(((ImmutableNode<Integer>)intNode.getChildren().toArray()[0]).getValue(), 0);
        assertEquals(((ImmutableNode<Integer>)intNode.getChildren().toArray()[1]).getValue(), 1);
        assertEquals(((ImmutableNode<Integer>)intNode.getChildren().toArray()[0]).getParent(), intNode);
        assertEquals(((ImmutableNode<Integer>)intNode.getChildren().toArray()[1]).getParent(), intNode);
    }
}