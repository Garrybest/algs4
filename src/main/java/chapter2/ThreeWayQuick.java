package chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 三向切分快速排序
 *
 * @Author: Fang Rui
 * @Date: 2018/7/2
 * @Time: 16:07
 */
public class ThreeWayQuick {
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

        Comparable v = array[low];
        int lt = low, gt = high, i = low + 1;
        while (i <= gt) {
            int cmp = array[i].compareTo(v);
            if (cmp < 0)
                SortUtil.swap(array, lt++, i++);
            else if (cmp > 0)
                SortUtil.swap(array, gt--, i);
            else
                i++;
        }


        sort(array, low, lt - 1);
        sort(array, gt + 1, high);
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = ThreeWayQuick.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Merge.sort(array);
        assert SortUtil.isSorted(array);
        SortUtil.show(array);
    }
}
