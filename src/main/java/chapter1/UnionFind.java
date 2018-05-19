package chapter1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

/**
 * @Description: 加权Union-Find算法的实现
 * @Author: Fang Rui
 * @Date: 2018/5/19
 * @Time: 15:09
 */
public class UnionFind {

    private int id[];
    private int height[];
    private int count;

    public UnionFind(int size) {
        count = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
            height[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (height[i] < height[j]) {
            id[i] = j;
        } else if (height[i] > height[j]) {
            id[j] = i;
        } else {
            id[i] = j;
            height[j]++;
        }
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = RecursiveBinarySearch.class.getClassLoader().getResource("tinyUF.txt").getPath();
        In in = new In(filePath);

        int n = in.readInt();
        UF uf = new UF(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}


