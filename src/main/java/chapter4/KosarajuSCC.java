package chapter4;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Kosaraju算法判断强连通性
 *
 * @Author: Fang Rui
 * @Date: 2018/12/7
 * @Time: 16:05
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    private boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    private int count() {
        return count;
    }

    private int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        String filePath = DirectedDFS.class.getClassLoader().getResource("tinyDG.txt").getPath();
        Digraph G = new Digraph(new In(filePath));
        KosarajuSCC scc = new KosarajuSCC(G);
        for (int v = 0; v < G.V(); v++) {
            StdOut.println(scc.id[v]);
        }
    }
}
