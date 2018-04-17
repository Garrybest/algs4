package chapter1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @Description: 二分查找的递归实现
 * @Author: Fang Rui
 * @Date: 2018/4/17
 * @Time: 10:04
 */
public class RecursiveBinarySearch {

    public static int rank(int key, int[] whiteList) {
        return rank(key, whiteList, 0, whiteList.length - 1);
    }

    public static int rank(int key, int[] whiteList, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (key > whiteList[mid]) return rank(key, whiteList, mid + 1, high);
        else if (key < whiteList[mid]) return rank(key, whiteList, low, mid - 1);
        else return mid;
    }

    public static void main(String[] args) {

        // 读取文件
        String filePath = RecursiveBinarySearch.class.getClassLoader().getResource("tinyW.txt").getPath();
        In in = new In(filePath);
        int[] whiteList = in.readAllInts();

        Arrays.sort(whiteList);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();

            int result = rank(key, whiteList);
            if (result == -1)
                StdOut.println("查找不到该元素");
            else
                StdOut.println("降序排序后该元素索引为：" + result);
        }
    }
}
