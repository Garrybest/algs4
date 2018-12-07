package chapter4;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;

/**
 * dfs实现的拓扑排序
 *
 * @Author: Fang Rui
 * @Date: 2018/12/6
 * @Time: 22:06
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String filePath = DirectedDFS.class.getClassLoader().getResource("jobs.txt").getPath();
        SymbolDigraph sg = new SymbolDigraph(filePath, "/");
        Topological top = new Topological(sg.digraph());
        for (int v : top.order) {
            StdOut.println(sg.nameOf(v));
        }
    }
}
