package data.agency.guo;

/**
 * @Description:
 * @author: guofengbo
 * @date: 2020-06-23 23:16
 **/
public class 选择排序 {

    public static int[] changeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        // 找到最小的 放到指定位置
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
            if (arr[min] > arr[j]) {
                min = j;
            }
        }
        if (min != i) {
            int tmp = arr[min];
            arr[min] = arr[i];
            arr[i] = tmp;
        }
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i : changeSort(new int[]{7, 6, 4, 3, 5})) {
            System.out.println(i);
        }
    }

}
