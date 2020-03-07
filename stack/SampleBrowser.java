package com.jy.stack;

/**
 * 使用栈实现浏览器的前进后退，以链表为基础实现栈
 * 本代码将前进、后退、当前页面分开，即当前页面没有在其余两个栈中
 */
public class SampleBrowser {
    public static void main(String[] args) {
        SampleBrowser test = new SampleBrowser();
        test.open("www.baidu.com");
        test.open("www.google.com");
        test.open("www.pornhub.com");
        test.goBack();
        test.checkCurrentPage();
        test.goBack();
        test.goForward();
        test.open("www.github.com");
        test.goForward();
        test.goBack();
        test.goBack();
        test.goBack();
        test.goBack();
        test.checkCurrentPage();
    }
    private String currentPage;
    private StackBasedLinkedList backStack;
    private StackBasedLinkedList forwardStack;

    public SampleBrowser() {
        backStack = new StackBasedLinkedList();
        forwardStack = new StackBasedLinkedList();
    }

    /**
     * 模拟打开一个网页
     * @param url
     */
    public void open(String url){
        if (currentPage != null){
            backStack.push(currentPage);
            forwardStack.clear(); // 新打开一个网页时，就不能再 “前进”打开网页
        }
        showUrl(url, "Open");
    }

    public boolean canGoBack(){
        return backStack.size > 0;
    }

    public boolean canForward(){
        return forwardStack.size > 0;
    }

    /**
     * 后退操作
     * @return
     */
    public String goBack(){
        if (canGoBack()){
            forwardStack.push(currentPage);
           // currentPage = backStack.getTopData();
            String backUrl = backStack.pop();
            showUrl(backUrl, "Back");
            return backUrl;
        }
        System.out.println("Cannot go back, no pages behind.");
        return null;
    }

    /**
     * 前进操作
     * @return
     */
    public String goForward(){
        if (canForward()){
            backStack.push(currentPage);
            String forwardUrl = forwardStack.pop();
            showUrl(forwardUrl, "Forward");
            return forwardUrl;
        }
        System.out.println("Cannot go forward, no pages ahead");
        return null;
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }

    /**
     * 模拟打开页面输出页面信息
     * @param url
     * @param prefix 表明是新打开的网页，还是前进、后退时打开的网页
     */
    public void showUrl(String url, String prefix){
        currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    public static class StackBasedLinkedList{
        private Node top = null;
        private int size = 0;

        public void push(String value) {
            Node newNode = new Node(value, null);
            if (size == 0) {
                top = newNode;
                size++;
            } else {
                newNode.next = top;
                top = newNode;
                size++;
            }
        }

        /**
         * 清空栈
         */
        public void clear(){
            this.top = null;
            this.size = 0;
        }

        /**
         * 返回top的数据
         * @return
         */
        public String getTopData(){
            if (top == null){
                System.out.println("栈为空");
                return null;
            }
            return top.data;
        }

        /**
         * 获取栈中元素个数
         * @return
         */
        public int getSize(){
            return size;
        }
        /**
         * null代表栈中没有数据
         *
         * @return
         */
        public String pop() {
            if (size == 0)
                return null;
            String data = top.getData();
            top = top.next;
            size--;
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

    }

    public static class Node{
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
        public Node(String data, Node next){
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
