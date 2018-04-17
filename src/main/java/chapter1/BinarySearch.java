package chapter1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @Description: 二分查找
 * @Author: Fang Rui
 * @Date: 2018/4/17
 * @Time: 10:04
 */
public class BinarySearch {

    public static int rank(int key, int[] whiteList) {
        int low = 0;
        int high = whiteList.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key > whiteList[mid]) low = mid + 1;
            else if (key < whiteList[mid]) high = mid - 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {

        // 读取文件
        String filePath = BinarySearch.class.getClassLoader().getResource("tinyW.txt").getPath();
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
