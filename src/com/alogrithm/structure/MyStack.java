package com.alogrithm.structure;

import java.util.Arrays;

public class MyStack {
	private int[] array;
	private int maxSize;
	private int top;

	public MyStack(int maxSize){//构造函数，初始化栈的大小
		this.maxSize = maxSize;
		array = new int[maxSize];
		top = -1;
	}

	public void push(int value) {//进栈，将一个元素存入栈
		if(top<maxSize-1){
			array[++top] = value;
		}
	}

	public int pop() {//出栈，将栈顶元素返回
		return array[top--];
	}

	public int peek() {//访问栈顶元素
		return array[top];
	}

	public boolean isEmpty() {//判断栈是否为空
		return (top==-1);
	}

	public boolean isFull() {//判断栈是否满
		return (top ==maxSize-1);
	}

	public void print() {
		System.out.println(Arrays.toString(array));
	}

}
