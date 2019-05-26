package com.alogrithm.Tree.RBTree;

import java.util.TreeMap;
import java.util.logging.XMLFormatter;

/**
 * Created by Sakura on 2019/5/23.
 * 红黑树（RBTree）：一种特殊的二叉查找树，主要用于存储有序的数据，时间复杂度：O（log2 n）
 * java集合中的TreeMap和TreeSet、Linux虚拟内存的管理   通过红黑树实现的
 *
 *  1.每个节点上是黑色或红色
 *  2.根节点是黑色
 *  3.每个叶子节点是黑色（叶子节点指null的空节点）
 *  4.如果一个节点上红色的，则它的子节点必须是黑色的（反之不一定）-从每个叶子节点到根节点的所有路径上不能有两个连续的红节点
 *  5.从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点
 * 新插入的节点颜色总是红色的：插入黑色节点会违背规则5，而插入红色节点，只有一半的机会违背规则4
 */
public class RBTree<T extends Comparable<T>> {
    private RBTreeNode<T> mRoot;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class RBTreeNode<T extends Comparable<T>>{
        T key;
        boolean color;
        RBTreeNode<T> parent;
        RBTreeNode<T> leftChild;
        RBTreeNode<T> rightChild;

        public RBTreeNode(T key, boolean color, RBTreeNode<T> parent, RBTreeNode<T> leftChild, RBTreeNode<T> rightChild) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
    public RBTree(){
        mRoot = null;
    }
    //返回当前节点的父节点
    private RBTreeNode<T> parentOf(RBTreeNode<T> node) {
        return node!=null ? node.parent : null;
    }
    //返回当前节点的颜色
    private boolean colorOf(RBTreeNode<T> node) {
        return node!=null ? node.color : BLACK;
    }
    //判断当前节点是否为红色
    private boolean isRed(RBTreeNode<T> node) {
        return ((node!=null)&&(node.color==RED)) ? true : false;
    }
    //判断当前节点是否为黑色
    private boolean isBlack(RBTreeNode<T> node) {
        return !isRed(node);
    }
    //设置当前节点的颜色为黑色
    private void setBlack(RBTreeNode<T> node) {
        if (node!=null)
            node.color = BLACK;
    }
    //设置当前节点的颜色为红色
    private void setRed(RBTreeNode<T> node) {
        if (node!=null)
            node.color = RED;
    }
    //设置当前节点的父节点
    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) {
        if (node!=null)
            node.parent = parent;
    }
    //设置当前节点的颜色
    private void setColor(RBTreeNode<T> node, boolean color) {
        if (node!=null)
            node.color = color;
    }

