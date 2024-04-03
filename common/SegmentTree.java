package common;

public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;
    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger=merger;
        data=(E[]) new Object[arr.length];
        for (int i=0;i<arr.length;i++){
            data[i]=arr[i];
        }
        tree=(E[]) new Object[4*arr.length];
        
        buildSegmentTree(0,0, data.length-1);
    }
    
    private void buildSegmentTree(int treeIndex,int l,int r){
        if (l==r){
            tree[treeIndex]=data[l];
            return;
        }
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        int mid=l+(r-l)/2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);
        tree[treeIndex]=merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }
    
    public int getSize(){
        return data.length;
    }
    
    public E get(int index){
        if (index<0||index>=data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }
    private int leftChild(int index){
        return 2*index+1;
    }
    private int rightChild(int index){
        return 2*index+2;
    }
    
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append('[');
        for (int i=0;i<tree.length;i++){
            if (tree[i]!=null){
                res.append(tree[i]);
            }else{
                res.append("null");
            }
            if (i!=tree.length-1){
                res.append(",");
            }
        }
        res.append(']');
        return res.toString();
    }
    
    //返回待查询区间[queryL, queryR]的值
    public E query(int queryL,int queryR){
        if (queryL<0||queryL>=data.length||queryR<0||queryR>= data.length||queryL>queryR){
            throw new IllegalArgumentException("Index is Illegal.");
        }
        return query(0,0, data.length-1,queryL,queryR);
    }
    
    //设计递归函数
    //在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if (l==queryL&&r==queryR){
            return tree[treeIndex];
        }
        int mid=l+(r-l)/2;
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        if (queryL>=mid+1){ //待查询区间落在右孩子那边
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }else if (queryR<=mid){ //待查询区间落在左孩子那边
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }
        // l<=queryL<=mid<queryR<=r的情况下，需要左递归和右递归找到更细的范围结果然后合并
        E leftResult=query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult=query(rightTreeIndex,mid+1,r,mid+1,queryR);
        return merger.merge(leftResult,rightResult);
    }
    
    public void set(int index,E e){
        if (index<0||index>=data.length){
            throw new IllegalArgumentException("Index is Illegal.");
        }
        data[index]=e;
        set(0,0,data.length-1,index,e);
    }
    
    private void set(int treeIndex,int l,int r,int index,E e){
        if (l==r){
            tree[treeIndex]=e;
            return;
        }
        int mid=l+(r-l)/2;
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        if (index>=mid+1){
            set(rightTreeIndex,mid+1,r,index,e);
        }else{
            set(leftTreeIndex,l,mid,index,e);
        }
        tree[treeIndex]= merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }
    
    public interface Merger<E>{
        E merge(E a,E b);
    }
    
    public static void main(String[] args) {
        Integer[] nums={-2,0,3,-5,2,-1};
        SegmentTree<Integer> segmentTree=new SegmentTree<>(nums,(a,b)->a+b);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0,2)); //nums[0]+nums[1]+nums[2]
        System.out.println(segmentTree.query(0,5)); //nums[0]+...+nums[5]
    }
}
