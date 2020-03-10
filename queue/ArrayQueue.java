package com.jy.queue;

public class ArrayQueue {
    private String[] items;
    private int n = 0; // 数组大小
    private int head = 0; // 队头下标
    private int tail = 0; // 队尾下标

    public ArrayQueue(int capacity){
        items = new String[capacity];
        this.n = capacity;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public boolean enqueue(String item){
        if (tail == n){
            System.out.println("队满，无法插入");
            return false;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    /**
     * 出队
     * @return String
     */
    public String dequeue(){
        if (head == tail){
            System.out.println("队空，无法出队");
            return null; // 队空时返回 null
        }
        String result = items[head];
        head++;
        return result;
    }

    public void printAll(){
        for (int i = head; i < n; i++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

}
