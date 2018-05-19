package chapter1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @Description: 随机背包的数组实现
 * @Author: Fang Rui
 * @Date: 2018/5/1
 * @Time: 15:52
 */
public class RandomBag<Item> implements Iterable<Item> {

    private int n; // 记录目前背包里面有多少个元素
    private Item[] bag;

    public RandomBag(int max) {
        this.n = 0;
        bag = (Item[]) new Object[max];
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }

    void add(Item item) {
        bag[n++] = item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<Item> {

        private int current = -1;

        public RandomBagIterator() {
            // 打乱数组顺序
            for (int i = 0; i < n; i++) {
                int exchangeNum = i + (int) (Math.random() * (n - i));
                Item temp = bag[i];
                bag[i] = bag[exchangeNum];
                bag[exchangeNum] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return current < n - 1;
        }

        @Override
        public Item next() {
            return bag[++current];
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        RandomBag<String> randomBag = new RandomBag<>(10);
        StdOut.println("请输入背包元素：");
        while (!StdIn.isEmpty()) {
            randomBag.add(StdIn.readString());
        }
        StdOut.println("随机背包的大小为：" + randomBag.size());

        Iterator iterator = randomBag.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
    }
}
