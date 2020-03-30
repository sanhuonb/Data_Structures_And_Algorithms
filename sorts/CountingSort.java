package com.jy.sorts;

/**
 *  计数排序
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] test = {10000, 8050, 9050, 7020, 5080, 6210, 2100, 3200, 1000, 400};
        countingSort(test);
        for (int i : test){
            System.out.println(i + " ");
        }
    }
    /**
     * 计数排序，a是数组,假设数组中存储的都是非负整数
     * @param a
     */
    public static void countingSort(int[] a){
        int n = a.length; // 数组大小
        if (n <= 1) return;

        //查找数组中数据的范围
        int max = a[0];
        for (int i = 0; i < n; i++){
            if (a[i] > max){
                max = a[i];
            }
        }
        int[] c = new int[max +1];

        // 计算每个元素的个数，并记录到 c 中
        for (int i = 0; i < n; i++){
            c[a[i]]++;
        }

        // 依次累加
        for (int i = 1; i < max + 1; i++){
            c[i] = c[i] + c[i - 1];
        }

        // 临时数组 r，存储排序后的结果
        int[] r = new int[n];
        for(int i = n - 1; i >= 0; i--){
            int index = c[a[i]] - 1;
            r[index] = a[i];
            c[a[i]]--;
        }

        // 将结果拷贝回 a 数组
        for (int i = 0; i < n; i++){
            a[i] = r[i];
        }
    }
}
