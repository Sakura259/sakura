package com.alogrithm.Heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakura on 2019/5/28.
 * 最小堆：
 * 父结点的键值总是小于或等于任何一个子节点的键值。
 */
public class MinHeap<T extends Comparable<T>> {
    private List<T> minHeap;

    public MinHeap(){
        this.minHeap = new ArrayList<>();
    }
    /*
     * 向上调整法：
     * 当向堆插入数据时，是在数组最后添加元素，因此是从下向上开始调整堆
     * 父当前节点的父节点 = （当前节点-1）/2    需要先减一，防止数组索引为-1导致的数组越界
     * */
    private void filterUp(int start){
        int current = start;
        int parent = (start-1)/2;
        T temp = minHeap.get(current);
        while (current>0){
            int cmp = temp.compareTo(minHeap.get(parent));
            if (cmp >= 0)
                break;
            else {
                minHeap.set(current,minHeap.get(parent));
                current = parent;
                parent = (parent-1)/2;
            }
        }
        minHeap.set(current,temp);
    }
    //插入函数
    public void insert(T data){
        int size = minHeap.size();
        minHeap.add(data);
        filterUp(size);
    }

    /*
     * 向下调整法：
     * 在删除节点时，通过将最后一个节点代替删除节点，因此从上向下开始调整
     * 默认根节点为0，因此，当前节点的子左孩子为 2*current+1
     * */
    private void filterDown(int start,int end){
        int current = start;
        int child = current*2+1;
        T temp = minHeap.get(current);
        while (child<=end){
            if (child<end&&(minHeap.get(child).compareTo(minHeap.get(child+1))>0)){
                child++;
            }
            int cmp = temp.compareTo(minHeap.get(child));
            if (cmp>0){
                minHeap.set(current,minHeap.get(child));
                current = child;
                child = 2*child+1;
            }
            else break;
        }
        minHeap.set(current,temp);
    }

    public int remove(T data){
        int index = minHeap.indexOf(data);
        if (minHeap.isEmpty()||index == -1)
            return -1;
        int size = minHeap.size();
        minHeap.set(index,minHeap.get(size-1));
        minHeap.remove(size-1);
        if (minHeap.size()>0&&index<minHeap.size())
            filterDown(index,minHeap.size()-1);
        return 0;
    }

    @Override
    public String toString() {
        return minHeap.toString();
    }
}
