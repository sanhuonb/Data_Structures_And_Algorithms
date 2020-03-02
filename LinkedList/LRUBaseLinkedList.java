package com.jy.LinkedList;

import com.jy.Array.LRUBaseArray;
import jdk.jshell.Snippet;

import javax.xml.crypto.Data;

public class LRUBaseLinkedList<T> {
    private final static Integer DEFAULT_CAPACITY = 10;
    private SNode<T> headNode; // 头结点
    private Integer length; //链表长度
    private Integer capacity; //链表容量

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public LRUBaseLinkedList(int capacity) {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = capacity;
    }

    public void access_data(T object) {
        SNode pre = findPreNode(object);

        if (pre == null){
            if (length >= capacity){
                deleteEndNode();
            }
            insertIntoHead(object);
        }else {
            deleteAfterNode(pre);
            insertIntoHead(object);
        }

    }

    // 输出链表全部内容
    public void printAll(){
        SNode node = headNode.getNext();
        while (node != null){
            System.out.println(node.getElement() + ", ");
            node = node.getNext();
        }
        System.out.println();
    }

    public void deleteAfterNode(SNode node){
        SNode nextNode = node.getNext();
        node.setNext(nextNode.getNext());
        nextNode = null;
        length--;
    }

    /**
     * 将元素插入表头
     * @param object
     */
    public void insertIntoHead(T object){
        SNode newNode = new SNode(object);
        newNode.next = headNode.getNext();
        headNode.setNext(newNode);
        length++;
    }
    /**
     * 删除链表尾部节点
     */
    public void deleteEndNode(){
        SNode node = headNode;

        if (node.getNext() == null){ // 如果链表为空，直接返回
            return;
        }
        while (node.getNext().getNext() != null){ //得到倒数第二个节点
            node = node.getNext();
        }
        SNode temp = node.getNext();
        node.setNext(null);
        temp = null;
        length--;

    }
    /**
     * 找到某个元素的前一个节点
     * @param element
     * @return SNode 传入元素的前一个节点
     */
    public SNode findPreNode(T element) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (element.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public class SNode<T> {
        private T element;
        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList<Integer> lru = new LRUBaseLinkedList<Integer>(4);
        lru.access_data(1);
        lru.access_data(2);
        lru.access_data(3);
        lru.access_data(4);
        lru.printAll();
        lru.access_data(2);
        lru.printAll();
        lru.access_data(5);
        lru.printAll();

    }
}
