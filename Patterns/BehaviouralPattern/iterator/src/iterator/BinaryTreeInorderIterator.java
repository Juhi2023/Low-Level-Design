package iterator;
import java.util.*;
import datastructures.BinaryTree;

public class BinaryTreeInorderIterator implements Iterator<Integer> {
    private Deque<BinaryTree> stk = new ArrayDeque<>();

    private void pushLefts(BinaryTree node) {
        while (node != null) {
            stk.push(node);
            node = node.left;
        }
    }

    public BinaryTreeInorderIterator(BinaryTree root) {
        pushLefts(root);
    }

    public boolean hasNext() {
        return !stk.isEmpty();
    }

    public Integer next() {
        BinaryTree node = stk.pop();
        int val = node.data;
        if (node.right != null) {
            pushLefts(node.right);
        }
        return val;
    }
}