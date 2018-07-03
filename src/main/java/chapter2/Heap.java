package chapter2;

import edu.princeton.cs.algs4.In;

/**
 * 堆排序
 *
 * @Author: Fang Rui
 * @Date: 2018/7/3
 * @Time: 9:28
 */
public class Heap {
    private static void sink(Comparable[] array, int k) {
        while (2 * k + 1 <= array.length - 1) {
            int j = 2 * k + 1;
            if (j < array.length - 1 && SortUtil.less(array[j], array[j + 1])) // 说明存在j+1
                j++;
            if (SortUtil.less(array[k], array[j]))
                SortUtil.swap(array, k, j);
            k = j;
        }
    }

    private static void sink(Comparable[] array, int k, int N) {
        while (2 * k + 1 <= N - 1) {
            int j = 2 * k + 1;
            if (j < N - 1 && SortUtil.less(array[j], array[j + 1])) // 说明存在j+1
                j++;
            if (SortUtil.less(array[k], array[j]))
                SortUtil.swap(array, k, j);
            k = j;
        }
    }

    private static void swim(Comparable[] array, int k) {
        while (k > 0) {
            int j = (k - 1) / 2;
            if (SortUtil.less(array[j], array[k]))
                SortUtil.swap(array, j, k);
            k = j;
        }
    }

    public static void sort(Comparable[] array) {
        int N = array.length;
        for (int i = (N - 2) / 2; i >= 0; i--)
            sink(array, i, N);
        for (int i = N - 1; i >= 0; i--) {
            SortUtil.swap(array, 0, i);
            sink(array, 0, --N);
        }
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = Insertion.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Merge.sort(array);
        assert SortUtil.isSorted(array);
        SortUtil.show(array);
    }

}
