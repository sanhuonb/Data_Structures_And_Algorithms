package com.jy.hashtable;

import java.security.PublicKey;

/**
 * 散列表实现
 * 原先作者代码中比较 key 使用 == 进行判断，修改为 使用 equals 函数判断
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> {
    /**
     * 散列表默认长度
     */
    private static final int DEFAULT_INITAL_CAPACITY = 8;

    /**
     * 装载因子
     */
    private static final float LOAD_FACTOR = 0.5F;
    private Entry<K, V>[] table; // 初始化散列表数组
    private int size = 0; // 实际元素数量
    private int use = 0; // 散列表索引数量

    public HashTable(){
        table = (Entry<K, V>[])new Entry[DEFAULT_INITAL_CAPACITY];
    }
    static class Entry<K, V>{
        K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value, Entry<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(K key, V value){
        int index = hash(key);
        // 位置未被引用，创建哨兵节点
        if (table[index] == null){
            table[index] = new Entry<>(null, null, null);
        }
        Entry<K, V> temp = table[index];
        //新增节点
        if (temp.next == null){
            temp.next = new Entry<>(key, value, null);
            size++;
            use++;
            //动态扩容
            if (use >= table.length * LOAD_FACTOR){
                resize();
            }
        } else { // 解决散列冲突，用链表法
            do {
                temp = temp.next;
                if (temp.key == key){
                    temp.value = value;
                    return;
                }
            }while (temp.next != null);
            Entry<K, V> temp2 = table[index].next;
            table[index].next = new Entry<>(key, value, temp2);
            size++;
        }
    }

    public void remove(K key){
        int index = hash(key);
        Entry e = table[index];
        if (e == null || e.next == null){
            return;
        }
        Entry pre;
        Entry<K, V> headNode = table[index];
        do {
            pre = e;
            e = e.next;
            if (key.equals(e.key)){
                pre.next = e.next;
                size--;
                if (headNode.next == null) use--;
                return;
            }
        }while (e.next != null);
    }

    /**
     * 获取
     * @param key
     * @return
     */
    public V get(K key){
        int index = hash(key);
        Entry<K, V> e = table[index];
        if (e == null || e.next == null){
            return null;
        }
        while (e.next != null){
            e = e.next;
            if (e.key.equals(key)){ // e.key == key 时会出错
                return e.value;
            }
        }
        return null;
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[])new Entry[table.length * 2];
        use = 0;
        for (int i = 0; i < oldTable.length; i++){
            if (oldTable[i] == null || oldTable[i].next == null){
                continue;
            }
            Entry<K, V> e = oldTable[i];
            while (e.next != null){
                e = e.next;
                int index = hash(e.key);
                if (table[index] == null){
                    use++;
                    table[index] = new Entry<>(null, null, null); // 创建哨兵节点
                }
                table[index].next = new Entry<>(e.key, e.value, table[index].next);
            }
        }
    }

    /**
     * 散列函数
     * @param key
     * @return
     */
    private int hash(Object key){
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % table.length;
    }
}
