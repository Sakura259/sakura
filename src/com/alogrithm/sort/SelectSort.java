package com.alogrithm.sort;

/**
 * Created by Sakura on 2019/5/19.
 * 选择排序：不稳定
 * 时间复杂度：O（n次方）
 * 空间复杂度：O（1）
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        SelectSort.selectSort(array);
        SortTest.print(array);
    }
    /*
    * 首先在未排序的数列中找到最小(or最大)元素，然后将其存放到数列的起始位置；
    * 接着，再从剩余未排序的元素中继续寻找最小(or最大)元素，然后放到已排序序列的末尾。
    * 以此类推，直到所有元素均排序完毕。
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
}
