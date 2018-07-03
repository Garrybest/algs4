package chapter2;

import chapter1.RecursiveBinarySearch;
import edu.princeton.cs.algs4.In;


/**
 * 选择排序
 *
 * @Author: Fang Rui
 * @Date: 2018/5/22
 * @Time: 10:10
 */
public class Selection {

    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (SortUtil.less(array[j], array[min]))
                    min = j;
            }
            SortUtil.swap(array, min, i);
        }
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = RecursiveBinarySearch.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Selection.sort(array);
        assert SortUtil.isSorted(array);
        SortUtil.show(array);
    }
}
