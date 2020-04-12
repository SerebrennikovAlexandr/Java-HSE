/*
This Project was made by Serebrennikov Alexander, BSE181.
December, 2019.
 */

package tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;

final class ImmutableTree<T extends Number> extends AbstractTree<T> {
    /**
     * CONSTRUCTOR
     */

    public ImmutableTree(ImmutableNode<T> root, BinaryOperator<T> adder, Comparator<T> comparator, T zeroToCompare) {
        setRoot(root);
        setAdder(adder);
        setComparator(comparator);
        setZero(zeroToCompare);
    }

    /**
     * METHODS
     */

    @Override
    AbstractTree<T> removeSubtree(Node<T> rootSubTree) {
        if(getRoot() == null || getRoot() == rootSubTree) {
            return null;
        }

        ImmutableNode<T> newRoot = copyTreeWithoutSubtree(getRoot(), rootSubTree);

        ImmutableTree<T> newTree = new ImmutableTree<T>(newRoot, getAdder(), getComparator(), getZero());

        return newTree;
    }

    @Override
    AbstractTree<T> maximizeWithK(int k) {
        return null;
    }

    @Override
    AbstractTree<T> maximize() {
        return null;
    }

    private ImmutableNode<T> copyTreeWithoutSubtree(Node<T> currentRoot, Node<T> rootSubTree) {
        if(currentRoot == rootSubTree) {
            return null;
        }
        Collection<Node<T>> res = null;
        if(currentRoot.getChildren() != null) {
            res = new ArrayList<Node<T>>();
            Collection<Node<T>> tmp = currentRoot.getChildren();
            for(Node<T> cur: tmp) {
                ImmutableNode<T> node = copyTreeWithoutSubtree(cur, rootSubTree);
                if(node != null) {
                    res.add(node);
                }
            }
        }
        return new ImmutableNode<T>(currentRoot.getValue(), (ImmutableNode<T>) currentRoot.getParent(), res);
    }
}
