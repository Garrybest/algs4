package chapter3;

import chapter2.Insertion;
import chapter2.SortUtil;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 *
 * @Author: Fang Rui
 * @Date: 2018/7/5
 * @Time: 10:18
 */
public class Bst<Key extends Comparable<Key>, Value> implements OrderSt<Key, Value> {
    protected Node root;

    class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.N = n;
            this.color = false;
        }

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.N = n;
            this.color = color;
        }
    }

    @Override
    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    protected Node put(Key key, Value value, Node x) {
        if (x == null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(key, value, x.left);
        else if (cmp > 0)
            x.right = put(key, value, x.right);
        else
            x.val = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Value get(Key key) {
        return get(key, root);
    }

    private Value get(Key key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(key, x.left);
        else if (cmp > 0)
            return get(key, x.right);
        else return x.val;
    }

    @Override
    public void delete(Key key) {
        root = delete(key, root);
    }

    private Node delete(Key key, Node x) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(key, x.left);
        else if (cmp > 0)
            x.right = delete(key, x.right);
        else {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public int size() {
        return size(root);
    }

    protected final int size(Node node) {
        if (node == null)
            return 0;
        else
            return node.N;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key low, Key high) {
        Queue<Key> queue = new LinkedList<>(); // Collection接口继承于Iterable接口
        keys(root, queue, low, high);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key low, Key high) {
        if (x == null)
            return;
        int cmplow = low.compareTo(x.key);
        int cmphigh = high.compareTo(x.key);
        if (cmplow < 0)
            keys(x.left, queue, low, high); // 遍历左子树
        if (cmplow <= 0 && cmphigh >= 0)
            queue.add(x.key); // 加入节点
        if (cmphigh > 0)
            keys(x.right, queue, low, high); // 遍历右子树
    }

    @Override
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        return floor(key, root);
    }

    private Key floor(Key key, Node x) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        Key result;
        if (cmp < 0) {
            result = floor(key, x.left);
            if (result == null)
                return null;
        } else if (cmp > 0) {
            result = floor(key, x.right);
            if (result == null)
                return x.key;
        } else
            return x.key;
        return result;
    }

    @Override
    public Key ceiling(Key key) {
        return ceiling(key, root);
    }

    private Key ceiling(Key key, Node x) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        Key result;
        if (cmp < 0) {
            result = ceiling(key, x.left);
            if (result == null)
                return x.key;
        } else if (cmp > 0) {
            result = ceiling(key, x.right);
            if (result == null)
                return null;
        } else
            return x.key;
        return result;
    }

    @Override
    public Key select(int k) {
        return select(k, root);
    }

    private Key select(int k, Node x) {
        if (k < 0 || k > size(x) - 1)
            return null;
        int leftSize = size(x.left);
        if (k < leftSize)
            return select(k, x.left);
        else if (k > leftSize)
            return select(k - leftSize - 1, x.right);
        else
            return x.key;
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return rank(key, x.right) + size(x.left) + 1;
        else
            return size(x.left);
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = Bst.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] strings = in.readAllStrings();
        Bst<String, Integer> bst = new Bst<>();
        for (int i = 0; i < strings.length; i++) {
            bst.put(strings[i], i);
        }
        System.out.println(bst.get("zoo"));
        System.out.println(bst.min());
        System.out.println(bst.max());
        bst.deleteMin();
        bst.deleteMax();
        System.out.println(bst.size());

        System.out.println(bst.floor("damn"));
        System.out.println(bst.ceiling("damn"));
        System.out.println(bst.select(3));
        System.out.println(bst.rank("dog"));

        bst.delete("bed");
        for (String s : bst.keys()) {
            System.out.print(s + " ");
        }


    }

}
