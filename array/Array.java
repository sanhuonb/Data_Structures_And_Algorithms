/**
 * 实现数组功能，包括新建数组、插入、删除、查找等功能
 */
package array;

public class Array {
    private int[] data;
    private int capacity;
    private int count;

    public Array(int capacity) {
        this.data = new int[capacity];
        this.capacity = capacity;
        this.count = 0; // 元素个数从0开始
    }

    //判断数组是否为空，空的话返回true
    public boolean isEmpty(){
        if (count == 0)
            return true;
        else return false;
    }

    //根据索引返回数组相应位置的值，如果索引越界则返回 -1
    public int findIndex(int index){
        if (index < 0 || index > count){
            System.out.println("索引越界");
            return -1;
        }
        return data[index];
    }

    //插入元素，并且插入位置后面的数都后移,成功插入则返回 true
    public boolean insert(int index, int value){
        if (count == capacity){
            System.out.println("数组已满，无法插入");
            return false;
        }
        if (index < 0 || index > count){
            System.out.println("位置不合法");
            return false;
        }
        for (int i = count + 1; i > index; i--){
            data[i] = data[i - 1];
        }
        data[index] = value;
        count++;
        return true;
    }

    //删除索引位置的元素，成功删除返回true，否则返回false
    public boolean delete(int index){
        if (index < 0 || index >= count){ // index的位置必须小于个数
            System.out.println("位置不合法");
            return false;
        }
        //从删除位置开始，将后面的元素往前移
        for (int i = index + 1; i < count; i++){
            data[i - 1] = data[i];
        }
        --count;
        return true;
    }

    public void printAll(){
        for (int i = 0; i < count; i++){
            System.out.println(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(10);
        System.out.println(array.isEmpty());
        array.insert(0, 0);
        array.insert(1, 1);
        System.out.println(array.isEmpty());
        array.insert(2, 2);
        System.out.println(array.delete(0));
        array.printAll();
    }
}