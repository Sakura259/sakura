package com.alogrithm.sort;

import java.util.Random;

/**
 * Created by Sakura on 2019/5/19.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
//        quickSort1(array,0,array.length-1);
        quickSort2(array,0,array.length-1);
        SortTest.print(array);
    }
    /*
    * 快速排序（基于固定切分）：第一种方式
    * 1.选择基准数
    * 2.从后往前遍历第一个比基准数小的数
    * 3.从前往后遍历第一个比基准数大的数,然后交换位置
    * 4.直到前后遍历指针重合，当前指针位置与基准数交换位置，第一趟排序结束
    * 5.基于分治思想继续排序
    * */
    public static void quickSort1(int[] arr,int start,int end) {
        if(start>end)    //判断遍历是否结束
            return;
        int i=start,j=end;
        int temp = arr[i];
        while(i<j){
            //从右往左遍历第一个比基准数小的值
            while(arr[j]>=temp&&i<j){
                j--;
            }
            //从左往右遍历第一个比基准数大的数
            while(arr[i]<=temp&&i<j){
                i++;
            }
            if(i<j){  //两边碰到一个比基准数大，另一个比基准数小，交换两个数的位置
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        //基准数与i所在位置交换，使得基准数左边的都比它小，右边的都比它大
        arr[start] = arr[i];
        arr[i] = temp;
        quickSort1(arr, start, i-1); //以基准数所在位置的左右继续进行递归排序
        quickSort1(arr, i+1, end);
    }
    /*
    * 快速排序：第二种方式
    * 1.选择基准数
    * 2.从后往前遍历第一个比基准数小的数，与基准数交换位置
    * 3.从前往后遍历第一个比基准数大的数，与基准数交换位置
    * 4.直到前后遍历指针重合，第一趟排序结束
    * 5.基于分治思想继续排序
    * */
    public static void quickSort2(int[] arr,int start,int end){
        if(start>end)
            return;
        int i =start,j = end;
        Mid(arr,start,end);
        int temp = arr[start];
        while (i<j){
            while (arr[j]>temp&&i<j){
                j--;
            }
            arr[i] = arr[j];
            while (arr[i]<temp&&i<j){
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = temp;
        quickSort2(arr,start,i-1);
        quickSort2(arr,i+1,end);
    }
    /*
    * 使用随机数形成基准数：能解决待排数组基本有序的情况
    * 生成随机数数组下标
    * 与原基准数交换位置
    * */
    public static void Random(int[] arr,int start,int end){
        int random = new Random().nextInt(end-start+1)+start;
        int temp = arr[start];
        arr[start] = arr[random];
        arr[random] = temp;
    }
    /*
    * 三数取中（最理想选取基准数的方法）
    * 基于数组头、中、尾寻找中间值，与原基准数交换位置
    *
    * */
    public static void Mid(int[] arr,int start,int end){
        int mid = start+(end-start)/2;
        if(arr[mid]>arr[end]){
            Swap(arr[mid],arr[end]);
        }
        if (arr[start]>arr[end]){
            Swap(arr[start],arr[end]);
        }
        if (arr[start]>arr[mid]){
            Swap(arr[start],arr[mid]);
        }
    }

    public static void Swap(int a,int b){
        int temp = a;
        a = b;
        b = temp;
    }

}
