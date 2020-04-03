package com.jy.sorts;

/**
 * 四种常见的二分查找的变形的代码
 * 1.查找第一个值等于给定值的元素
 * 2.查找最后一个值等于给定值的元素
 * 3.查找第一个大于等于给定值的元素
 * 4.查找最后一个小于等于给定值的元素
 */
public class MoreBinarySearch {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 6, 6, 7, 8, 9};
        int result = bSearch4(a, 6);
        System.out.println(result);
    }
    /**
     * 查找第一个值等于给定值的元素
     * 每次 mid 位置的值与要查找元素的比较有 3 种情况：大于、小于或者等于。
     * 在 mid 的值与要查找元素相等时，如果 mid 是数组首位元素或者 mid 之前的元素
     * 不等于要查找的元素，则 mid 就是要查到的元素
     * 如果 mid 之前的元素等于要查找的元素，则要查找的元素的区间在 [low, mid - 1] 之间，继续循环
     */
    public static int bSearch1(int[] a, int value){
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = low + ((high - low) >> 2);
            if (a[mid] > value){
                high = mid - 1;
            } else if (a[mid] < value){
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) return mid; //如果 mid 是数组第一个元素或者 mid 前面的数不等于查找的数，则 mid 就是要查找的元素
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素，原理和代码与上一个差不多
     * @param a
     * @param value
     * @return
     */
    public static int bSearch2(int[] a, int value){
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value){
                low = mid + 1;
            } else if (a[mid] > value){
                high = mid - 1;
            } else {
                if (mid == a.length - 1 || a[mid + 1] != value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int bSearch3(int[] a, int value){
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value){
                if (mid == 0 || a[mid - 1] < value) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int bSearch4(int[] a, int value){
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = low + ((high - low) >>1);
            if (a[mid] > value){
                high = mid - 1;
            } else {
                if (mid == a.length - 1 || a[mid + 1] > value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
}
