package com.jy.sorts;

public class MergeSort {
    public static void main(String[] args) {
        int[] a = {5, 4, 2, 1, 3, 7, 6, 8, 9};
        mergeSort(a);
        for (int i : a){
            System.out.println(i + " ");
        }
    }
    //归并排序 算法，a 是数组
    public static void mergeSort(int[] a){
        int n = a.length; // 数组长度
        mergeSortInternally(a, 0, n - 1);
    }

    //递归调用函数
    private static void mergeSortInternally(int[] a, int p, int r){
        if (p >= r) return; // 终止条件
        int q = p + (r - p) / 2; // 取 p 到 r 之间的中点位置 q，防止(p+r)的和超过int类型最大值
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q + 1, r);
        merge(a, p, q, r); // 将a[p....q]和a[q+1....r]合并为 A[p....r]
    }
    private static void merge(int[] a, int p, int q, int r){
        int i = p;
        int j = q + 1;
        int k = 0;
        int[] temp = new int[r - p + 1]; // 申请一个大小跟a[p...r]一样的临时数组
        while (i <= q && j <= r){
            if (a[i] <= a[j]){
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        //判断哪个子数组中有剩余的数据
        int start = i;
        int end = q;
        if (j <= r){
            start = j;
            end = r;
        }

        //将剩余的数据拷贝到临时数组temp
        while (start <= end){
            temp[k++] = a[start++];
        }

        // 将 temp 中的数据拷贝回 a[p...r]
        for (i = 0; i <= r - p; i++){
            a[p + i] = temp[i];
        }
    }

    private static void mergeBySentry(int[] a, int p, int q, int r){
        int[] leftArr = new int[q - p + 2];
        int[] rightArr = new int[r - q + 1];

        // 将数组左边元素拷贝到临时数组
        for (int i = 0; i  <= q - p; i++){
            leftArr[i] = a[p + i];
        }
        leftArr[q - p + 1] = Integer.MAX_VALUE; // 第一个数组添加哨兵（最大值）

        //将数组右边元素拷贝到临时数组
        for (int i = 0; i < r - q; i++){
            rightArr[i] = a[q + 1 + i];
        }
        rightArr[r - q] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int k = p;
        while (k <= r){
            if (leftArr[i] <= rightArr[j]){
                a[k++] = leftArr[i++];
            } else {
                a[k++] = rightArr[j++];
            }
        }
    }
    
}
