package com.alogrithm.sort;

import java.util.Arrays;

/**
 * Created by Sakura on 2019/5/19.
 * 堆排序：利用堆结构实现的排序算法（不稳定）
 * 时间复杂度（平均）：O（n*log2(n)）
 * 空间复杂度：O（n*log2(n)）
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        HeapSort.heapSort(array);
        SortTest.print(array);
    }
    /*
     * 6.堆排序---(简单选择的优化)
     * */
    public static void heapSort(int[] arr) {
        //第一次初始化大根堆，从最后一个有子节点的根节点开始往上调整最大堆
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        System.out.println(Arrays.toString(arr));
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
            if(arr[child]>temp){//子节点大于父节点，则交换
                arr[parent] = arr[child];
                parent = child;
            }
            else
                break;
        }
        arr[parent] = temp; //将原父节点的值放入当前子节点
    }
}
