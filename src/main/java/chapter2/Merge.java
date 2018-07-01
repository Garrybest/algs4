package chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 归并排序
 *
 * @Author: Fang Rui
 * @Date: 2018/7/1
 * @Time: 16:41
 */
public class Merge {
    private static Comparable[] aux;

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void merge(Comparable[] array, int low, int mid, int high) {
        if (less(array[mid], array[mid + 1]))
            return;

        int i = low;
        int j = mid + 1;

        System.arraycopy(array, low, aux, low, high + 1 - low);

        for (int k = low; k <= high; k++) {
            if (i > mid)
                array[k] = aux[j++];
            else if (j > high)
                array[k] = aux[i++];
            else if (less(aux[i], aux[j]))
                array[k] = aux[i++];
            else
                array[k] = aux[j++];
        }
    }

    public static void sort(Comparable[] array) {
        aux = new Comparable[array.length];
        sort(array, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, int low, int high) {
        if (low >= high)
            return;

        // 小规模数组使用插入排序
        if (high - low + 1 <= 4)
            Insertion.sort(array, low, high);
        else {
            int mid = (low + high) / 2;
            sort(array, low, mid);
            sort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
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
        Merge.sort(array);
        assert Merge.isSorted(array);
        Merge.show(array);
    }

}
