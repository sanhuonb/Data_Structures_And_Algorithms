package com.jy.sorts;

public class Sorts {
    public static void main(String[] args) {
        int[] temp = {2, 1, 4, 5, 9, 8, 6, 7, 3};
        selectionSort(temp);
        for (int i : temp){
            System.out.println(i + " ");
        }
    }

    /**
     * 冒泡排序
     * @param a
     */
    public static void bubbleSort(int[] a){
        int n = a.length;// 数组长度
        if (n <= 1) return;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n - 1 - i; j++){
                if (a[j] > a[j + 1]){
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序改进：在每一轮排序后记录最后一次元素交换的位置，作为下次比较的边界，边界外的元素无需比较
     * @param a
     */
    public static void bubbleSort2(int[] a){
        int n = a.length; // 数组长度
        if (n <= 1) return;
        int lastExchange = 0; // 最后一次交换的位置
        int sortBorder = n - 1; // 无序数据的边界，边界外的数据已经排好序，无需再比较
        for (int i = 0; i < n; i++){
            boolean flag = false; // 提前退出标志位
            for (int j = 0; j < sortBorder; j++){
                if (a[j] > a[j + 1]){
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    lastExchange = j;
                    flag = true;
                }
            }
            sortBorder = lastExchange;
            if (!flag) return; // 如果没有数据交换，提前退出程序
        }
    }

    /**
     * 插入排序，a 代表数组
     * @param a
     */
    public static void insertionSort(int[] a){
        int n = a.length; // 数组长度
        if (n <= 1)
            return;
        for (int i = 1; i < n; i++){
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--){
                if (a[j] > value){ // 此处必须是value，即比插入的数大的数都要往后移，不能是a[i]
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }

    /**
     * 选择排序
     * @param a
     */
    public static void selectionSort(int[] a){
        int n = a.length;
        if (n <= 1) return;
        for (int i = 0; i < n - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < n; j++){ //寻找最小值
                if (a[j] < a[minIndex]){
                    minIndex = j;
                }
            }
            //交换
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }
}
