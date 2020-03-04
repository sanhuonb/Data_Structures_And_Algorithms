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

    /**
     * 合并两个有序链表，返回有序链表
     *
     * @param link1
     * @param link2
     * @return 合并后的有序链表
     */
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
        while (node1 != null) {
            result.insertToTail(node1.data); // 调用形参为 int 类型的插入函数，可以避免内存地址引用错误
            node1 = node1.next;
        }
        while (node2 != null) {
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
     * 删除倒数第 n 个节点，参数 n 由传入的值决定，当删除错误时返回 -1
     * 程序会判断链表是否为空，链表为空则提示错误返回 -1。当链表只有一个节点但是删除的位置不是 1 的话也会提示传入参数错误。
     *
     * @param index
     * @return 删除元素的值
     */
    public static int MydeleteLastKth(LinkedList list, int index) {
        int result;
        if (list.head == null) {
            System.out.println("链表为空");
            return -1;
        }
        if (list.head.next == null) {
            if (index == 1) {
                result = list.head.data;
                list.head = null;
                return result;
            } else {
                System.out.println("输入删除的位置有误! ");
                return -1;
            }
        }
        Node p = list.head;
        Node q = list.head;
        int count = 0, deleteIndex = 0; // 计算链表数据个数
        while (p != null) {
            count++;
            p = p.next;
        }
        p = null;
        deleteIndex = count - index;
        for (int i = 1; i < deleteIndex; i++) {
            q = q.next;
        }
        Node temp = q.next;
        result = temp.data;
        q.next = q.next.next;
        temp = null;
        return result;
    }

    /**
     * 删除倒数第 n 个节点，更优的代码，具体理论查资料
     *
     * @param list
     * @param k
     * @return
     */
    public static LinkedList deleteLastKth(LinkedList list, int k) {
        Node fast = list.head;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }
        if (fast == null) { // 两种情况，一种链表为空，一种删除的位置在链表之外
            return list;
        }
        Node slow = list.head;
        Node pre = null;
        while (fast.next != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;

        }
        if (pre == null) {
            list.head = list.head.next; // 如果删除的节点恰好是正数第一个节点
        } else {
            pre.next = pre.next.next;
        }
        return list;
    }

    /**
     * 寻找中间节点
     *
     * @param list
     * @return
     */
    public static Node findMiddleNode(LinkedList list) {
        if (list.head == null)
            return null;
        Node fast = list.head;
        Node slow = list.head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
        LinkedList list = new LinkedList();
        int data[] = {1, 2, 3, 4, 5};
        for (int i = 0; i < data.length; i++) {
            list.insertToTail(data[i]);
        }
        list.printAll();
        System.out.println("-------------");
        Node temp = findMiddleNode(list);
        System.out.println(temp.data);
    }
}
