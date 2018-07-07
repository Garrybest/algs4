package chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 *
 * @Author: Fang Rui
 * @Date: 2018/7/2
 * @Time: 10:52
 */
public class Quick {
    public static void sort(Comparable[] array) {
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, int low, int high) {
        // 对于小数组使用插入排序比较好
        if (high - low <= SortUtil.M) {
            Insertion.sort(array, low, high);
            return;
        }
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    private static int partition(Comparable[] array, int low, int high) {
        int left = low;
        int right = high + 1;
        while (true) {
            while (SortUtil.less(array[++left], array[low])) {
                if (left == high)
                    break;
            }
            while (SortUtil.less(array[low], array[--high])) ; // array[low]充当哨兵的角色，因为它不可能比自己小
            if (left >= right)
                break;
            SortUtil.swap(array, left, right);
        }
        SortUtil.swap(array, low, right);
        return right;
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = Quick.class.getClassLoader().getResource("1Kints.txt").getPath();
        In in = new In(filePath);

        int[] a = in.readAllInts();
        Integer[] array = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            array[i] = a[i];
        }
        Insertion.sort(array, 0, array.length - 1);
        assert SortUtil.isSorted(array);
        SortUtil.show(array);
    }
}
