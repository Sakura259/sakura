package com.alogrithm.sort;

import java.util.Arrays;

public class SortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,4,6,8,9,3,1,2,7,10};
		int[] tempArr = new int[10];
//		SortTest.bubbleSort(array);

//		quickSort(array, 0, array.length-1);

//		insertionSort(array, array.length-1);

//		shellSort(array, array.length);

//		selectSort(array);

//		heapSort(array);

//		mergeSort(array, tempArr, 0, array.length-1);

//		countSort(array);

		radixSort(array,1000);

//		countSort(array);

		SortTest.print(array);
	}

	/*
	 * 输出函数
	 * */
	public static void print(int[] arr){
		for (int i : arr) {
			System.out.print(i +" ");
		}
		System.out.println();
	}

	/*
	 * 1.冒泡排序
	 * 两两交换比较
	 * */


	/*
	 * 2.快速排序
	 * */


	/*
	 * 3.直接插入排序
	 * */
	public static void insertionSort(int[] arr,int n) {
		int temp;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];//默认第一个数已排序正确，从第二个数开始比较
			int j = i-1;
			while(j>=0&&arr[j]>temp){//从已排序好的数组从后往前比较
				arr[j+1] = arr[j];//如果已存在的数比待插入的数大，则往后挪一位
				j--;//继续向前比较
			}
			arr[j+1] = temp;//当碰到比带插入数小的数时，在其后放入待插入的数
		}
	}

	/*
	 * 4.希尔排序（直接插入排序的优化）---递减增量
	 * */
	public static void shellSort(int[] arr,int n) {
		for (int gap = n/2; gap >= 1; gap/=2) {
			for (int i = gap; i < arr.length; i++) {
				int temp = arr[i];
				int j = i-gap;
				while(j>=0&&temp<arr[j]){
					arr[j+gap] = arr[j];
					j-=gap;
				}
				if((j+gap)!=i){
					arr[j+gap] = temp;
				}
//				if(temp<arr[j]){
//					arr[i] = arr[j];
//					arr[j] = temp;
//				}
			}
		}
	}

	/*
	 * 5.简单选择排序
	 * */
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) { //以所在位置的数为基准
			int temp = arr[i],k = i;
			for (int j = i+1; j < arr.length; j++) {//向后遍历比较，找到最小的数存入所在位置
				if(arr[i]>arr[j]){
					arr[i] = arr[j];
					k = j;
				}
			}
			arr[k] = temp;
		}
	}

	/*
	 * 6.堆排序---(简单选择的优化)
	 * */
	public static void heapSort(int[] arr) {
		//第一次初始化大根堆，从最后一个有子节点的根节点开始往上调整最大堆
		for (int i = arr.length/2; i >= 0; i--) {
			adjustHeap(arr,i,arr.length);
		}
		//从顶点开始往下调整大根堆
		for (int i = arr.length-1; i > 0; i--) {
			int temp = arr[0]; //最后一个叶子节点与根节点交换，使得最大的数在最后
			arr[0] = arr[i];
			arr[i] = temp;

			adjustHeap(arr, 0, i);
		}
	}
	public static void adjustHeap(int[] arr,int parent,int length) {
		int temp = arr[parent];//基准数为父节点
		for (int child = parent*2+1; child < length; child=child*2+1) {
			if(child+1<length&&arr[child]<arr[child+1]){//判断左右叶子节点哪个最大
				child++;
			}
			if(arr[child]>temp){//子节点大于右节点，则交换
				arr[parent] = arr[child];
				parent = child;
			}
			else
				break;
		}
		arr[parent] = temp; //将原父节点的值放入当前子节点
	}

	/*
	 * 7.二路归并排序---重点思想（分治合并）
	 * arr数组为存储源数据，tempArr为一个临时存储数组
	 * */
	public static void mergeSort(int[] arr,int[] tempArr,int startIndex,int endIndex) {
		int midIndex;
		if(startIndex<endIndex){//判断是否不可再分，即每组元素最少只有一个
			midIndex = (startIndex+endIndex)/2;
			mergeSort(arr, tempArr, startIndex, midIndex);
			mergeSort(arr, tempArr, midIndex+1, endIndex);
			merge(arr, tempArr, startIndex, midIndex, endIndex);//对每组元素进行排序
		}
	}
	public static void merge(int[] arr,int[] tempArr,int startIndex,int midIndex,int endIndex) {
		int i = startIndex,j = midIndex+1, k = startIndex;//方便计数，k用于tempArr数组的计数
		while(i!=midIndex+1&&j!=endIndex+1){//判断是否有某一组已经遍历比较完
			if(arr[i]>arr[j]){
				tempArr[k++] = arr[j++];
			}
			else {
				tempArr[k++] = arr[i++];
			}
		}
		while(i!=midIndex+1){//对前一组未比较的元素直接存入临时数组
			tempArr[k++] = arr[i++];
		}
		while(j!=endIndex+1){//对后一组未比较的元素直接存入数组
			tempArr[k++] = arr[j++];
		}
		for(int s = startIndex;s<=endIndex;s++){//将临时数组---即已排序好的数组返回源数组
			arr[s] = tempArr[s];
		}
	}

	/*
	 * 8.计数排序
	 * */
	public static void countSort(int[] arr) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0;i<arr.length;i++){//找出数组的最大最小值
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}
		int[] tempArr = new int[max+1];//max+1---可以减少开辟数组的空间（相对于开辟max个空间）
		for (int i = 0; i < arr.length; i++) {//tempArr下标就是排序的数字，这个位置表示相同数字有几个
			int maxPos = arr[i];
			tempArr[maxPos]++;
		}
		int index = 0;
		for(int i = 0;i<tempArr.length;i++){
			while(tempArr[i]-- > 0){
				arr[index++] = i;
			}
		}
	}

	/*
	 * 9.基数排序
	 * */
	public static void radixSort(int[] arr,int d) {
		int n = 1,k = 0,length = arr.length;
		int[][] bucket = new int[10][length];//二位数组：第一个表示单个位0-9这10个桶，第二个表示相同位的数字有几个相同的
		int[] order = new int[length];
		while(n<d){
			for (int i : arr) {
				int digit = (i/n)%10;//逐位取数---个、十、百……
				bucket[digit][order[digit]++] = i;//将该数存入应在的桶中
			}
			for (int i = 0; i < length; i++) {
				if(order[i]!=0){//判断桶中有几个数
					for (int j = 0; j < order[i]; j++) {
						arr[k++] = bucket[i][j];//重新取出存入arr中，进行下一次判断
					}
				}
				order[i] = 0;//计数归零
			}
			n *= 10;//判断下一位
			k = 0;//存入计数归零
		}
	}

	/*
	 * 10.桶排序
	 * */
	public static void bucketSort(int[] arr) {
		int max = Integer.MIN_VALUE;
		for(int i = 0;i<arr.length;i++){
			max = Math.max(max, arr[i]);
		}
		int[] tempArr = new int[max+1];
		for(int i=0;i<arr.length;i++){
			tempArr[arr[i]]++;
		}
		for(int i = 0,j=0;i<=max;i++){
			while(tempArr[i]-->0){
				arr[j++] = i;
			}
		}
	}
}
