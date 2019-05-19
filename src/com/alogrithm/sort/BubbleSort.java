package com.alogrithm.sort;

/**
 * Created by Sakura on 2019/5/19.
 * 1.冒泡排序：稳定
 * 时间复杂度（平均）：O（n方）
 * 空间复杂度：O（1）
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        BubbleSort.bubbleSort(array);
        SortTest.print(array);
    }

    public static void bubbleSort(int[] arr){
        int temp;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]>arr[j]){ //比较两个数的大小
                    temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }
}
