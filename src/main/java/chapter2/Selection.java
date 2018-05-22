package chapter2;

import chapter1.RecursiveBinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Description: 选择排序算法
 * @Author: Fang Rui
 * @Date: 2018/5/22
 * @Time: 10:10
 */
public class Selection {

    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array[j], array[min]))
                    min = j;
            }
            Selection.exch(array, min, i);
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // 交换两个局部变量是没用的
    private static void exch(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void show(Comparable[] array) {
        for (int i = 0; i < array.length; i++)
            StdOut.print(array[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = RecursiveBinarySearch.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Selection.sort(array);
        assert Selection.isSorted(array);
        Selection.show(array);
    }
}
