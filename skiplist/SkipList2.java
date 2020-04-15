package com.jy.skiplist;

import java.util.Random;

/**
 * 优化调表插入
 * Author：ldb
 */
public class SkipList2 {
    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;
    private Node head = new Node(MAX_LEVEL);
    private Random r = new Random();

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
        int level = head.forwards[0] == null ? 1 : randomLevel();
        if (level > levelCount){
            level = ++levelCount;
        }
        Node newNode = new Node(level);
        newNode.data = value;
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i){
            update[i] = head;
        }
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            if (level > i){
                update[i] = p;
            }
        }
        for (int i = 0; i < level; ++i){
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }
    }

    public void insert2(int value){
        int level = head.forwards[0] == null ? 1 : randomLevel();
        if (level > levelCount){
            level = ++levelCount;
        }
        Node newNode = new Node(level);
        newNode.data = value;
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            if (level > i){
                if (p.forwards[i] == null){
                    p.forwards[i] = newNode;
                } else {
                    Node next = p.forwards[i];
                    p.forwards[i] = newNode;
                    newNode.forwards[i] = next;
                }
            }
        }
    }

    private int randomLevel(){
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i){
            if (r.nextInt() % 2 == 1){
                level++;
            }
        }
        return level;
    }

    public class Node{
        private int data = -1;
        private Node forwards[];
        private int maxLevel = 0;
        public Node(int level){
            forwards = new Node[level];
        }
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("{data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }
}
