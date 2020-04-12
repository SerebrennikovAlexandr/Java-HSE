package tree;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class MutableTreeTest {

    @Test
    void getRoot() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        assertEquals(intTree.getRoot(), intNode);
    }

    @Test
    void getSum() {
        // Test 1

        MutableNode<Integer> intNode = new MutableNode<>(0, null, new ArrayList<>());

        for(int i = 1; i <= 3; i++) {
            intNode.addChild(new MutableNode<>(i, null, new ArrayList<>()));
        }

        for(int i = 0; i < 2; i++) {
            MutableNode<Integer> tmp = (MutableNode<Integer>)intNode.getChildren().toArray()[i];
            tmp.addChild(new MutableNode<>(-4, null, new ArrayList<>()));
            tmp.addChild(new MutableNode<>(6, null, new ArrayList<>()));
        }

        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        assertEquals(intTree.getSum(), 10);

        // Test 2

        BinaryOperator<Integer> newAdder = (x, y) -> x * y;
        intTree.setAdder(newAdder);

        assertEquals(intTree.getSum(), 0);

        // Test 3

        intNode = new MutableNode<>(2, null, new ArrayList<>());
        intNode.addChild(new MutableNode<>(3, null, new ArrayList<>()));

        intTree.setRoot(intNode);
        intTree.setZero(1);

        assertEquals(intTree.getSum(), 6);
    }

    @Test
    void getZero() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        assertEquals(intTree.getZero(), 0);
    }

    @Test
    void getAdder() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        assertEquals(intTree.getAdder(), adder);
    }

    @Test
    void getComparator() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        assertEquals(intTree.getComparator(), comparator);
    }

    @Test
    void setRoot() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        MutableNode<Integer> anotherIntNode = new MutableNode<>(-3, null, new ArrayList<>());
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        intTree.setRoot(anotherIntNode);

        assertEquals(intTree.getRoot(), anotherIntNode);
    }

    @Test
    void setZero() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        intTree.setZero(1);

        assertEquals(intTree.getZero(), 1);
    }

    @Test
    void setAdder() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        BinaryOperator<Integer> newAdder = (x, y) -> x * y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        intTree.setAdder(newAdder);

        assertEquals(intTree.getAdder(), newAdder);
    }

    @Test
    void setComparator() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;
        Comparator<Integer> newComparator = (x, y) -> y.compareTo(x);

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        intTree.setComparator(newComparator);

        assertEquals(intTree.getComparator(), newComparator);
    }

    @Test
    void getSize() {
        MutableNode<Integer> intNode = new MutableNode<>(0, null, new ArrayList<>());

        for(int i = 1; i <= 3; i++) {
            intNode.addChild(new MutableNode<>(i, null, new ArrayList<>()));
        }

        for(int i = 0; i < 2; i++) {
            MutableNode<Integer> tmp = (MutableNode<Integer>)intNode.getChildren().toArray()[i];
            tmp.addChild(new MutableNode<>(-4, null, new ArrayList<>()));
            tmp.addChild(new MutableNode<>(6, null, new ArrayList<>()));
        }

        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        assertEquals(intTree.getSize(), 8);
    }

    @Test
    void removeSubtree() {
        MutableNode<Integer> intNode = new MutableNode<>(0, null, new ArrayList<>());

        for(int i = 1; i <= 3; i++) {
            intNode.addChild(new MutableNode<>(i, null, new ArrayList<>()));
        }

        for(int i = 0; i < 2; i++) {
            MutableNode<Integer> tmp = (MutableNode<Integer>)intNode.getChildren().toArray()[i];
            tmp.addChild(new MutableNode<>(-4, null, new ArrayList<>()));
            tmp.addChild(new MutableNode<>(6, null, new ArrayList<>()));
        }

        BinaryOperator<Integer> adder = (x, y) -> x + y;
        Comparator<Integer> comparator = Integer::compareTo;

        MutableTree<Integer> intTree = new MutableTree<>(intNode, adder, comparator, 0);

        assertEquals(intTree.getSize(), 8);
        assertEquals(intTree.getSum(), 10);

        MutableNode<Integer> nodeToDelete = (MutableNode<Integer>)intNode.getChildren().toArray()[0];

        intTree.removeSubtree(nodeToDelete);

        assertEquals(intTree.getSize(), 5);
        assertEquals(intTree.getSum(), 7);
        assert(!intTree.getRoot().getChildren().contains(nodeToDelete));
    }
}