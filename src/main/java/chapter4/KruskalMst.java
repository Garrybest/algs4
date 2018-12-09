package chapter4;

import chapter1.UnionFind;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * 最小生成树的Kruskal算法
 *
 * @Author: Fang Rui
 * @Date: 2018/12/8
 * @Time: 17:16
 */
public class KruskalMst {
    private Queue<Edge> mst;
    private Edge[] edges;
    private double weight;

    public KruskalMst(EdgeWeightedGraph G) {
        mst = new LinkedList<>();
        edges = new Edge[G.E()];

        int idx = 0;
        for (Edge e : G.edges())
            edges[idx++] = e;
        Arrays.sort(edges);

        UnionFind uf = new UnionFind(G.V());
        idx = 0;
        while (mst.size() < G.V() - 1) {
            Edge e = edges[idx++];
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w))
                continue;
            uf.union(v, w);
            weight += e.weight();
            mst.offer(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) {
        String filePath = DirectedDFS.class.getClassLoader().getResource("tinyEWG.txt").getPath();
        In in = new In(filePath);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMst mst = new KruskalMst(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
