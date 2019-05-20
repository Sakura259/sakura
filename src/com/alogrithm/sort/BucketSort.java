package com.alogrithm.sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sakura on 2019/5/20.
 * 8.桶排序（稳定）
 * 时间复杂度：O（n+k）
 * 空间复杂度：O（n+k）
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        BucketSort.bucketSort(array);
    }
    public static void bucketSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){//找出数组的最大最小值
            max = Math.max(max, arr[i]);  //数组的最大值
            min = Math.min(min, arr[i]);  //数组的最小值
        }
        //桶的数量
        int bucketNum = (max-min)/arr.length+1;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        //将每个元素放入应存入的桶中
        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i]-min)/arr.length;
            buckets.get(num).add(arr[i]);
        }
        //对每个桶进行排序
        for (int i = 0; i < buckets.size(); i++) {
            Collections.sort(buckets.get(i));
        }
        System.out.println(buckets.toString());

    }
}
