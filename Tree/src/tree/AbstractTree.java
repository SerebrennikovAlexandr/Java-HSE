/*
This Project was made by Serebrennikov Alexander, BSE181.
December, 2019.
 */

package tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.function.BinaryOperator;

abstract class AbstractTree<T extends Number> {
    /**
     * FIELDS
     */

    private Node<T> root;
    private BinaryOperator<T> adder;
    private T sum;
    private Comparator<T> comparator;
    private T zero;

    /**
     * ACCESSORS
     */

    public Node<T> getRoot() {
        return root;
    }

    public T getSum() {
        recountSum();
        return sum;
    }

    public T getZero() {
        return zero;
    }

    public BinaryOperator<T> getAdder() {
        return adder;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    void setRoot(Node<T> root) {
        this.root = root;
    }

    void setZero(T zero) {
        this.zero = zero;
    }

    void setAdder(BinaryOperator<T> adder) {
        this.adder = adder;
    }

    void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * METHODS
     */

    abstract AbstractTree<T> removeSubtree(Node<T> rootSubTree);

    abstract AbstractTree<T> maximizeWithK(int k);

    abstract AbstractTree<T> maximize();

    /**
     * Method to get the size - the amount of nodes un the graph.
     * @return Integer number - amount of nodes.
     */
    public int getSize() {
        return getSize(root);
    }

    /**
     * Method to recount the sum of values in all tree nodes.
     * Works without recursion.
     * Resets this.sum - the result of adder function to zero and all values.
     */
    private void recountSum() {
        T res = zero;
        ArrayDeque<Node<T>> dq = new ArrayDeque<>();
        dq.add(root);
        while(!dq.isEmpty())
        {
            Node<T> tmp = dq.peekFirst();
            dq.removeFirst();
            res = adder.apply(res, tmp.getValue());
            if(tmp.getChildren() != null) {
                dq.addAll(tmp.getChildren());
            }
        }
        sum = res;
    }

    /**
     * Technical recursive method to get the size - the amount of nodes un the graph.
     * @return Integer number - amount of nodes.
     */
    private int getSize(Node<T> rootSubTree) {
        int res = 0;
        if(rootSubTree == null) {
            return res;
        }
        res++;
        if(rootSubTree.getChildren() != null) {
            for(Node<T> node: rootSubTree.getChildren()) {
                res += getSize(node);
            }
        }
        return res;
    }
}
