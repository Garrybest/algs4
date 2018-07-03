package chapter2;

import edu.princeton.cs.algs4.In;

/**
 * 插入排序
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
            for (; j > low && SortUtil.less(insertElement, array[j - 1]); j--)
                array[j] = array[j - 1]; // 元素往右移一位
            array[j] = insertElement;
        }
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = Insertion.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Insertion.sort(array, 0, array.length - 1);
        assert SortUtil.isSorted(array);
        SortUtil.show(array);
    }
}
