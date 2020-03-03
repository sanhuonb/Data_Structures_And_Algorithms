package com.jy.LinkedListPackge;


public class LinkedList {

    private Node head = null; //头结点

    //根据值返回节点
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    //根据索引返回节点
    public Node findByIndex(int index) {
        Node p = head;
        int count = 0;
        while (p != null && count < index) {
            count++;
            p = p.next;
        }
        return p;
    }

    //表头部插入,注意链表结构是不带头链表
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    //链表尾部插入，注意链表结构是不带头链表
    public void insertToTail(int value) {
        Node newNode = new Node(value, null);
        insertToTail(newNode);
    }

    /**
     * 注意！
     * 如果插入的节点如果是头结点，必须使用形参为 int 类型的函数，使用形参为 Node 的函数会导致头结点不是一个单独的节点
     * 即头结点会指向插入节点的后续节点。改进方法可以插入第一个节点时 new 一个没有 next 的新节点插入。
     *
     * @param newNode
     */
    public void insertToTail(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            newNode.next = p.next;
            p.next = newNode;
        }
    }

    //在某节点后插入一个节点，传入插入的节点位置以及待插入的节点的值。
    public void insertAfter(Node node, int value) {
        Node newNode = new Node(value, null);
        insertAfter(node, newNode);
    }

    public void insertAfter(Node node, Node newNode) {
        if (node == null) return;
        newNode.next = node.next;
        node.next = newNode;
    }

    //在某节点前插入节点
    public void insertBefore(Node node, int value) {
        Node newNode = new Node(value, null);
        insertBefore(node, newNode);
    }

    public void insertBefore(Node node, Node newNode) {
        if (node == null) return;
        if (node == head) {
            insertToHead(newNode);  // 如果要插入的位置是第一个节点之前，调用表头部插入方法
            return;
        }
        Node q = head;
        while (q != null && q.next != node) {
            q = q.next;
        }
        if (q == null) { // 如果遍历链表没有找到 node 节点
            return;
        }
        newNode.next = node;
        q.next = newNode;
    }

    // 根据节点信息删除节点
    public void deleteByNode(Node p) {
        if (p == null || head == null) return;
        if (p == head) {
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        q.next = q.next.next;
    }

    // 根据节点的值删除节点
    public void deleteByValue(int value) {

        /*
        if (head == null) return;
        Node p = head;
        Node q = null;
        while (p != null && p.data != value){
            q = p;
            p = p.next;
        }
        if (p == null){ // 链表中没有 value 值的节点
            return;
        }
        if (q == null){  // 如果该节点是头部节点
            head = head.next;
        } else {
            q.next = q.next.next;
        }
        */

        // 上面注释的也是删除单个 value 的代码，以下这段代码根据删除重复 value 修改得来
        if (head != null && head.data == value) {
            head = head.next;
            return;
        }
        Node pNode = head;
        while (pNode != null) {
            if (pNode.next.data == value) {
                if (pNode.next.next != null) {
                    pNode.next = pNode.next.next;
                    break;
                } else {
                    pNode.next = null;
                    break;
                }
            }
            pNode = pNode.next;
        }

        /*
        //可重复删除指定 value 节点的代码
        if (head != null && head.data == value){
            head = head.next;
        }
        Node pNode = head;
        while (pNode != null){
            if (pNode.next.data == value){
                if (pNode.next.next != null) {
                    pNode.next = pNode.next.next;
                    continue;
                }else{
                    pNode.next = null;
                    break;
                }
            }
            pNode = pNode.next;
        }
        */
    }

    //输出全部节点
    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //传入左半部分节点和右半部分节点，判断是否是回文串
    public boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;
        boolean flag = true;
        System.out.println("left_:" + l.data);
        System.out.println("right_:" + r.data);
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
                continue;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    //判断是否是回文串
    public boolean palindrome() {
        if (head == null) {
            return false;
        } else {
            System.out.println("开始找中间节点");
            Node p = head;
            Node q = head;
            if (p.next == null) {
                System.out.println("只有一个节点");
                return true;
            }
            while (q.next != null && q.next.next != null) {
                p = p.next;
                q = q.next.next;
            }
            System.out.println("中间节点: " + p.data);
            System.out.println("开始执行回文判断");
            Node leftLink = null;
            Node rightLink = null;
            if (q.next == null) { // 满足这个提交，p 一定是整个链表的中间，且链表的节点数目是奇数
                /*注意此处不能先翻转子串赋值给 leftLink 再赋值给 rightLink，会导致 p 的值变更，
                后续改进可以 copy 左右子串分别进行赋值
                 */
                rightLink = p.next;
                leftLink = inverseLinkList(p).next;
                System.out.println("左边第一个节点的值为: " + leftLink.data);
                System.out.println("右边第一个节点的值为: " + rightLink.data);
            } else { // 不满足上个条件则链表节点数目是偶数，p 和 q 都是中间节点
                System.out.println("p的值：" + p.data);
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            return TFResult(leftLink, rightLink);  //判断左右两部分链表是否相等，即判断是否是回文串
        }

    }

    //带节点的链表翻转
    public Node inverseLinkList_head(Node p) {
        Node Head = new Node(9999, null);
        Node pre = null;
        Node next = null;
        Head.next = p;
        Node Cur = p.next;
        while (Cur != null) {
            next = Cur.next;
            Cur.next = Head.next;
            Head.next = Cur;
            System.out.println("first " + Head.data);
            Cur = next;
        }
        return Head;
    }

    //无表头的链表翻转
    public Node inverseLinkList(Node p) {
        Node pre = null;
        Node r = head;
        System.out.println("第一个节点的数据：" + r.data);
        Node next = null;
        while (r != p) {
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }
        r.next = pre;  // 上面的循环遍历到 p 节点时退出，没有指定 r 的 next 指针
        return r;
    }

    public static LinkedList mergeOrderedLinkedList(LinkedList link1, LinkedList link2) {
        LinkedList result = new LinkedList();
        Node node1 = link1.head;
        Node node2 = link2.head;
        while (node1 != null && node2 != null) {
            if (node1.data <= node2.data) {
                result.insertToTail(node1.data); // 调用形参为 int 类型的插入函数，可以避免内存地址引用错误
                node1 = node1.next;
            } else {
                result.insertToTail(node2.data);
                node2 = node2.next;
            }
        }
        while (node1 != null){
            result.insertToTail(node1.data); // 调用形参为 int 类型的插入函数，可以避免内存地址引用错误
            node1 = node1.next;
        }
        while (node2 != null){
            result.insertToTail(node2.data);
            node2 = node2.next;
        }
        return result;
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return this.data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setData(int value) {
            this.data = value;
        }
    }

    /**
     * 传入一个链表，检测是否是中环
     *
     * @param p
     * @return
     */
    public boolean detectLoop(Node p) {
        if (p == null) {
            return false;
        }
        Node pSlow = p;
        Node pFast = p.next;
        while (pFast != null && pFast.next != null) {
            if (pFast == pSlow)
                return true;
            pSlow = pSlow.next;
            pFast = pFast.next.next;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedList link1 = new LinkedList();
        LinkedList link2 = new LinkedList();
        int data[] = {1, 2, 3, 4};
        int data2[] = {3, 4, 5, 6};
        for (int i = 0; i < data.length; i++) {
            link1.insertToTail(data[i]);
        }
        for (int i = 0; i < data2.length; i++) {
            link2.insertToTail(data2[i]);
        }
        link1.printAll();
        link2.printAll();

        LinkedList result = mergeOrderedLinkedList(link1, link2);
        result.printAll();
    }
}
