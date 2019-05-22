package com.alogrithm.Tree;

import java.util.LinkedList;

/**
 * Created by Sakura on 2019/5/20.
 * 二叉查找树
 * （1）任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * (2) 任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * （3）任意节点的左右子树也为二叉查找树
 * （4）没有键值相等的节点
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private BSNode<T> mRoot;

    public class BSNode<T extends Comparable<T>> {
        T key;      //键值，节点的键值
        BSNode<T> parent;        //父节点
        BSNode<T> leftChild;      //左孩子节点
        BSNode<T> rightChild;     //右孩子节点

        public BSNode(T key, BSNode<T> parent, BSNode<T> leftChild, BSNode<T> rightChild) {
            this.key = key;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
    /*
    *       3
    *   1       5
    *     2   4   6
    *  先序结果：3-1-2-5-4-6
    *  中序结果：1-2-3-4-5-6
    *  后序遍历：2-1-4-6-5-3
    * */

    /*
    * 1.先序遍历（从上往下输出遍历）：根节点在第一个
    * （1）访问根节点
    * （2）先序遍历左子树
    * （3）先序遍历右子树
    * */
    private void preOrder(BSNode<T> currentNode){
        if (currentNode!=null){
            System.out.print(currentNode.key+" ");
            preOrder(currentNode.leftChild);
            preOrder(currentNode.rightChild);
        }
    }
    //非递归版本的先序遍历
    public void noRecursivePreOrder(){
        LinkedList<BSNode<T>> stack = new LinkedList<>();
        BSNode<T> currentNode = mRoot;
        while (currentNode!=null||!stack.isEmpty()){
            if (currentNode!=null){
                System.out.print(currentNode.key+" ");
                stack.push(currentNode);
                currentNode = currentNode.leftChild;
            }else {
                BSNode<T> node = stack.pop();
                currentNode = node.rightChild;
            }
        }
    }
    public void preOrder(){
        preOrder(mRoot);
    }

    /*
    * 2.中序遍历（从根节点遍历左子树，直到不存在左子树，输出当前节点，
    *   再判断当前节点是否存在右子树，存在右子树继续判断是否存在左子树）：根节点在中间
    * （1）中序遍历左子树
    * （2）访问根节点
    * （3）中序遍历右子树
    * */
    private void inOrder(BSNode<T> currentNode){
        if (currentNode!=null){
            inOrder(currentNode.leftChild);
            System.out.print(currentNode.key+" ");
            inOrder(currentNode.rightChild);
        }
    }
    //非递归版本的中序遍历
    public void noRecursiveInOrder(){
        LinkedList<BSNode<T>> stack = new LinkedList<>();
        BSNode<T> currentNode = mRoot;
        while (currentNode!=null||!stack.isEmpty()){
           if (currentNode!=null) {
               stack.push(currentNode);
               currentNode = currentNode.leftChild;
           }else {
               BSNode<T> node = stack.pop();
               System.out.print(currentNode.key+" ");
               currentNode = node.rightChild;
           }
        }
    }
    public void inOrder(){
        inOrder(mRoot);
    }

    /*
    * 3.后序遍历（遍历左子树，直到最后一个节点，再遍历当前节点的右子树）从左子树的最后一个右子节点开始输出：根节点在最后
    * （1）后序遍历左子树
    * （2）后序遍历右子树
    * （3）访问根节点
    * */
    private void postOrder(BSNode<T> currentNode){
        if (currentNode!=null){
            postOrder(currentNode.leftChild);
            postOrder(currentNode.rightChild);
            System.out.print(currentNode.key+" ");
        }
    }
    //非递归版本的后序遍历
    public void noRecursivePostOrder(){
        LinkedList<BSNode<T>> stack = new LinkedList<>();
        BSNode<T> currentNode = mRoot;
        BSNode<T> lastNode = null;
        while (currentNode!=null){  //将左子树节点全部压入栈中
            stack.push(currentNode);
            currentNode = currentNode.leftChild;
        }
        while (!stack.isEmpty()){
            currentNode = stack.pop();
            //一个根节点被访问的前提是：无右子树或右子树已被访问过
            if (currentNode.rightChild!=null&&currentNode.rightChild!=lastNode){
                stack.push(currentNode);
                currentNode = currentNode.rightChild;
                while (currentNode!=null){  //遍历到左子树的最下面
                    stack.push(currentNode);
                    currentNode = currentNode.leftChild;
                }
            }else {
                System.out.print(currentNode.key+" ");
                lastNode = currentNode;  //修改最近判断过的节点，用于判断右子树是否被访问过
            }
        }
    }
    public void postOrder(){
        postOrder(mRoot);
    }

    //查找键值
    private BSNode<T> search(BSNode<T> current,T key){
        if (current==null)
            return current;
        int cmp = key.compareTo(current.key);
        if (cmp>0)
            return search(current.rightChild,key);
        else if (cmp<0){
            return search(current.leftChild,key);
        }
        else return current;
    }
    public BSNode<T> search(T key) {
        return search(mRoot, key);
    }

    //查找最大值
    private BSNode<T> findMax(BSNode<T> current){
        if (current==null)
            return null;
        while (current.rightChild!=null){
            current = current.rightChild;
        }
        return current;
    }
    public T findMax(){
        BSNode<T> node = findMax(mRoot);
        if (node!=null)
            return node.key;
        return null;
    }

    //查找最小值
    private BSNode<T> findMin(BSNode<T> current){
        if (current==null)
            return null;
        while (current.leftChild!=null){
            current = current.leftChild;
        }
        return current;
    }
    public T findMin(){
        BSNode<T> node = findMin(mRoot);
        if (node!=null)
            return node.key;
        return null;
    }

    //插入函数--需要完成连接两个端点：1.插入节点的父节点 2.插入节点上父节点左右子节点
    private void insert(BSNode<T> node){
        int cmp;
        BSNode<T> current = mRoot;   //当前节点
        BSNode<T> parentNode = null;  //当前节点的父节点
        while (current!=null){
            parentNode = current;
            cmp = node.key.compareTo(current.key);  //比较当前节点与要插入节点的大小
            if (cmp<0)   //新插入节点 < 当前节点  ---需要继续遍历当前节点的左子树
                current = current.leftChild;
            else  //新插入节点 >= 当前节点   --需要继续遍历当前节点的右子树
                current = current.rightChild;
        }
        node.parent =parentNode;   //连接插入节点的父节点
        //插入节点是什么节点或是父节点的左右子节点
        if (parentNode==null){
            mRoot = node;
        }else {
            cmp = node.key.compareTo(parentNode.key);
            if (cmp<0)
                parentNode.leftChild = node;
            else
                parentNode.rightChild = node;
        }

    }
    public void insert(T key){
        BSNode<T> node = new BSNode<T>(key,null,null,null);
        if (node!=null)
            insert(node);
    }

    //删除节点
    private boolean delete(T key){
        BSNode<T> current = mRoot;   //用于记录所要删除的节点
        BSNode<T> parentNode = mRoot;  //用于记录所要删除节点的父节点
        boolean isLeftChild = false;   //用于删除节点是否为其父节点的左子节点
        //遍历节点，找到需要删除的节点，记录在current节点中
        while (current.key!=key){
            parentNode = current;
            int cmp = key.compareTo(current.key);
            if (cmp<0){
                isLeftChild = true;  //记录是否为父节点的左子节点
                current = current.leftChild;
            }else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)
                return false;
        }
        //要删除的节点无左右子节点
        if (current.leftChild == null&&current.rightChild == null){
            if (current == mRoot)
                mRoot = null;
            else if (isLeftChild){  //如果是左子节点，则父节点的左子节点为无
                parentNode.leftChild = null;
            }else  //如果是右子节点，则父节点的右子节点为无
                parentNode.rightChild = null;
            return true;
        }
        //当前节点的左子节点不存在而右子节点存在
        else if (current.leftChild == null&&current.rightChild!=null){
            if (current ==mRoot)
                mRoot = null;
            else if (isLeftChild){
                parentNode.leftChild = current.rightChild;
            }else parentNode.rightChild = current.rightChild;
            return true;
        }
        //当前节点的左子节点存在而右子节点不存在
        else if (current.leftChild!=null&&current.rightChild == null){
            if (current == mRoot)
                mRoot = null;
            else if (isLeftChild){
                parentNode.leftChild = current.leftChild;
            }else parentNode.rightChild = current.leftChild;
            return true;
        }
        //当前节点的左右子节点都存在，寻找该节点右子树的最小节点代替---需要连接三个端点
        else{
            BSNode<T> successor = getSuccessor(current);
            //父节点与后继节点的连接
            if (current == mRoot)
                successor = mRoot;
            else if (isLeftChild){
                parentNode.leftChild = successor;
            }else{
                parentNode.rightChild = successor;
            }
            //后继结点与原删除节点左子树的连接
            successor.leftChild = current.leftChild;
            return true;
        }
    }
    //获得后继结点
    private BSNode<T> getSuccessor(BSNode<T> delNode){
        BSNode<T> successorParent = delNode;   //用于记录后继节点的父节点
        BSNode<T> successor = delNode;   //用于记录后继节点
        BSNode<T> current = delNode.rightChild;   //寻找删除节点以右孩子为根的子树的最小值
        //寻找以其右孩子为根的子树的最小节点
        while (current!=null){   //只需要连接两个节点
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        //如果后继结点不是删除节点的右子结点，将删除结点替换为后继节点
        if (successor!=delNode.rightChild){  //需要连接三个节点
            if (successor.rightChild!=null){
                successorParent.leftChild = successor.rightChild;   //将后继节点的右子树转移给后继结点父节点的左子节点
            }
            //后继节点与删除节点的右子树的连接
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
    public void remove(T key){
        delete(key);
    }

    //打印二叉查找树
    private void print(BSNode<T> current,T key,int direction){
        if (current!=null){
            if (direction ==0)
                System.out.printf("%2d is root",current.key);
            else
                System.out.printf("%2d is %d's %6s child",current.key,key,direction==1? "right":"left");
            System.out.println();
            print(current.leftChild,current.key,-1);
            print(current.rightChild,current.key,1);
        }
    }
    public void print(){
        if (mRoot!=null)
            print(mRoot,mRoot.key,0);
    }

    //销毁二叉查找树
    private void destroy(BSNode<T> current) {
        if (current==null)
            return ;
        if (current.leftChild != null)
            destroy(current.leftChild);
        if (current.rightChild != null)
            destroy(current.rightChild);
        current=null;
    }
    public void destroy() {
        destroy(mRoot);
        mRoot = null;
    }


}
