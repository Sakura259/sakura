package com.alogrithm.Tree.AVLTree;

/**
 * Created by Sakura on 2019/5/22.
 * 树的高度：当前节点到可以遍历到的最下面的子节点+1
 * 树的深度：根节点到当前节点的位置+1    根节点为1
 * AVL树（自平衡二叉查找树、高度平衡树）：它的任何节点的两个子树的高度差别都 <= 1

 */
public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> mRoot;

    //AVL树的节点（内部类）
    class AVLTreeNode<T extends Comparable<T>>{
        T key;   //键值
        int height;   //高度
        AVLTreeNode<T> leftChild;  //左子节点
        AVLTreeNode<T> rightChild;  //右子节点

        public AVLTreeNode(T key, AVLTreeNode<T> leftChild, AVLTreeNode<T> rightChild) {
            this.key = key;
            this.height = 0;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
    //构造函数
    public AVLTree(){
        mRoot = null;
    }
    //获得树的高度  空树为0
    private int getTreeHeight(AVLTreeNode<T> node){
        if (node!=null)
            return node.height;
        return 0;
    }
    public int getTreeHeight(){
        return getTreeHeight(mRoot);
    }

    //先序遍历
    private void preOrder(AVLTreeNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.key+" ");
            preOrder(tree.leftChild);
            preOrder(tree.rightChild);
        }
    }
    public void preOrder() {
        preOrder(mRoot);
    }

    //中序遍历
    private void inOrder(AVLTreeNode<T> currentNode){
        if (currentNode!=null){
            inOrder(currentNode.leftChild);
            System.out.print(currentNode.key+" ");
            inOrder(currentNode.rightChild);
        }
    }
    public void inOrder(){
        inOrder(mRoot);
    }

    //后序遍历
    private void postOrder(AVLTreeNode<T> currentNode){
        if (currentNode!=null){
            postOrder(currentNode.leftChild);
            postOrder(currentNode.rightChild);
            System.out.print(currentNode.key+" ");
        }
    }
    public void postOrder(){
        postOrder(mRoot);
    }

    //比较两个数的大小
    private int max(int a,int b){
        return a > b ? a : b;
    }

    //返回当前根节点的最小节点
    private AVLTreeNode<T> minimum(AVLTreeNode<T> current) {
        if (current == null)
            return null;

        while(current.leftChild != null)
            current = current.leftChild;
        return current;
    }

    public T minimum() {
        AVLTreeNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;
        return null;
    }

    /*
     * 查找最大结点：返回当前根结点的AVL树的最大结点。
     */
    private AVLTreeNode<T> maximum(AVLTreeNode<T> current) {
        if (current == null)
            return null;

        while(current.rightChild != null)
            current = current.rightChild;
        return current;
    }

    public T maximum() {
        AVLTreeNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;
        return null;
    }

    //LL旋转和LR旋转用于平衡左子树失衡的情况
    //RR旋转和RL旋转用于平衡右子树失衡的情况

    /*
    *           k1                       k2
    *        k2     k3               k4       k1
    *      k4   k5       >>>      k6       k5    k3
    *    k6
    * （1）LL旋转(左子节点变为根节点)：左子树的高度比右子树的高度大2
    * 解决办法：围绕失去平衡的根节点进行，将k2变为根节点，k1变为k2的右子树，k2的右子树k5变为k1的左子树
    * */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k1){
        AVLTreeNode<T> k2;
        k2 = k1.leftChild;  //根节点的左子节点
        k1.leftChild = k2.rightChild;   //k2的右子树k5变为k1的左子树
        k2.rightChild = k1;  //k1变为k2的右子树
        k1.height = max(getTreeHeight(k1.leftChild),getTreeHeight(k1.rightChild))+1;
        k2.height = max(getTreeHeight(k2.leftChild),k1.height)+1;
        return k2;  //返回选择后的根节点
    }

    /*
     *           k1                       k3
     *        k2     k3               k1       k5
     *             k4   k5    >>>  k2    k4       k6
     *                     k6
     * （2）RR旋转（右子节点变为根节点）：右子树的高度比左子树的高度大2
     * 解决办法：围绕失去平衡的根节点进行，将k3变为根节点，k1变为k3的右子树，k3的左子树k4变为k1的右子树
     * */
    private AVLTreeNode<T> rightRightRoration(AVLTreeNode<T> k1){
        AVLTreeNode<T> k3;
        k3 = k1.rightChild;
        k1.rightChild = k3.leftChild;  //k3的左子树k4变为k1的右子树
        k3.leftChild = k1;  //k1变为k3的右子树
        k1.height = max(getTreeHeight(k1.leftChild),getTreeHeight(k1.rightChild))+1;
        k3.height = max(getTreeHeight(k3.rightChild),k1.height)+1;
        return k3;  //返回旋转后的根节点
    }

    /*
     *        k3                           k3                        k2
     *    k1     D                    k2        D                 k1      k3
     *  A   k2         (RR)  >>>   k1    C           (LL) >>>   A    B  C     D
     *     B   C                 A    B
     * （3）LR旋转：左右旋转---先右旋再左旋
     * 解决办法：先围绕k1进行RR旋转，再围绕k3进行LL旋转
     * */
    private AVLTreeNode<T> leftRightRoration(AVLTreeNode<T> k3){
        k3.leftChild = rightRightRoration(k3.leftChild);
        return leftLeftRotation(k3);
    }

    /*
     *        k1                           k1                             k2
     *    A     k3                      A       k2                     k1      k3
     *        k2    D    (LL)  >>>           B     k3     (RR) >>>   A    B  C     D
     *      B   C                                C    D
     * （4）RL旋转：右左旋转---先左旋再右旋
     * 解决办法：先围绕k3进行LL旋转，再围绕k1进行RR旋转
     * */
    private AVLTreeNode<T> rightLeftRoration(AVLTreeNode<T> k1){
        k1.rightChild = leftLeftRotation(k1.rightChild);
        return rightRightRoration(k1);
    }

    /*
    * 插入节点函数
    * 1.当前根节点为空，才将要插入的数据封装成节点进行赋值，否则就在梭巡插入位置
    * 2.元素比当前根节点小，插入左子树中
    *   若插入后出现失衡情况：插入是左子树的左子节点：LL旋转
    *                        插入是左子树的右子节点：LR旋转（先RR再LL）
    * 3.原序比当前跟几点大，插入右子树中
    *   若插入后出现失衡情况：插入是右子树的右子节点:RR旋转
    *                        插入是右子树的左子节点：RL旋转（先LL再RR）
    * */
    private AVLTreeNode<T> insert(AVLTreeNode<T> currentRoot,T key){
        if (currentRoot==null){  //判断当前根节点是否为空
            currentRoot = new AVLTreeNode<T>(key,null,null);
            if (currentRoot==null){
                System.out.println("ERROR:create AVLTree node failed");
                return null;
            }
        }else {
            int cmp = key.compareTo(currentRoot.key);
            if (cmp<0){  //插入当前根节点的左子树中
                currentRoot.leftChild = insert(currentRoot.leftChild,key);
                //从插入的节点从下往上遍历，判断是否失去平衡--由于插入在左子树中，应此失衡后用LL或LR旋转恢复
                if (getTreeHeight(currentRoot.leftChild)-getTreeHeight(currentRoot.rightChild)==2){
                    if (key.compareTo(currentRoot.leftChild.key)<0)  //判断插入的节点在根节点的左子树的左子树中
                        currentRoot = leftLeftRotation(currentRoot);
                    else currentRoot = leftRightRoration(currentRoot);  //插入的节点在根节点的左子树的右子树中
                }
            }else if (cmp>0){  //插入当前根节点的右子树中
                currentRoot.rightChild = insert(currentRoot.rightChild,key);
                //从插入节点从下往上开始遍历，判断是否失衡---由于插入节点在右子树，因此造成时候后用RR或RL旋转恢复
                if (getTreeHeight(currentRoot.rightChild)-getTreeHeight(currentRoot.leftChild)==2){
                    if (key.compareTo(currentRoot.rightChild.key)>0)  //判断插入节点在根节点的右子树的右子树中
                        currentRoot = rightRightRoration(currentRoot);
                    else currentRoot = rightLeftRoration(currentRoot);  //判断插入节点在根节点的右子树的左子树中
                }
            }else {//不能添加相同键值的元素
                System.out.println("add node failed:'can not add the same key!'");
            }
        }
        //记录每个节点的相对高度
        currentRoot.height = max(getTreeHeight(currentRoot.leftChild),getTreeHeight(currentRoot.rightChild))+1;
        return currentRoot;
    }
    public void insert(T key){
        mRoot = insert(mRoot,key);
    }

    //返回节点：搜索从根节点开始遍历符合键值的节点
    private AVLTreeNode<T> search(AVLTreeNode<T> current, T key) {
        if (current==null)
            return current;

        int cmp = key.compareTo(current.key);
        if (cmp < 0)
            return search(current.leftChild, key);
        else if (cmp > 0)
            return search(current.rightChild, key);
        else
            return current;
    }

    public AVLTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    /*
    * 删除节点函数  currentRoot表示要删除的当前根节点
    * 1.先判断删除节点在左子树还是右子树
    * 2.判断到当前节点是需要删除的节点后，该节点的子节点有两个还是一个或零个
    * 3.该节点有两个子节点，判断左右子树的高度
    *  左子树高，则寻找左子树中最大节点来代替
    *  右子树高，则寻找右子树中最小节点来代替
    * */
    private AVLTreeNode<T> remove(AVLTreeNode<T> currentRoot,AVLTreeNode<T> delNode){
        if (currentRoot==null)
            return null;
        int cmp = delNode.key.compareTo(currentRoot.key);
        if (cmp<0){  //要删除的节点在当前根节点的左子树
            currentRoot.leftChild = remove(currentRoot.leftChild,delNode);
            //删除节点后，若AVL树失去平衡，，因为删除节点在左子树所以若失去平衡的话只可能是 右子树的高度-2 = 左子树的高度
            if (getTreeHeight(currentRoot.rightChild)-getTreeHeight(currentRoot.leftChild)==2){
                AVLTreeNode<T> temp = currentRoot.rightChild;  //用于判断进行RR旋转还是RL旋转
                if (getTreeHeight(temp.leftChild)>getTreeHeight(temp.rightChild))
                    rightLeftRoration(currentRoot);
                else rightRightRoration(currentRoot);
            }
        }else if (cmp>0) {  //要删除节点在当前根节点的右子树
            currentRoot.rightChild = remove(currentRoot.rightChild, delNode);
            //删除节点后，若AVL树失去平衡，因为删除节点在右子树，所以若失去平衡的话只可能是 左子树的高度-2 = 右子树的高度
            if (getTreeHeight(currentRoot.leftChild) - getTreeHeight(currentRoot.rightChild) == 2) {
                AVLTreeNode<T> temp = currentRoot.leftChild;  //用于判断进行LL旋转还是LR旋转
                if (getTreeHeight(temp.rightChild) > getTreeHeight(temp.leftChild))
                    leftRightRoration(currentRoot);
                else leftLeftRotation(currentRoot);
            }
        }else{  //当前根节点是对应要删除的点---这里使用懒惰删除，即找到最适合替代删除节点的值进行赋值
            if (currentRoot.leftChild!=null&&currentRoot.rightChild!=null){ //要删除的节点左右子节点都不为空
                //删除节点的左子树的高度>右子树的高度
                if (getTreeHeight(currentRoot.leftChild)>getTreeHeight(currentRoot.rightChild)){
                    //通过寻找删除节点左子树的最大节点。代替要删除节点的值
                    AVLTreeNode<T> max = maximum(currentRoot.leftChild);
                    currentRoot.key = max.key;
                    currentRoot.leftChild = remove(currentRoot.leftChild,max);
                }else {
                    //通过寻找删除节点右子树的最小节点，代替要删除节点的值
                    AVLTreeNode<T> min = minimum(currentRoot.rightChild);
                    currentRoot.key = min.key;
                    currentRoot.leftChild = remove(currentRoot.leftChild,min);
                }
            }else { //当前根节点有一个子节点或没有子节点，直接用子节点替代原节点
                AVLTreeNode<T> temp = currentRoot;
                currentRoot = (currentRoot.leftChild!=null)?currentRoot.leftChild:currentRoot.rightChild;
                temp = null;
            }
        }
        return currentRoot;
    }
    public void remove(T key){
        AVLTreeNode<T> delNode;
        if ((delNode = search(mRoot,key))!=null){
            mRoot = remove(mRoot,delNode);
        }
    }

    //打印AVL树
    private void print(AVLTreeNode<T> tree, T key, int direction) {
        if(tree != null) {
            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key, key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction==1?"right" : "left");
            print(tree.leftChild, tree.key, -1);
            print(tree.rightChild,tree.key,  1);
        }
    }
    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
