package chapter4;

import edu.princeton.cs.algs4.*;

/**
 * 最小生成树的即时Prim算法
 *
 * @Author: Fang Rui
 * @Date: 2018/12/8
 * @Time: 15:47
 */
public class PrimMst {
    private boolean[] marked;
    private double[] distTo;
    private Edge[] edgeTo;
    private IndexMinPQ<Double> pq;
    private double weight;

    public PrimMst(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int i = 0; i < G.V(); i++)
            distTo[i] = Double.MAX_VALUE;

        distTo[0] = 0;
        pq.insert(0, 0d);
        while (!pq.isEmpty()) {
            weight += pq.minKey();
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w))
                    pq.changeKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Bag<Edge> bag = new Bag<>();
        for (int i = 1; i < edgeTo.length; i++)
            bag.add(edgeTo[i]);
        return bag;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) {
        String filePath = DirectedDFS.class.getClassLoader().getResource("tinyEWG.txt").getPath();
        In in = new In(filePath);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimMst mst = new PrimMst(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}

