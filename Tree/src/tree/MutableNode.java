/*
This Project was made by Serebrennikov Alexander, BSE181.
December, 2019.
 */

package tree;

import java.util.Collection;
import java.util.function.Function;

class MutableNode<T extends Number> implements Node<T> {
    /**
     * FIELDS
     */

    private T value;
    private MutableNode<T> parent;
    private Collection<Node<T>> children;

    /**
     * ACCESSORS
     */

    public T getValue() {
        return value;
    }

    public MutableNode<T> getParent() {
        return parent;
    }

    public Collection<Node<T>> getChildren() {
        return children;
    }

    void setValue(T value) {
        this.value = value;
    }

    void setParent(MutableNode<T> parent) {
        if(parent != null && parent.getChildren() != null) {
            parent.getChildren().add(this);
        }
        this.parent = parent;
    }

    void setChildren(Collection<Node<T>> children) {
        if(children != null) {
            for(int i = 0; i < children.size(); i++)
            {
                ((MutableNode<T>)children.toArray()[i]).setParent(this);
            }
            this.children = children;
        } else {
            throw new IllegalArgumentException("Collection for children can't be null");
        }
    }

    /**
     * CONSTRUCTOR
     */

    public MutableNode(T value,
                         MutableNode<T> parent,
                         Collection<? extends Node<T>> children
    ) {
        setValue(value);
        setParent(parent);
        setChildren((Collection<Node<T>>) children);
    }

    /**
     * METHODS
     */

    public void addChild(MutableNode<T> child) {
        if(!children.contains(child)) {
            child.setParent(this);
        }
    }

    public void removeChild(MutableNode<T> child) {
        if(children.contains(child)) {
            child.setParent(null);
            children.remove(child);
        }
    }

    public void print(int indents) {
        for(int i = 0; i < indents; i++) {
            System.out.print(" ");
        }
        System.out.print(value);
    }
}
