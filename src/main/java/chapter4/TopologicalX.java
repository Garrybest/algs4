package chapter4;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列实现的拓扑排序
 *
 * @Author: Fang Rui
 * @Date: 2018/12/7
 * @Time: 10:02
 */
public class TopologicalX {
    private Queue<Integer> order;
    private int[] rank;

    public TopologicalX(Digraph G) {
        order = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        rank = new int[G.V()];
        int count = 0;

        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
            if (indegree[v] == 0)
                queue.offer(v);
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            order.offer(v);
            rank[v] = count++;

            for (int w : G.adj(v)) {
                if (--indegree[w] == 0)
                    queue.offer(w);
            }
        }

        if (count != G.V())
            order = null;
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public int rank(int v) {
        if (isDAG())
            return rank[v];
        else
            return -1;
    }

    public static void main(String[] args) {
        String filePath = DirectedDFS.class.getClassLoader().getResource("jobs.txt").getPath();
        SymbolDigraph sg = new SymbolDigraph(filePath, "/");
        TopologicalX top = new TopologicalX(sg.digraph());
        for (int v : top.order) {
            StdOut.println(sg.nameOf(v));
        }
    }
}
