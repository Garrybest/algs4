package chapter4;

import edu.princeton.cs.algs4.Digraph;

/**
 * 判断有向图是否有环
 *
 * @Author: Fang Rui
 * @Date: 2018/12/6
 * @Time: 21:52
 */
public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onCycle;
    private boolean hasCycle;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onCycle = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onCycle[v] = true;
        for (int w : G.adj(v)) {
            if (hasCycle)
                return;
            else if (!marked[w])
                dfs(G, w);
            else if (onCycle[w])
                hasCycle = true;
        }
        onCycle[v] = false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
