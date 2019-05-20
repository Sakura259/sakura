package com.alogrithm.sort;

/**
 * Created by Sakura on 2019/5/20.
 * 9.计数排序(稳定)：是桶排序的一种特殊情况，把每个桶中当成只有一个元素的情况
 * 时间复杂度：O（n+k）
 * 空间复杂度：O（n+k）
 */
public class CountSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        CountSort.countSort(array);
        SortTest.print(array);
    }

    public static void countSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){//找出数组的最大最小值
            max = Math.max(max, arr[i]);  //数组的最大值
            min = Math.min(min, arr[i]);  //数组的最小值
        }
        int[] tempArr = new int[max - min + 1];//max - min + 1---可以减少开辟数组的空间（相对于开辟max个空间）
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
}
