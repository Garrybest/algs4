package chapter4;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小生成树的延时Prim算法
 *
 * @Author: Fang Rui
 * @Date: 2018/12/7
 * @Time: 20:54
 */
public class LazyPrimMst {
    private boolean[] marked;
    private Queue<Edge> mst;
    private PriorityQueue<Edge> pq;
    private double weight;

    public LazyPrimMst(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        pq = new PriorityQueue<>();

        visit(G, 0);

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w])
                continue;
            weight += e.weight();
            mst.offer(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge edge : G.adj(v)) {
            if (!marked[edge.other(v)])
                pq.offer(edge);
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
        LazyPrimMst mst = new LazyPrimMst(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
