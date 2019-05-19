package com.alogrithm.sort;

/**
 * Created by Sakura on 2019/5/19.
 * 3.插入排序：稳定
 * 时间复杂度（平均）：O（n方）
 * 空间复杂度：O（1）
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        InsertionSort.insertionSort(array);
        SortTest.print(array);
    }
    /*
    * 默认arr[0]位置正确，此后的每一个数与前面已排序好的数进行比较，插入到正确位置
    * */
    public static void insertionSort(int[] arr) {
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
}
