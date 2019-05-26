package com.alogrithm.Tree.Huffman;

/**
 * Created by Sakura on 2019/5/26.
 * 哈夫曼树（最优二叉树）：是一种优化判断的树结构
 *               180
 *            /      \
 *          100       80
 *                  /    \
 *                 50    30
 *                      /   \
 *                    20     10
 * （01）路径和路径长度
 *  定义：在一棵树中，从一个结点往下可以到达子孙结点之间的通路，称为路径
 *      通路中分支的数目称为路径长度。   规定：根节点的层数为1，根节点到L层节点的路径长度为 （L-1）
 * （02）节点的权和带权路径长度
 * 定义：若将树中节点赋有一个一个有着某种含义的数值，则这个数值称为该节点的权
 *      节点的带权路径长度为：从根节点到该节点的路径长度与该节点的权的乘积
 *  （03）树的带权路径长度
 *  定义：为所有叶子节点的带权路径长度之和，记WPL
 *  WPL = 1*100 + 50*2 + 20*3 + 10*3 = 290
 */
public class Huffman {

    private HuffmanNode mRoot;	// 根结点

    /*
     * 创建Huffman树
     *
     * @param 权值数组
     */
    public Huffman(int a[]) {
        HuffmanNode parent = null;
        MinHeap heap;

        // 建立数组a对应的最小堆
        heap = new MinHeap(a);

        for(int i=0; i<a.length-1; i++) {
            HuffmanNode left = heap.dumpFromMinNode();  // 最小节点是左孩子
            HuffmanNode right = heap.dumpFromMinNode(); // 其次才是右孩子

            // 新建parent节点，左右孩子分别是left/right；
            // parent的大小是左右孩子之和
            parent = new HuffmanNode(left.key+right.key, left, right, null);
            left.parent = parent;
            right.parent = parent;

            // 将parent节点数据拷贝到"最小堆"中
            heap.insert(parent);
        }

        mRoot = parent;

        // 销毁最小堆
        heap.destroy();
    }

    /*
     * 前序遍历"Huffman树"
     */
    private void preOrder(HuffmanNode tree) {
        if(tree != null) {
            System.out.print(tree.key+" ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"Huffman树"
     */
    private void inOrder(HuffmanNode tree) {
        if(tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key+" ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }


    /*
     * 后序遍历"Huffman树"
     */
    private void postOrder(HuffmanNode tree) {
        if(tree != null)
        {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * 销毁Huffman树
     */
    private void destroy(HuffmanNode tree) {
        if (tree==null)
            return ;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        tree=null;
    }

    public void destroy() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"Huffman树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(HuffmanNode tree, int key, int direction) {

        if(tree != null) {

            if(direction==0)	// tree是根节点
                System.out.printf("%2d is root\n", tree.key);
            else				// tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction==1?"right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right,tree.key,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
