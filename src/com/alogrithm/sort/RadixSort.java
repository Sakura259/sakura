package com.alogrithm.sort;

/**
 * Created by Sakura on 2019/5/20.
 * 10.基数排序（稳定）
 * 时间复杂度：O（n*k）
 * 空间复杂度：O（n+k）
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = {5,4,6,8,9,3,1,2,7,10};
        RadixSort.radixSort(array,1000);
        SortTest.print(array);
    }

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
}
