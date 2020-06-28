package com.guo;

/**
 * @Description:
 * @author: guofengbo
 * @date: 2020-06-20 23:39
 **/
public class 冒泡排序 {

    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        // 把最大的放最后 所以从最后一位开始循环
        for (int end = arr.length - 1; end > 0; end--) {
            //从第一个开始循环比较大小
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int a, int b) {
        int c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }

    public static void main(String[] args) {
        for (int i : bubbleSort(new int[]{1, 6, 4, 3, 5})) {
            System.out.println(i);
        }
    }

}
