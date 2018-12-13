package chapter4;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Topological;

/**
 * @Author: Fang Rui
 * @Date: 2018/12/12
 * @Time: 20:34
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int i = 0; i < distTo.length; i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        Topological top = new Topological(G);
        if (top.hasOrder()) {
            for (int v : top.order()) {
                relax(G, v);
            }
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();
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
        String filePath = DirectedDFS.class.getClassLoader().getResource("tinyEWDAG.txt").getPath();
        In in = new In(filePath);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        // compute shortest paths
        int s = 5;
        AcyclicSP sp = new AcyclicSP(G, s);

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
