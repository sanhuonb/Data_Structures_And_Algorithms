package com.jy.queue;

/**
 * 用链表实现栈
 */
public class QueueBasedOnLinkedList {
    private Node head;
    private Node tail;

    /**
     * 入队
     * @param values
     * @return
     */
    public void enqueue(String values){
        if (tail == null){
            Node newNode = new Node(values, null);
            head = newNode;
            tail = newNode;
        } else {
            tail.next = new Node(values, null);
            tail = tail.next;
        }
    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        if (head == null){
            System.out.println("队空，无法出队");
            return null;
        }

        String result = head.getData();
        head = head.next;
        if (head == null){
            tail = null;
        }
        return result;
    }

    public void printAll(){
        Node p = head;
        if (p == null){
            System.out.println("队空");
            return;
        }
        while (p != null){
            System.out.print(p.getData() + " ");
            p = p.next;
        }
        System.out.println();
    }

    private static class Node{
        private String data;
        private Node next;

        public Node(String data, Node next){
            this.data = data;
            this.next = next;
        }
        public String getData(){
            return this.data;
        }
    }

}
