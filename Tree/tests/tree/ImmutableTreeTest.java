package tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableTreeTest {

    private BinaryOperator<Integer> adder = (x, y) -> x + y;
    private Comparator<Integer> comparator = Integer::compareTo;
    private Function<ImmutableNode<Integer>, Collection<? extends Node<Integer>>> constructor =
            intNode -> {
                ArrayList<Node<Integer>> list = new ArrayList<>();
                list.add(new ImmutableNode<>(1, intNode));
                list.add(new ImmutableNode<>(2, intNode));

                ArrayList<Node<Integer>> tmp = new ArrayList<>();
                tmp.add(new ImmutableNode<>(4, intNode));

                list.add(new ImmutableNode<>(3, intNode, tmp));
                return list;
            };

    @Test
    void removeSubtree() {
        ImmutableNode<Integer> intNode = new ImmutableNode<>(-1, null, constructor);

        ImmutableTree<Integer> intTree1 = new ImmutableTree<>(intNode, adder, comparator, 0);

        ImmutableNode<Integer> nodeToDelete = ((ImmutableNode<Integer>)intTree1.getRoot().getChildren().toArray()[2]);
        ImmutableTree<Integer> intTree2 = (ImmutableTree<Integer>) intTree1.removeSubtree(nodeToDelete);

        assertEquals(9, intTree1.getSum());
        assertEquals(5, intTree1.getSize());

        assert (intTree2 != null);
        assertEquals(2, intTree2.getSum());
        assertEquals(3, intTree2.getSize());
    }

    @Test
    void getSum() {


        ImmutableNode<Integer> intNode = new ImmutableNode<>(-1, null, constructor);

        ImmutableTree<Integer> intTree = new ImmutableTree<>(intNode, adder, comparator, 0);

        assertEquals(9, intTree.getSum());
    }

    @Test
    void getSize() {

        ImmutableNode<Integer> intNode = new ImmutableNode<>(-1, null, constructor);

        ImmutableTree<Integer> intTree = new ImmutableTree<>(intNode, adder, comparator, 0);

        assertEquals(5, intTree.getSize());
    }
}