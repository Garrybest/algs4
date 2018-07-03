package chapter2;

import edu.princeton.cs.algs4.In;

/**
 * 希尔排序
 *
 * @Author: Fang Rui
 * @Date: 2018/7/1
 * @Time: 15:30
 */
public class Shell {
    public static void sort(Comparable[] array) {
        int N = array.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1; // 将数组三等分，h为第二部分数组的第一个元素
        while (h >= 1) {
            // 第一部分数组每个元素已经有序了，从第二部分开始进行排序
            for (int i = h; i < N; i++) {
                Comparable insertElement = array[i];
                int j = i;
                for (; j >= h && SortUtil.less(insertElement, array[j - h]); j -= h) {
                    array[j] = array[j - h];
                }
                array[j] = insertElement;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        // 读取文件
        String filePath = Shell.class.getClassLoader().getResource("words3.txt").getPath();
        In in = new In(filePath);

        String[] array = in.readAllStrings();
        Shell.sort(array);
        assert SortUtil.isSorted(array);
        SortUtil.show(array);
    }
}
