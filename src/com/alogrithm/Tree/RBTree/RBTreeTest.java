package com.alogrithm.Tree.RBTree;

/**
 * Created by Sakura on 2019/5/24.
 */
public class RBTreeTest {
    private static final int arr[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
    public static void main(String[] args) {
        RBTree<Integer> tree=new RBTree<Integer>();

        System.out.printf("原始数据: ");
        for(int i=0; i<arr.length; i++){
            System.out.printf("%d ", arr[i]);
            tree.insert(arr[i]);
        }
        System.out.printf("\n");
        tree.preOrder();
        System.out.println();
        tree.inOrder();
        System.out.println();
        tree.postOrder();
    }
}
