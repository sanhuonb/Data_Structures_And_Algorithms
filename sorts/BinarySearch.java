package com.jy.sorts;

/**
 * 二分查找法，包括二分查找法的递归实现和算法的优化
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        int index = bSearch(test, 8);
        System.out.println("index = " + index);
    }

    /**
     *  二分查找法
     * @param a 数组
     * @param value 查找的值
     * @return
     */
    public static int BinarySearch(int[] a, int value){
        int n = a.length; // 数组长度
        int low = 0;
        int high = n - 1;

        while (low <= high){ // 注意必须是 low <= high,而不是 low < high
            int mid = low + ((high - low) >> 1); // (low + high) / 2 的写法可能导致溢出；移位比除以2效率高
            if (a[mid] == value){
                return mid;
            } else if (a[mid] < value){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 递归实现二分查找
     * @param a 数组
     * @param value 查找的值
     * @return
     */
    public static int bSearch(int[] a, int value){
        return BinarySearchInternally(a, 0, a.length - 1, value);
    }

    private static int BinarySearchInternally(int[] a, int low, int high, int value){
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (a[mid] == value){
            return mid;
        } else if (a[mid] < value){
            return BinarySearchInternally(a, mid + 1, high, value);
        } else {
            return BinarySearchInternally(a, low, mid - 1, value);
        }
    }
}
