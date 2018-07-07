package chapter3;

import edu.princeton.cs.algs4.In;

/**
 * @Author: Fang Rui
 * @Date: 2018/7/7
 * @Time: 19:55
 */
public class RedBlackBst<Key extends Comparable<Key>, Value> extends Bst<Key, Value> {
    private static final boolean RED = true;
    public static final boolean BLACK = false;

    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        x.N = h.N;
        h.color = RED;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.N = h.N;
        h.color = RED;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(key, value, root);
        root.color = BLACK;
    }

    @Override
    protected Node put(Key key, Value value, Node h) {
        if (h == null)
            return new Node(key, value, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(key, value, h.left);
        else if (cmp > 0)
            h.right = put(key, value, h.right);
        else
            h.val = value;

        if (!isRed(h.left) && isRed(h.right))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = RedBlackBst.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] strings = in.readAllStrings();
        RedBlackBst<String, Integer> rbBst = new RedBlackBst<>();
        for (int i = 0; i < strings.length; i++) {
            rbBst.put(strings[i], i);
        }
        for (String s : rbBst.keys()) {
            System.out.print(s + " ");
        }
    }
}
