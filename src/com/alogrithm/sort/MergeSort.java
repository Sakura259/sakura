package com.alogrithm.sort;

/**
 * Created by Sakura on 2019/5/20.
 * 7.二路归并排序（稳定）
 * 时间复杂度：O（n*log2(n)）
 * 空间复杂度：O（n）
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        int[] tempArr = new int[10];
//        mergeSortUpToDown(array, tempArr, 0, array.length-1);
        mergeDownToUpSort(array,tempArr);
        SortTest.print(array);
    }
    /*
    * 从上往下归并排序
    * 1.分解：将当前区间一份为二，分裂点为（low+high）/2
    * 2.求解：递归地对两个子区间进行归并排序，直到归并子区间长度为1
    * 3.合并：将已排序的两个子区间归并成一个有序的区间。
    * */
    public static void mergeSortUpToDown(int[] arr,int[] tempArr,int startIndex,int endIndex) {
        if (arr==null)
            return;
        int midIndex;
        if(startIndex<endIndex){//判断是否不可再分，即每组元素最少只有一个
            midIndex = (startIndex+endIndex)/2;
            mergeSortUpToDown(arr, tempArr, startIndex, midIndex);
            mergeSortUpToDown(arr, tempArr, midIndex+1, endIndex);
            merge(arr, tempArr, startIndex, midIndex, endIndex);//对每组元素进行排序
        }
    }

    public static void merge(int[] arr,int[] tempArr,int startIndex,int midIndex,int endIndex) {
        int i = startIndex,j = midIndex+1, k = startIndex;//方便计数，k用于tempArr数组的计数
        while(i!=midIndex+1&&j!=endIndex+1){//判断是否有某一组已经遍历比较结束
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
    * 从下往上归并排序
    * 1.将待排序数列分为长度为1的子区间。
    * 2.两两相排序合并，直到合并成一个数列为止
    * */
    public static void mergeDownToUpSort(int[] arr,int[] tempArr){
        if (arr==null)
            return;
        for (int gap = 1; gap < arr.length; gap*=2) {
            mergeGroups(arr,tempArr,arr.length,gap);
        }
    }
    public static void mergeGroups(int[] arr,int[] tempArr,int len,int gap){
        int i;
        //将每两个相邻的子数列进行合并排序
        for (i = 0; i+2*gap-1 < len; i+=(2*gap)) {
            merge(arr,tempArr,i,i+gap-1,i+2*gap-1);
        }
        //即到最后，还剩余一个子数列没有排序，将该子数列合并到已排序的数列中
        if (i+gap-1 < len-1){
            merge(arr , tempArr, i, i + gap - 1, len - 1);
        }
    }

}
