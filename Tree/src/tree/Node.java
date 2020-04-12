/*
This Project was made by Serebrennikov Alexander, BSE181.
December, 2019.
 */

package tree;

import java.util.Collection;

interface Node<T extends Number> extends Wrapper<T> {
    Node<T> getParent();
    Collection<Node<T>> getChildren();
    void print(int indents);
}
