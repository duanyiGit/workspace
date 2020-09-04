package com.duanyi.link;

import com.sun.glass.ui.Size;

public class MyLinkedList {
    private ListNode head; //头结点
    private int size = 0;

    /**
     * 头插法
     * @param data  待插入的数据
     */
    public  void insertHead(int data){
        ListNode newNode= new ListNode(data);
        newNode.next = head;
        head = newNode;
    }

    /**
     * 中间插入
     * @param data  待插入的数据
     * @param position   待插入的位置
     */
    public void insertMid(int data , int position){
        if(position == 0 ){
            insertHead(data);
        }else{
            ListNode cur = head;
           //从头节点开始遍历到要插入的位置
            for (int i = 1; i < position; i++) {
               cur = cur.next;
            }
            ListNode newNode = new ListNode(data);
            newNode.next = cur.next;
            cur.next = newNode;
        }
    }

    /**
     * 删除头节点
     */
    public void deleteHead(){
        head = head.next;
    }

    /**
     * 删除指定位置的节点
     * @param position
     */
    public void deleteMid(int position){
        if(position == 0){
            deleteHead();
        }else{
            ListNode cur = head;
            for(int i = 1;i<position;i++){
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
    }

    /**
     * 打印值
     */
    public void print(){
        ListNode cur = head;
        while (cur != null){
            System.out.print(cur.value +" ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList myList = new MyLinkedList();
        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(10);
        myList.print(); // 10 -> 7 -> 5
        myList.deleteMid(0);
        myList.print(); // 7 -> 5
        myList.deleteHead();
        myList.print(); // 5
        myList.insertMid(11, 1);
        myList.print(); // 5 -> 11
        myList.insertMid(12, 2);
        myList.print(); // 5 -> 11-> 12
        myList.deleteMid(1);
        myList.print(); // 5 -> 12
    }

}

class ListNode{
    int value; //值
    ListNode  next;  //下一个指针的数据

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}