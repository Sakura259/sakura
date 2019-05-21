package com.alogrithm.structure;

public class MyLinkedList {
	private int size;//链表节点的个数
	private Node head;//头节点
	private Node tail;//尾节点

	public MyLinkedList() {//初始化链表
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	//链表的每个节点类---包含本节点的数据和指向下一节点的节点
	private class Node{
		private Object data;//每个节点的数据
		private Node next;//每个节点指向下一个节点的连接
		private Node prev;//每个节点指向上一个节点的连接
		public Node(){

		}
		public Node(Object data){//初始化节点数据
			this.data = data;
		}
		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + next + "]";
		}
	}

	public Object addHead(Object obj) {//在链表头添加节点
		Node node = new Node(obj);
		if(size == 0){//判断是否有节点
			head = node;
			tail = node;
		}else {//新的节点为头节点
			head.prev = node;
			node.next = head;
			head = node;
		}
		size++;
		return obj;
	}

	public void addTail(Object obj){//在链表尾添加数据
		Node node  = new Node(obj);
		if(size == 0){
			head = node;
			tail = node;
		}else {
			node.prev = tail;
			tail.next = node;
			tail = node;
		}
		size++;
	}

	public Node deleteHead() {//删除链表头节点
		Node temp = head;
		if(size!=0){
			head = head.next;
			head.prev = null;
		}
		size--;
		return temp;
	}

	public Node deleteTail(){//删除链表尾的尾节点
		Node node = tail;
		if(size!=0){
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return node;
	}

	public Node find(Object obj) {//遍历链表，寻找所需节点
		Node curret = head;
		int tempSize = size;
		while(tempSize>0){
			if(obj.equals(curret.data)){//判断节点的数据是否与寻找的数据相等
				return curret;
			}
			else {
				curret = curret.next;
			}
			tempSize--;
		}
		return null;
	}

	public boolean delete(Object value) {//遍历链表，删除数据
		Node curret = head;//当前节点
		Node previous = head;//前一节点
		if(size == 0){
			return false;
		}
		while(curret.data!=value){//先找到所需节点，记录在curret中
			if(curret.next == null){
				return false;
			}else {
				previous = curret;
				curret = curret.next;
			}
		}//对链表进行节点删除
		if(curret == head){
			head = head.next;
			head.prev = null;
			size--;
		}else {
			previous.next = curret.next;
			curret.prev = previous;
			size--;
		}
		return true;
	}

	public boolean isEmpty() {
		return (size ==0);
	}

	public void display() {
		if(size>0){
			Node node = head;
			int tempSize = size;
			if(tempSize == 1){
				System.out.print("["+node.data+"->]");
				return;
			}
			while(tempSize>0){
				if(node.equals(head)){
					System.out.print("["+node.data+"->");
				}
				else if (node.next == null) {
					System.out.print(node.data+"]");
				}
				else {
					System.out.print(node.data+"->");
				}
				node = node.next;
				tempSize--;
			}
			System.out.println();
		}else {
			System.out.println("[]");
		}

	}

}
