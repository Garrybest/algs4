package chapter4;

import edu.princeton.cs.algs4.*;

import java.util.PriorityQueue;

/**
 * 最短路径的Dijkstra算法
 *
 * @Author: Fang Rui
 * @Date: 2018/12/12
 * @Time: 16:43
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for (int i = 0; i < distTo.length; i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        distTo[s] = 0;
        pq.insert(s, 0d);

        while (!pq.isEmpty())
            relax(G, pq.delMin());
    }

    public void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();
                if (pq.contains(w))
                    pq.changeKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> stack = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            stack.push(e);
        return stack;
    }

    public static void main(String[] args) {
        String filePath = DirectedDFS.class.getClassLoader().getResource("tinyEWD.txt").getPath();
        In in = new In(filePath);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        // compute shortest paths
        int s = 0;
        DijkstraSP sp = new DijkstraSP(G, s);

        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }
}
