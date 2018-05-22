package chapter2;

import chapter1.RecursiveBinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Description: 插入排序算法
 * @Author: Fang Rui
 * @Date: 2018/5/22
 * @Time: 10:58
 */
public class Insertion {

    public static void sort(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            Comparable insertElement = array[i];
            int j = i;
            for (; j > 0 && less(insertElement, array[j - 1]); j--)
                array[j] = array[j - 1]; // 元素往右移一位
            array[j] = insertElement;
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
        Insertion.sort(array);
        assert Insertion.isSorted(array);
        Insertion.show(array);
    }

}
