package com.jy.sorts;

/**
 * 在 O(n) 的时间复杂度内查找一个无序数组中的第 K 大元素
 */
public class KthLargest {
    public static void main(String[] args) {
        int[] temp = {7, 9, 8, 6, 4, 5, 1, 3, 2};
        int key = 1;
        System.out.println("第" + key + "大的数是： " + kthSmallest(temp, key));
    }
    public static int kthSmallest(int[] arr, int k){
        if (arr == null || arr.length < k){
            return -1;
        }
        int partition = partition(arr, 0, arr.length - 1);
        while (partition + 1 != k){
            if (partition + 1 < k){
                partition = partition(arr, partition + 1, arr.length - 1);
            } else {
                partition = partition(arr, 0, partition - 1);
            }
        }
        return arr[partition];
    }

    private static int partition(int[] arr, int p, int r){
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++){
            if (arr[j] >= pivot){ // 从大到小排序， 找的是第n大的数，如果从小到大排序，找的是第n小的数
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }

    private static void swap(int[] arr, int i, int j){
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
