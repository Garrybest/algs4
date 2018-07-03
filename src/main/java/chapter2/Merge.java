package chapter2;

import edu.princeton.cs.algs4.In;

/**
 * 归并排序
 *
 * @Author: Fang Rui
 * @Date: 2018/7/1
 * @Time: 16:41
 */
public class Merge {
    private static Comparable[] aux;

    private static void merge(Comparable[] array, int low, int mid, int high) {
        if (SortUtil.less(array[mid], array[mid + 1]))
            return;

        int i = low;
        int j = mid + 1;

        System.arraycopy(array, low, aux, low, high + 1 - low);

        for (int k = low; k <= high; k++) {
            if (i > mid)
                array[k] = aux[j++];
            else if (j > high)
                array[k] = aux[i++];
            else if (SortUtil.less(aux[i], aux[j]))
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
        if (high - low + 1 <= SortUtil.M)
            Insertion.sort(array, low, high);
        else {
            int mid = (low + high) / 2;
            sort(array, low, mid);
            sort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = Shell.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Merge.sort(array);
        assert SortUtil.isSorted(array);
        SortUtil.show(array);
    }
}
