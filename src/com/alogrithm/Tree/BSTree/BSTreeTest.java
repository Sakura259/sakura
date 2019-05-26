package com.alogrithm.Tree.BSTree;

/**
 * Created by Sakura on 2019/5/22.
 */
public class BSTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] arr = {1,5,4,3,2,6};
        for (int i = 0; i < arr.length; i++) {
            tree.insert(arr[i]);
        }
        tree.preOrder();
        System.out.println();
        tree.inOrder();
        System.out.println();
        tree.postOrder();
        System.out.println();
        System.out.println(tree.findMax());
        System.out.println(tree.findMin());
        tree.remove(arr[3]);
        tree.inOrder();
    }
}
