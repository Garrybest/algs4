package chapter2;

import chapter1.RecursiveBinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 插入排序算法
 *
 * @Author: Fang Rui
 * @Date: 2018/5/22
 * @Time: 10:58
 */
public class Insertion {

    public static void sort(Comparable[] array, int low, int high) {
        if (low >= high)
            return;
        for (int i = low + 1; i < high + 1; i++) {
            Comparable insertElement = array[i];
            int j = i;
            for (; j > low && less(insertElement, array[j - 1]); j--)
                array[j] = array[j - 1]; // 元素往右移一位
            array[j] = insertElement;
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
        String filePath = Insertion.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Insertion.sort(array, 0, array.length - 1);
        assert Insertion.isSorted(array);
        Insertion.show(array);
    }

}
