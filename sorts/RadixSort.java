package com.jy.sorts;

/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] test = {10, 80, 5, 63, 3, 23, 14, 4, 73, 9};
        radixSort(test);
        for (int i : test){
            System.out.println(i);
        }
    }
    public static void radixSort(int[] a){
        int max = a[0];
        for (int i = 0; i < a.length; i++){
            if (a[i] > max){
                max = a[i];
            }
        }
        for (int exp = 1; max / exp > 0; exp *= 10){
            countingSort(a, exp);
        }
    }

    /**
     * 计数排序 - 对数组按照 “某个位数” 进行排序
     * 例如排序 123 和 456，先从个位排 3 和 6，再比较十位的 2 和 5
     * @param a
     * @param exp
     */
    private static void countingSort(int[] a, int exp) {
        if (a.length <= 1){
            return;
        }

        /**
         * 计算每个元素的个数
         * 跟计数排序差不多，区别是计数排序 C 数组的个数是数组中最大值 max 然后 +1
         */
        int[] c = new int[10];
        for (int i = 0; i < a.length; i++){
            c[(a[i] / exp) % 10]++; // 求数的某位的元素是多少，例如求 123 的十位是 2
        }

        // 依次累加
        for (int i = 1; i < c.length; i++){
            c[i] = c[i] + c[i - 1];
        }

        // 临时数组 r, 存储排序之后的结果
        int[] r = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--){
            int index = c[(a[i] / exp) % 10] - 1;
            r[index] = a[i];
            c[(a[i] / exp) % 10]--;
        }
        for (int i = 0; i < a.length; i++){
            a[i] = r[i];
        }
    }

}
