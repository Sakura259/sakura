package com.alogrithm.Tree.Huffman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakura on 2019/5/27.
 */
public class MinHeap {
    private List<HuffmanNode> minHeap;

    public MinHeap(int[] arr){
        minHeap = new ArrayList<HuffmanNode>();
        for (int i = 0; i < arr.length; i++) {
            minHeap.add(new HuffmanNode(arr[i],null,null,null));
        }
        for (int i = arr.length/2-1;i>=0;i--)
            filterdown(i,arr.length-1);

    }
    /*
    * 向下调整法
    * 第一次调用时，该list不是一个最小堆，从最后一个叶子节点的父节点开始调整
    * 之后调用时，除了根节点（arr[0]）为无序的，其他都是遵循最小堆规则的，即从0开始调整
    * */
    private void filterdown(int start,int end){
        int current = start;
        int child = 2*start+1;
        HuffmanNode temp = minHeap.get(current);
        while (child<=end){
            if (child<end&&(minHeap.get(child).compareTo(minHeap.get(child+1)))>0){
                child++;
            }
            int cmp = temp.compareTo(minHeap.get(child));
            if (cmp<=0)
                break;
            else {
                minHeap.set(current,minHeap.get(child));
                current = child;
                child = 2*child+1;
            }
        }
        minHeap.set(current,temp);
    }

    /*
    * 向上调整法：
    * */
    private void filterup(int start){
        int current = start;
        int parent = (start-1)/2;
        HuffmanNode temp = minHeap.get(current);
        while (current>0){
            int cmp = minHeap.get(parent).compareTo(temp);
            if (cmp<=0)
                break;
            else {
                minHeap.set(current,minHeap.get(parent));
                current = parent;
                parent = (parent-1)/2;
            }
        }
        minHeap.set(current,temp);
    }

    //将node插入到二叉树中
    public void insert(HuffmanNode node){
        int size = minHeap.size();
        minHeap.add(node);
        filterup(size);
    }

    //返回最小的节点，再将最小堆的最后一个节点代替根节点，然后重新调整为最小堆
    public HuffmanNode dumpFromMinNode(){
        int size = minHeap.size();
        if (size==0)
            return null;
        HuffmanNode node = (HuffmanNode)minHeap.get(0).clone();
        minHeap.set(0,minHeap.get(size-1));
        minHeap.remove(size-1);
        if (minHeap.size()>1)
            filterdown(0,minHeap.size()-1);
        return node;
    }

    // 销毁最小堆
    protected void destroy() {
        minHeap.clear();
        minHeap = null;
    }
}
