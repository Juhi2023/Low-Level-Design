package datastructures;

import iterator.Iterator;
import iterable.Iterable;
import iterator.BinaryTreeInorderIterator;

public class BinaryTree implements Iterable<Integer>{
    public int data;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
        data = value;
        left = null;
        right = null;
    }

    public Iterator<Integer> getIterator() {
        return new BinaryTreeInorderIterator(this);
    }
}