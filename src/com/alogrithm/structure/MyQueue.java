package com.alogrithm.structure;

import java.util.Arrays;

public class MyQueue {
	private Object[] queArray;//队列数组
	private int maxSize;//队列最大容量
	private int front;//前继指针，表示队头---数据删除的地方
	private int rear;//后继指针，表示队尾---数据插入的地方
	private int realSize;//队列真实容量

	public MyQueue(int maxSize){//构造函数，初始化队列容量，指针位置
		this.maxSize = maxSize;
		queArray = new Object[maxSize];
		front = 0;
		rear = -1;
		realSize = 0;
	}

	public void insert(int value) {//插入函数，操作后继指针
		if(isFull()){
			System.out.println("队列已满!");
		}
		else {//对后指针进行处理---即入队元素（插入数据）
			if(rear == maxSize-1){//循环队列，如果已满，则重新回到初始位置
				rear = -1;
			}
			queArray[++rear] = value;
			realSize++;
		}
	}

	public Object remove() {//移除函数，操作前继指针
		Object removeValue = null;
		if(!isEmpty()){//对前指针进行处理---即出队元素（弹出元素）
			removeValue = queArray[front];
			queArray[front++] = null;
			if(front == maxSize){//判断前继指针是否已满，恢复初始位置
				front = 0;
			}
			realSize--;
			return removeValue;

		}
		return removeValue;
	}

	public Object peekFront() {//访问队头元素
		return queArray[front];
	}

	public boolean isFull() {//判断队列是否已满
		return (maxSize==realSize);
	}

	public boolean isEmpty() {//判断队列是否为空
		return (realSize == 0);
	}

	public int getSize() {//返回队列的大小
		return realSize;
	}

	public void print() {
		System.out.println(Arrays.toString(queArray));
	}
}
