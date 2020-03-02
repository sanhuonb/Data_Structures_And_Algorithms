package com.jy.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SpecialYang in 2018/12/7 2:00 PM.
 * <p>
 * 基于数组实现的LRU缓存
 * 1. 空间复杂度为O(n)
 * 2. 时间复杂度为O(n)
 * 3. 不支持null的缓存
 * <p>
 * 用来学习
 */

public class LRUBaseArray<T> {
    private static final int DEFAULT_CAPACITY = (1 << 3);
    private int capacity;
    private int count;
    private T[] value;
    private Map<T, Integer> holder;

    public LRUBaseArray(int capacity) {
        this.capacity = capacity;
        value = (T[]) new Object[capacity];
        count = 0;
        holder = new HashMap<T, Integer>(capacity);
    }

    public LRUBaseArray() {
        this(DEFAULT_CAPACITY);
    }

    //模拟 LRU 访问某个数据
    public void access_data(T object) {
        if (object == null) {
            throw new IllegalArgumentException("该缓存容易不支持null !");
        }
        Integer index = holder.get(object);
        if (index == null) { // 判断传入的数据是否在数组内
            if (isFull()) { // 如果数组内没有该数据并且数组已满, 则将链表尾部节点删除，并将该数据插到数组头部
                removeAndCache(object);
            } else { // 如果数组未满，则直接将该节点查到表头
                cache(object, count);
            }
        } else { //如果数组存在该数据，则找到这个数据的节点，从原来的位置删除，并插入数组头部
            update(index);
        }
    }

    /**
     * 若缓存中有该数据，则更新数据位置
     * @param index
     */
    public void update(Integer index) {
        T target = value[index];
        rightShift(index);
        value[0] = target;
        holder.put(target, 0);
    }

    /**
     * 缓存满的时候，先移除数组尾部元素，再将数据插入表头
     *
     * @param object
     */
    public void removeAndCache(T object) {
        T key = value[--count];
        holder.remove(key);
        cache(object, count);
    }

    /**
     * 先将数据右移，再缓存数据到头部
     *
     * @param object
     * @param end    数组右移边界
     */
    public void cache(T object, int end) {
        rightShift(end);
        value[0] = object;
        holder.put(object, 0);
        count++;
    }

    /**
     * 将数组的数据右移一位
     *
     * @param end
     */
    private void rightShift(int end) {
        for (int i = end - 1; i >= 0; i--) {
            value[i + 1] = value[i];
            holder.put(value[i], i + 1);
        }
    }

    public boolean isContain(T object){
        return holder.containsKey(object);
    }

    public boolean isFull() {
        return count == capacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++){
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    static class TestLRUBaseArray{
        public static void main(String[] args) {
            testSpecifiedConstructor(4);
        }
        private static void testWithException(){
            LRUBaseArray<Integer> lru = new LRUBaseArray<Integer>();
            lru.access_data(null);
        }
        private static void testDEAFAULTConstructor(){
            System.out.println("--------无参测试--------");
            LRUBaseArray<Integer> lru = new LRUBaseArray<>();
            lru.access_data(1);
            lru.access_data(2);
            lru.access_data(3);
            lru.access_data(4);
            lru.access_data(5);
            System.out.println(lru);
            lru.access_data(6);
            lru.access_data(7);
            lru.access_data(8);
            lru.access_data(9);
            System.out.println(lru);
        }
        private static void testSpecifiedConstructor(int capacity){
            System.out.println("--------有参测试--------");
            LRUBaseArray<Integer> lru = new LRUBaseArray<>(capacity);
            lru.access_data(1);
            System.out.println(lru);
            lru.access_data(2);
            System.out.println(lru);
            lru.access_data(3);
            System.out.println(lru);
            lru.access_data(4);
            System.out.println(lru);
            lru.access_data(2);
            System.out.println(lru);
            lru.access_data(4);
            System.out.println(lru);
            lru.access_data(7);
            System.out.println(lru);
            lru.access_data(1);
            System.out.println(lru);
            lru.access_data(2);
            System.out.println(lru);
        }

    }

}
