package com.alogrithm.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sakura on 2019/5/28.
 * 最大堆：
 * 父结点的键值总是大于或等于任何一个子节点的键值
 */
public class MaxHeap<T extends Comparable<T>> {
    private List<T> maxHeap;

    public MaxHeap(){
        this.maxHeap = new ArrayList<>();
    }

    /*
    * 向上调整法：
    * 当向堆插入数据时，是在数组最后添加元素，因此是从下向上开始调整堆
    * */
    private void filterUp(int start){
        int current = start;
        int parent = (current-1)/2;
        T temp = maxHeap.get(current);
        while (current>0){
            int cmp = temp.compareTo(maxHeap.get(parent));
            if (cmp<=0)
                break;
            else {
                maxHeap.set(current,maxHeap.get(parent));
                current = parent;
                parent = (parent-1)/2;
            }
        }
        maxHeap.set(current,temp);
    }
    //插入函数---数组从0开始存储，size()就表示当前要增加的数组的位置
    public void insert(T data){
        int size = maxHeap.size();
        maxHeap.add(data);
        filterUp(size);
    }
    /*
    * 向下调整法：
    * 在删除节点时，通过将最后一个节点代替删除节点，因此从上向下开始调整
    * */
    private void filterDown(int start,int end){
        int current = start;
        int child = start*2+1;
        T temp = maxHeap.get(current);
        while (child<=end){
            if (child<end&&(maxHeap.get(child).compareTo(maxHeap.get(child+1))<0)){
                child++;
            }
            int cmp = temp.compareTo(maxHeap.get(child));
            if (cmp<0){
                maxHeap.set(current,maxHeap.get(child));
                current = child;
                child = 2*child+1;
            }
            else break;
        }
        maxHeap.set(current,temp);
    }

    public int remove(T data){
        int index = maxHeap.indexOf(data);
        if (maxHeap.isEmpty()||index == -1)
            return -1;
        int size = maxHeap.size();
        maxHeap.set(index,maxHeap.get(size-1));
        maxHeap.remove(size-1);
        if (maxHeap.size()>0&&index<maxHeap.size())
            filterDown(index,maxHeap.size()-1);
        return 0;
    }

    @Override
    public String toString() {
        return maxHeap.toString();
    }
}
