package com.alogrithm.Tree.AVLTree;

/**
 * Created by Sakura on 2019/5/23.
 */
public class AVLTreeTest {
    private static int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
    public static void main(String[] args) {
        int i;
        AVLTree<Integer> tree = new AVLTree<Integer>();

        System.out.printf("== 依次添加: ");
        for(i=0; i<arr.length; i++) {
            tree.insert(arr[i]);
        }
        System.out.println("前序遍历");
        tree.preOrder();

        System.out.println("树的高度："+tree.getTreeHeight());
        System.out.println("输出树的详细信息：");
        tree.print();
    }
}
