package chapter4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 无向图的API
 *
 * @Author: Fang Rui
 * @Date: 2018/7/10
 * @Time: 9:20
 */
public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int V) {
        return adj[V];
    }

    @Override
    public String toString() {
        String s = V() + " vertices, " + E() + " edges\n";
        for (int i = 0; i < V(); i++) {
            s += i + ": ";
            for (int w : adj(i)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static int degree(Graph G, int V) {
        int degree = 0;
        for (int w : G.adj(V)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(Graph G) {
        int maxDegree = 0;
        for (int i = 0; i < G.V(); i++) {
            if (maxDegree < degree(G, i)) {
                maxDegree = degree(G, i);
            }
        }
        return maxDegree;
    }

    public static double avgDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int i = 0; i < G.V(); i++) {
            for (int w : G.adj(i)) {
                if (w == i) {
                    count++;
                }
            }
        }
        return count / 2;
    }
}
