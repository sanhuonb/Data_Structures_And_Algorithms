package com.jy.stack;

/**
 * 基于链表实现的栈
 */
public class StackBasedOnLinkedList {
    private Node top = null;

    public void push(int value) {
        Node newNode = new Node(value, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    /**
     * -1代表栈中没有数据
     *
     * @return
     */
    public int pop() {
        if (top == null)
            return -1;
        int data = top.getData();
        top = top.next;
        return data;
    }

    /**
     * 输出栈中全部元素
     */
    public void printAll() {
        Node p = top;
        if (p == null) {
            System.out.println("栈为空");
            return;
        }
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        StackBasedOnLinkedList test = new StackBasedOnLinkedList();
        test.printAll();
        test.push(1);
        test.push(2);
        test.push(3);
        test.printAll();
        test.pop();
        test.pop();
        test.printAll();
    }
}
