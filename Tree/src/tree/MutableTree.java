/*
This Project was made by Serebrennikov Alexander, BSE181.
December, 2019.
 */

package tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.function.BinaryOperator;

class MutableTree<T extends Number> extends AbstractTree<T> {

    /**
     * ACCESSORS
     */

    @Override
    public void setRoot(Node<T> root) {
        super.setRoot(root);
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        super.setComparator(comparator);
    }

    @Override
    public void setZero(T zero) {
        super.setZero(zero);
    }

    @Override
    public void setAdder(BinaryOperator<T> adder) {
        super.setAdder(adder);
    }

    /**
     * CONSTRUCTOR
     */

    public MutableTree(MutableNode<T> root, BinaryOperator<T> adder, Comparator<T> comparator, T zeroToCompare) {
        setRoot(root);
        setAdder(adder);
        setComparator(comparator);
        setZero(zeroToCompare);
    }

    /**
     * METHODS
     */

    @Override
    public AbstractTree<T> removeSubtree(Node<T> rootSubTree) {
        if (getRoot() == null || getRoot() == rootSubTree)
            return null;
        ArrayDeque<MutableNode<T>> dq = new ArrayDeque<>();
        dq.add((MutableNode<T>) getRoot());
        while(!dq.isEmpty())
        {
            boolean found = false;
            MutableNode<T> tmp = dq.peekFirst();
            dq.removeFirst();
            for(int i = 0; i < tmp.getChildren().size(); i++)
            {
                if((MutableNode<T>)tmp.getChildren().toArray()[i] == rootSubTree) {
                    found = true;
                    tmp.removeChild((MutableNode<T>)tmp.getChildren().toArray()[i]);
                    ((MutableNode<T>)tmp.getChildren().toArray()[i]).setParent(null);
                } else {
                    dq.addLast((MutableNode<T>)tmp.getChildren().toArray()[i]);
                }
            }
            if(found) {
                break;
            }
        }
        return this;
    }

    @Override
    public AbstractTree<T> maximize() {
        return null;
    }

    @Override
    public AbstractTree<T> maximizeWithK(int k) {
        return null;
    }
}
