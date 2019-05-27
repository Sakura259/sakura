package com.alogrithm.Heap;

/**
 * Created by Sakura on 2019/5/28.
 */
public class HeapTest {
    public static void main(String[] args) {
        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        int b[] = {80, 40, 30, 60, 90, 70, 10, 50, 20};
//        HeapTest.maxTest(a);
        HeapTest.minTest(b);
    }
    public static void maxTest(int[] a){
        MaxHeap<Integer> m = new MaxHeap<>();
        for (int i = 0; i < a.length; i++) {
            m.insert(a[i]);
        }
        System.out.println(m);
        m.insert(85);
        System.out.println(m);
        m.remove(90);
        System.out.println(m);
    }

    public static void minTest(int[] a){
        MinHeap<Integer> m = new MinHeap<>();
        for (int i = 0; i < a.length; i++) {
            m.insert(a[i]);
        }
        System.out.println(m);
        m.insert(15);
        System.out.println(m);
        m.remove(60);
        System.out.println(m);
    }
}
