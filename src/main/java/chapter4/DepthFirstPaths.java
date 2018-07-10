package chapter4;


import java.util.Stack;

/**
 * 深度优先搜索
 *
 * @Author: Fang Rui
 * @Date: 2018/7/10
 * @Time: 14:59
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int start;

    public DepthFirstPaths(Graph G, int start) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.start = start;
        dfs(G, start);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();
        for (int i = v; i != start; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(start);
        return path;
    }
}
