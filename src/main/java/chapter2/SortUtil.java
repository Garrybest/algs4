package chapter2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 排序算法的公用方法
 *
 * @Author: Fang Rui
 * @Date: 2018/7/2
 * @Time: 10:45
 */
public class SortUtil {

    // 判断是否是小数组的阈值
    public static final int M = 10;

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // 交换两个局部变量是没用的
    public static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void show(Comparable[] array) {
        for (Comparable anArray : array) StdOut.print(anArray + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1]))
                return false;
        }
        return true;
    }
}
