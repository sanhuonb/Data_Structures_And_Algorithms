package com.jy.skiplist;

import javafx.scene.transform.Shear;

import java.lang.ref.Cleaner;
import java.util.Arrays;

/**
 * 调表的一种实现方法
 * 调表中存储的是正整数，并且存储的是不重复的
 */
public class SkipList {
    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;
    private Node head = new Node(); // 带头链表

    public Node find(int value){
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data == value){
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value){
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node[] update = new Node[level];
        for (int i = level - 1; i >= 0; i--){
            update[i] = head;
        }
        Node p = head;

        //找到第 i 层索引的插入位置，将插入位置前面的结点保存到 update 数组
        for (int i = level - 1; i >= 0; i--){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }
        for (int i = level - 1; i >= 0; i--){
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }
        if (levelCount < level){
            levelCount = level;
        }
    }

    public void delete(int value){
    Node[] update = new Node[levelCount];
    Node p = head;
    for (int i = levelCount - 1; i >= 0; i--){
        while (p.forwards[i] != null && p.forwards[i].data < value){
            p = p.forwards[i];
        }
        update[i] = p;
    }
    if (p.forwards[0] != null && p.forwards[0].data == value){
        for (int i = levelCount - 1; i >= 0; i--){
            if (update[i].forwards[i] != null && update[i].forwards[i].data == value){
                update[i].forwards[i] = update[i].forwards[i].forwards[i];
            }
        }
    }
    while (levelCount > 1 && head.forwards[levelCount] == null){
        levelCount--;
    }
    }

    private int randomLevel(){
        int level = 1;
        while (Math.random() <SKIPLIST_P && level < MAX_LEVEL){
            level = level + 1;
        }
        return level;
    }

    public void printAll(){
        Node p = head;
        while (p.forwards[0] != null){
            System.out.println(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public class Node{
        private int data = -1;
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }
}
