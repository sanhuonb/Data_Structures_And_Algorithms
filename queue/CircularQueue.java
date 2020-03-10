package com.jy.queue;

/**
 * 循环队列
 */
public class CircularQueue {
    private String[] items;
    private int n;
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity){
        items = new String[capacity];
        this.n = capacity;
    }

    /**
     * 入队
     * @param values
     * @return
     */
    public boolean enqueue(String values){
        if (((tail + 1) % n) == head){ // 循环队列会有一个空间无法插入数据
            System.out.println("队满，无法插入");
            return false;
        }
        items[tail] = values;
        tail = (tail + 1) % n; // 队尾的位置应当考虑超过循环链表一周后的位置
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        if (head == tail){
            System.out.println("队空，无法出队");
            return null;
        }
        String result = items[head];
        head = (head + 1) % n;
        return result;
    }
    public void printAll(){
        for (int i = head; i % n != tail; i = (i + 1) % n){ // 注意循环条件
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
}
