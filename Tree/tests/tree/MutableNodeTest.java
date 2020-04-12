package tree;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MutableNodeTest {

    @Test
    void getValue() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());

        assertEquals(intNode.getValue(), 3);

        MutableNode<Double> doubleNode = new MutableNode<>(2.05, null, new ArrayList<>());

        assertEquals(doubleNode.getValue(), 2.05);
    }

    @Test
    void getParent() {
        MutableNode<Integer> intParent = new MutableNode<>(null, null, new ArrayList<>());
        MutableNode<Integer> intChild = new MutableNode<>(2, intParent, new ArrayList<>());

        assertEquals(intChild.getParent(), intParent);
    }

    @Test
    void getChildren() {
        ArrayList<Node<Integer>> list = new ArrayList<>();
        list.add(new MutableNode<>(0, null, new ArrayList<>()));
        list.add(new MutableNode<>(1, null, new ArrayList<>()));

        MutableNode<Integer> intNode = new MutableNode<>(3, null, list);

        assertEquals(list, intNode.getChildren());
    }

    @Test
    void setValue() {
        MutableNode<Integer> intNode = new MutableNode<>(null, null, new ArrayList<>());
        intNode.setValue(4);

        assertEquals(4, intNode.getValue());
    }

    @Test
    void setParent() {
        MutableNode<Integer> intParent = new MutableNode<>(null, null, new ArrayList<>());
        MutableNode<Integer> intChild = new MutableNode<>(2, null, new ArrayList<>());

        intChild.setParent(intParent);

        assertEquals(intChild.getParent(), intParent);
        assert(intParent.getChildren().contains(intChild));
    }

    @Test
    void setChildren() {
        MutableNode<Integer> intNode = new MutableNode<>(3, null, new ArrayList<>());
        assert(intNode.getChildren().isEmpty());

        ArrayList<Node<Integer>> list = new ArrayList<>();
        list.add(new MutableNode<>(0, null, new ArrayList<>()));
        list.add(new MutableNode<>(1, null, new ArrayList<>()));

        intNode.setChildren(list);

        assertEquals(intNode.getChildren(), list);

        for(Node<Integer> node: intNode.getChildren()) {
            assertEquals(node.getParent(), intNode);
        }

        try {
            new MutableNode<>(3, null, null);
        } catch (Exception e) {
            assert(e instanceof IllegalArgumentException);
        }
    }

    @Test
    void addChild() {
        MutableNode<Integer> intParent = new MutableNode<>(null, null, new ArrayList<>());
        MutableNode<Integer> intChild = new MutableNode<>(2, null, new ArrayList<>());

        intParent.addChild(intChild);

        assertEquals(intChild.getParent(), intParent);
        assert(intParent.getChildren().contains(intChild));
    }

    @Test
    void removeChild() {
        MutableNode<Integer> intParent = new MutableNode<>(null, null, new ArrayList<>());
        MutableNode<Integer> intChild = new MutableNode<>(2, intParent, new ArrayList<>());

        intParent.removeChild(intChild);

        assert(intParent.getChildren().isEmpty());
        assertNull(intChild.getParent());
    }
}