    /*
     * 前序遍历"红黑树"
     */
    private void preOrder(RBTreeNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.key+" ");
            preOrder(tree.leftChild);
            preOrder(tree.rightChild);
        }
    }
    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"红黑树"
     */
    private void inOrder(RBTreeNode<T> tree) {
        if(tree != null) {
            inOrder(tree.leftChild);
            System.out.print(tree.key+" ");
            inOrder(tree.rightChild);
        }
    }
    public void inOrder() {
        inOrder(mRoot);
    }


    /*
     * 后序遍历"红黑树"
     */
    private void postOrder(RBTreeNode<T> tree) {
        if(tree != null)
        {
            postOrder(tree.leftChild);
            postOrder(tree.rightChild);
            System.out.print(tree.key+" ");
        }
    }
    public void postOrder() {
        postOrder(mRoot);
    }

    //返回节点：搜索从根节点开始遍历符合键值的节点
    private RBTreeNode<T> search(RBTreeNode<T> x, T key) {
        if (x==null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.leftChild, key);
        else if (cmp > 0)
            return search(x.rightChild, key);
        else
            return x;
    }

    public RBTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    /*
     * 左旋示意图(对节点k1进行左旋)：
     *     /                             /
     *    k1                             k2
     *   /  \      --(左旋)-.           / \
     *  A    k2                       k1   C
     *     /   \                     /  \
     *    B     C                   A    B
     *   节点的改变需要两端确定：1.父节点的子节点是哪个节点 2.子节点的父节点是哪个节点
     * 1.将k2的左子节点赋值给k1的右子节点；若k2的左子节点非空时，将k1赋给k2左子节点的父节点（2次）
     * 2.将k1的父节点赋给k2的父节点，如果父节点不存在，则为根节点；同时更新k2是父节点的左右子节点（2次）
     * 3.k2的左子节点是k1，k1的父节点是k2（2次）
     */
    private void leftRoration(RBTreeNode<T> k1){
        RBTreeNode<T> k2 = k1.rightChild;
        k1.rightChild = k2.leftChild;
        if (k2.leftChild!=null){
            k2.leftChild.parent = k1;
        }
        k2.parent = k1.parent;
        if (k1.parent==null){
            this.mRoot = k2;
        }else {
            if (k1.parent.leftChild == k1){
                k1.parent.leftChild = k2;
            }else k1.parent.rightChild = k2;
        }
        k2.leftChild = k1;
        k1.parent = k2;
    }

    /*
     * 右旋示意图(对节点k1进行右旋)：
     *       /                              /
     *      k1                             k2
     *     /  \      --(右旋)-.           /  \
     *   k2    A                         B    k1
     *  /  \                                 /  \
     * B    C                               C    A
     *   节点的改变需要两端确定：1.父节点的子节点是哪个节点 2.子节点的父节点是哪个节点
     * 1.将k2的右子节点赋值给k1的左子节点；若k2的右子节点非空时，将k1赋给k2右子节点的父节点（2次）
     * 2.将k1的父节点赋给k2的父节点，如果父节点不存在，则为根节点；同时更新k2是父节点的左右子节点（2次）
     * 3.k2的右子节点是k1，k1的父节点是k2（2次）
     */
    private void rightRoration(RBTreeNode<T> k1){
        RBTreeNode<T> k2 = k1.leftChild;
        k1.leftChild = k2.rightChild;
        if (k2.rightChild!=null){
            k2.rightChild.parent = k1;
        }
        k2.parent = k1.parent;
        if (k1.parent==null){
            this.mRoot = k2;
        }else {
            if (k1.parent.leftChild ==k1)
                k1.parent.leftChild = k2;
            else k1.parent.rightChild = k2;
        }
        k2.rightChild = k1;
        k1.parent = k2;
    }

    /*
    * 插入函数：将节点添加到红黑树中
    * */
    private void insert(RBTreeNode<T> node){
        RBTreeNode<T> y = null;
        RBTreeNode<T> x = this.mRoot;
        int cmp;
        while (x!=null){
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp<0){
                x = x.leftChild;
            }else x = x.rightChild;
        }
        node.parent = y;
        if (y!=null){
            cmp = node.key.compareTo(y.key);
            if (cmp<0)
                y.leftChild = node;
            else y.rightChild = node;
        }else {
            this.mRoot = node;
        }
        node.color = RED; //将当前节点的颜色初始化为红色
        insertFixUp(node);
    }
    //插入函数后造成的红黑树失衡的修正函数
    private void insertFixUp(RBTreeNode<T> node){
        RBTreeNode<T> parentNode,grandParentNode;
        while (((parentNode = parentOf(node))!=null) && isRed(parentNode)){ //存在连续的两个红色节点
            grandParentNode = parentOf(parentNode);
            if (parentNode == grandParentNode.leftChild){  //父节点是祖父节点的左子节点
                RBTreeNode<T> uncleNode = grandParentNode.rightChild;  //叔叔节点上祖父节点的右子节点
                /*
                * Case1：叔叔节点不为空且叔叔节点为红色
                * 1.设置父节点和叔叔节点为黑色
                * 2.设置祖父节点为红色，并设置当前节点为祖父节点，继续遍历
                * */
                if ((uncleNode!=null)&&isRed(uncleNode)){
                    setBlack(parentNode);
                    setBlack(uncleNode);
                    setRed(grandParentNode);
                    node = grandParentNode;
                    continue;
                }
                /*
                * Case2:当前节点是父节点的右节点，叔叔节点为黑色节点（或为空，空节点为黑色）
                * 以当前节点的父节点为旋转支点，进行左旋
                * 操作结束后当前红黑树还未被修正，需要继续Case3
                * */
                if (parentNode.rightChild == node){
                    RBTreeNode<T> temp;
                    leftRoration(parentNode);
                    temp = parentNode;
                    parentNode = node;
                    node = temp;
                }
                /*
                * Case3:当前节点是父节点的左子节点，且叔叔节点为黑色
                * 1.设置父节点为黑色，祖父节点为红色
                * 2.以祖父节点为支点进行右旋
                * 达到修正
                * */
                setBlack(parentNode);
                setRed(grandParentNode);
                rightRoration(grandParentNode);
            }else { //父节点是祖父节点的右子节点
                RBTreeNode<T> uncleNode = grandParentNode.leftChild;
                // Case 1条件：叔叔节点是红色
                if ((uncleNode!=null) && isRed(uncleNode)){
                    setBlack(parentNode);
                    setBlack(uncleNode);
                    setRed(grandParentNode);
                    node = grandParentNode;
                    continue;
                }
                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parentNode.leftChild == node){
                    rightRoration(parentNode);
                    RBTreeNode<T> temp;
                    temp = parentNode;
                    parentNode = node;
                    node = temp;
                }
                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                setBlack(parentNode);
                setRed(grandParentNode);
                leftRoration(grandParentNode);
            }
        }
        setBlack(this.mRoot);
    }
    public void insert(T key){
        RBTreeNode<T> node = new RBTreeNode<>(key,BLACK,null,null,null);
        if (node!=null)
            insert(node);
    }

    //删除函数：删除红黑树的节点
    private void  remove(RBTreeNode<T> node){
        RBTreeNode<T> parentNode,childNode;
        boolean color;
        //被删除节点左右子节点都不为空
        if ((node.leftChild!=null)&&(node.rightChild!=null)){
            RBTreeNode<T> replaceNode = node;
            //获得取代节点---删除节点的右子树的最小节点
            replaceNode = replaceNode.rightChild;
            while (replaceNode.leftChild!=null){
                replaceNode = replaceNode.leftChild;
            }
            //删除节点的父节点与代替节点的连接
            if (parentOf(node)!=null){
                if (parentOf(node).leftChild == node)
                    parentOf(node).leftChild = replaceNode;
                else parentOf(node).rightChild = replaceNode;
            }else this.mRoot = replaceNode;

            childNode = replaceNode.rightChild;
            parentNode = parentOf(replaceNode);
            color = colorOf(replaceNode);
            //代替节点的父节点是删除节点
            if (parentNode == node)
                parentNode = replaceNode;
            else {//删除节点的右子节点与代替节点的右子节点的连接
                if (childNode!=null)
                    setParent(childNode,parentNode);
                parentNode.leftChild = childNode;
                //代替节点与删除节点的右子节点的连接
                replaceNode.rightChild = node.rightChild;
                setParent(node.rightChild,replaceNode);
            }
            //完成删除节点的父节点与代替节点的连接
            replaceNode.parent = node.parent;

            replaceNode.color = node.color;
            //删除节点的左子节点与代替节点的连接
            replaceNode.leftChild = node.leftChild;
            node.leftChild.parent = replaceNode;
            if (color == BLACK) //当代替节点为黑色时会造成红黑树失衡
                removeFixUp(childNode,parentNode);
            node = null;
            return;
        }
        if (node.leftChild!=null)
            childNode = node.leftChild;
        else childNode = node.rightChild;

        parentNode = node.parent;
        color = node.color;
        if (childNode!=null)
            childNode.parent = parentNode;
        if (parentNode!=null){
            if (parentNode.leftChild == node)
                parentNode.leftChild = childNode;
            else parentNode.rightChild = childNode;
        }else this.mRoot = childNode;

        if (color == BLACK)  //当代替节点为黑色时会造成红黑树失衡
            removeFixUp(childNode,parentNode);
        node = null;
    }
    //删除后导致的红黑树失衡的修正函数
    private void removeFixUp(RBTreeNode<T> node,RBTreeNode<T> parentNode){
        RBTreeNode<T> otherNode;
        while ((node==null||isBlack(node) && (node!=this.mRoot))){

        }
    }
//    public void remove(T key){
//        RBTreeNode<T> node;
//        if ((node = search(mRoot,key))!=null)
//            remove(node);
//    }
    /*
     * 打印"红黑树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(RBTreeNode<T> tree, T key, int direction) {
        if(tree != null) {
            if(direction==0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree)?"R":"B", key, direction==1?"right" : "left");
            print(tree.leftChild, tree.key, -1);
            print(tree.rightChild,tree.key,  1);
        }
    }
    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
