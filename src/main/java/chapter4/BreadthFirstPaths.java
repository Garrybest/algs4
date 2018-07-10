package chapter4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先搜索
 *
 * @Author: Fang Rui
 * @Date: 2018/7/10
 * @Time: 16:04
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int start;

    public BreadthFirstPaths(Graph G, int start) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.start = start;
        bfs(G, start);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.offer(w);
                }
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
