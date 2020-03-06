package com.jy.stack;

public class StackBasedOnArray {
    private String[] data;
    private int count; // 元素个数
    private int capacity; //栈的大小

    public StackBasedOnArray(int capacity){
        data = new String[capacity];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 入栈，如果栈不够，扩容成当前2倍容量，并将数据复制到新栈，再进行入栈操作
     * @param value
     */
    public void push(String  value){
        if (count == capacity){
            String[] newData = new String[capacity * 2];
            data =copyData(data, newData);
            capacity = capacity * 2; // 容量扩大2倍
            data[count] = value;
            count++; // 栈元素个数增加
            return;
        }
        data[count] = value;
        count++;
    }

    /**
     * 出栈
     * @return
     */
    public String pop(){
        if (count == 0){
            System.out.println("栈已空");
            return null;
        }
        String temp = data[count-1];
        count--; // 出栈，元素个数-1
        return temp;
    }
    public void printAll(){
        if (count == 0){
            System.out.println("空栈");
            return;
        }
        for (int i = 0; i < count; i++){
            System.out.println(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * 将 sorce 的数据复制到aim, 并将 aim 返回
     * @param sorce
     * @param aim
     * @return String[] 返回复制完成的数组
     */
    public String[]  copyData(String[] sorce, String[] aim){
        for (int i = 0; i < sorce.length; i++){
            aim[i] = sorce[i];
        }
        return aim;
    }

    public static void main(String[] args) {
        StackBasedOnArray test = new StackBasedOnArray(5);
        test.printAll();
        test.push("1");
        test.printAll();
        test.push("2");
        test.push("3");
        test.push("4");
        test.push("5");
        test.printAll();
        System.out.println("栈的容量：" + test.capacity + "\t栈的元素个数：" + test.count);
        test.push("6");
        test.printAll();
        System.out.println("栈的容量：" + test.capacity + "\t栈的元素个数：" + test.count);
    }
}
