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


	/*
	 * 4.希尔排序（直接插入排序的优化）---递减增量
	 * */


	/*
	 * 5.简单选择排序
	 * */




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
