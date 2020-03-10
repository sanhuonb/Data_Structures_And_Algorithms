package com.jy.queue;

/**
 * 队满时会自动进行数据搬移，无需重新创建数组空间
 */
public class DynamicArrayQueue {
    private String[] items;
    private int n; // 数组容量
    private int head = 0;
    private int tail = 0;

    public DynamicArrayQueue(int capacity){
        items = new String[capacity];
        this.n = capacity;
    }

    //入队操作，队满时进行数据搬移
    public boolean enqueue(String values){
        if (tail == n){ // 表示队尾没空间了
            if (head == 0){ // 队尾没空间并且 head == 0 时，整个队列都满了，无法进行数据搬移
                System.out.println("队没空间，无法插入");
                return false;
            }
            for (int i = head; i < tail; i++){
                items[i - head] = items[i];
            }
            //搬移完数据后更改队头队尾的下标
            tail = tail - head;
            head = 0;
        }
        items[tail] = values;
        tail++;
        return true;
    }

    public String dequeue(){
        if (head == tail){
            System.out.println("队空，无法出队");
            return null;
        }
        String result = items[head];
        head++;
        return result;
    }

    public void printAll(){
        if (head == tail){
            System.out.println("队空");
            return;
        }
        for (int i = head; i < tail; i++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

}
