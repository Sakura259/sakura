package com.alogrithm.sort;

/**
 * Created by Sakura on 2019/5/19.
 * 4.希尔排序：是针对直接插入排序算法的改进（不稳定）
 * 时间复杂度：O（n的1.3次方）
 * 空间复杂度：O（1）
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        ShellSort.shellSort(array,array.length);
        SortTest.print(array);
    }
    /*
    * 它的基本思想是：
    * 1.对于n个待排序的数列，取一个小于n的整数gap（length/2）(gap被称为步长)将待排序元素分成若干个组子序列，
    * 所有距离为gap的倍数的记录放在同一个组中；
    * 2.然后，对各组内的元素进行直接插入排序。
    * 这一趟排序完成之后，每一个组的元素都是有序的。
    * 3.然后减小gap的值(除2)，并重复执行上述的分组和排序。
    * 重复这样的操作，当gap=1时，整个数列就是有序的。
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
            }
        }
    }
}
