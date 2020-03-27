package com.jy.sorts;


public class BucketSort {

    public static void main(String[] args) {
        int[] test = {10, 8, 9, 7, 5, 6, 3, 2, 4, 1};
        bucketSort(test, 2);
        for (int i : test){
            System.out.println(i + "  ");
        }
    }

    /**
     * 桶排序
     * @param arr 数组
     * @param bucketSize 桶容量：每个桶的容量
     */
    public static void bucketSort(int[] arr, int bucketSize){
        if (arr.length < 2){
            return;
        }

        //找出数组中最大最小值
        int minValue = arr[0];
        int maxValue = arr[1];
        for (int i = 0; i < arr.length; i++){
            if (arr[i] < minValue){
                minValue = arr[i];
            } else if (arr[i] > maxValue){
                maxValue = arr[i];
            }
        }

        int bucketCount = (maxValue - minValue) / bucketSize + 1; // 桶的数量
        int[][] buckets = new int[bucketCount][bucketSize];

        /**
         * 这是很巧妙的一个数，即是某个元素在桶中插入的位置，也记录每个桶的数据个数
         * 下标代表桶的位置，数组的值代表桶里元素的个数。
         */
        int[] indexArr = new int[bucketCount];

        //将数组中的值分配到各个桶里
        for (int i = 0; i < arr.length; i++){
            int bucketIndex = (arr[i] - minValue) / bucketSize;
            if (indexArr[bucketIndex] == buckets[bucketIndex].length){ // 如果要存入的桶满了
                ensureCapacity(buckets, bucketIndex); // 扩容, 哪个桶的容量满了扩哪个
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }

        //对每个桶进行排序，这里使用快排序
        int k = 0;
        for(int i = 0; i < buckets.length; i++){
            if (indexArr[i] == 0){ // indexArr[i] 代表第 i 个桶有多少元素，没有元素则跳过
                continue;
            }
            quickSortC(buckets[i], 0, indexArr[i] - 1);
            //将桶中的排好序的数据拷贝回原数组
            for (int j = 0; j < indexArr[i]; j++){
                arr[k++] = buckets[i][j];
            }
        }
    }

    /**
     * 快速排序递归函数
     * @param arr
     * @param p
     * @param r
     */
    private static void quickSortC(int[] arr, int p, int r) {
        if (p >= r){
            return;
        }
        int q = partition(arr, p, r);
        quickSortC(arr, p, q - 1);
        quickSortC(arr, q + 1, r);
    }

    /**
     * 分区函数
     * @param arr
     * @param p
     * @param r
     * @return 分区点位置
     */
    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++){
            if (arr[j] <= pivot){
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 数组扩容
     * @param buckets
     * @param bucketIndex
     */
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2]; // 扩大为原来大小的 2 倍

        for (int i = 0; i < tempArr.length; i++){
            newArr[i] = tempArr[i];
        }
        buckets[bucketIndex] = newArr;
    }
}
