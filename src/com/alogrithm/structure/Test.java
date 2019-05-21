package com.alogrithm.structure;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Sakura on 2019/5/20.
 */
public class Test {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		Stack<Object> s = new Stack<>();
//		MyStack stack = new MyStack(10);
//		stack.push(1);
//		stack.push(2);
//		stack.push(3);
//		stack.push(14);
//		stack.push(15);
//		while(!stack.isEmpty()){
//			System.out.println(stack.peek());
//			stack.pop();
//		}
//		Queue<Integer> que = new PriorityQueue<>();
//		MyQueue queue = new MyQueue(10);
//		queue.insert(5);
//		queue.insert(2);
//		queue.insert(4);
//		queue.insert(6);
//		queue.insert(8);
//		queue.insert(9);
//		queue.insert(3);
//		queue.print();
//		System.out.println(queue.peekFront());
//		queue.remove();
//		System.out.println(queue.peekFront());
        MyLinkedList list = new MyLinkedList();
        list.addHead(1);
        list.addHead(2);
        list.addHead(3);
        list.addHead(4);
        list.addHead(5);
        list.addHead(6);
        list.addHead(8);
        list.addTail(111);
        list.deleteTail();
        list.display();
        list.deleteHead();
        list.display();

        list.deleteTail();
        list.display();

        list.delete(4);
        list.display();

    }

}

