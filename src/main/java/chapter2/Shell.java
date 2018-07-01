package chapter2;

import chapter1.RecursiveBinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 希尔排序
 *
 * @Author: Fang Rui
 * @Date: 2018/7/1
 * @Time: 15:30
 */
public class Shell {
    public static void sort(Comparable[] array) {
        int N = array.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1; // 将数组三等分，h为第二部分数组的第一个元素
        while (h >= 1) {
            // 第一部分数组每个元素已经有序了，从第二部分开始进行排序
            for (int i = h; i < N; i++) {
                Comparable insertElement = array[i];
                int j = i;
                for (; j >= h && less(insertElement, array[j-h]); j -= h) {
                    array[j] = array[j - h];
                }
                array[j] = insertElement;
            }
            h /= 3;
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void show(Comparable[] array) {
        for (Comparable anArray : array)
            StdOut.print(anArray + " ");
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
        String filePath = Shell.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Shell.sort(array);
        assert Shell.isSorted(array);
        Shell.show(array);
    }
}
