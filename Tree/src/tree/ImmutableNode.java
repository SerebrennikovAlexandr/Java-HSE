/*
This Project was made by Serebrennikov Alexander, BSE181.
December, 2019.
 */

package tree;

import java.util.Collection;
import java.util.function.Function;

class ImmutableNode<T extends Number> implements Node<T> {
    /**
     * FIELDS
     */

    private T value;
    private ImmutableNode<T> parent;
    private Collection<Node<T>> children;

    /**
     * ACCESSORS
     */

    public T getValue() {
        return value;
    }

    public ImmutableNode<T> getParent() {
        return parent;
    }

    public Collection<Node<T>> getChildren() {
        return children;
    }

    /**
     * CONSTRUCTORS
     */

    public ImmutableNode(T value, ImmutableNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public ImmutableNode(T value,
                         ImmutableNode<T> parent,
                         Function<ImmutableNode<T>, Collection<? extends Node<T>>> childrenConstructor
    ) {
        this.value = value;
        this.parent = parent;
        if(childrenConstructor != null) {
            this.children = (Collection<Node<T>>) childrenConstructor.apply(this);
        }
    }

    public ImmutableNode(T value,
                         ImmutableNode<T> parent,
                         Collection<Node<T>> children
    ) {
        this.value = value;
        this.parent = parent;
        this.children = children;
    }

    /**
     * METHODS
     */

    public void print(int indents) {
        for(int i = 0; i < indents; i++) {
            System.out.print(" ");
        }
        System.out.print(value);
    }
}
