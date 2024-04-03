package LeetCode;

public class LeetCode303_307 {
    class NumArray {
        
        private int[] valueNums;
        private int[] segmentTree;
        
        public NumArray(int[] nums) {
            int n=nums.length;
            valueNums=new int[n];
            System.arraycopy(nums,0,valueNums,0,n);
            segmentTree=new int[4*n];
            buildSegmentTree(0,0,n-1);
        }
        
        
        public int sumRange(int left, int right) {
            if (left<0||right<0||left>=valueNums.length||right>=valueNums.length||left>right){
                throw new IllegalArgumentException("Index is Illegal.");
            }
            return range(0,0,valueNums.length-1,left,right);
        }
        
        public void update(int index, int val) {
            if (index<0||index>=valueNums.length){
                throw new IllegalArgumentException("Index is Illegal.");
            }
            update(0,0,valueNums.length-1,index,val);
        }
        
        private void update(int treeIndex,int l,int r,int index,int val){
            if (l==r){
                segmentTree[treeIndex]=val;
                return;
            }
            int leftChild=leftChild(treeIndex);
            int rightChild=rightChild(treeIndex);
            int mid=l+(r-l)/2;
            if (index>=mid+1){
                update(rightChild,mid+1,r,index,val);
            }else {
                update(leftChild,l,mid,index,val);
            }
            segmentTree[treeIndex]=segmentTree[leftChild]+segmentTree[rightChild];
        }
        private int range(int treeIndex,int l,int r,int left,int right){
            if (l==left&&r==right) return segmentTree[treeIndex];
            int leftChild=leftChild(treeIndex);
            int rightChild=rightChild(treeIndex);
            int mid=l+(r-l)/2;
            if (left>mid){
                return range(rightChild,mid+1,r,left,right);
            }else if (right<=mid){
                return range(leftChild,l,mid,left,right);
            }
            int leftResult=range(leftChild,l,mid,left,mid);
            int rightResult=range(rightChild,mid+1,r,mid+1,right);
            return leftResult+rightResult;
        }
        
        private void buildSegmentTree(int treeIndex,int l,int r){
            if (l==r){
                segmentTree[treeIndex]=valueNums[l];
                return;
            }
            int leftTreeIndex=leftChild(treeIndex);
            int rightTreeIndex=rightChild(treeIndex);
            int mid=l+(r-l)/2;
            buildSegmentTree(leftTreeIndex,l,mid);
            buildSegmentTree(rightTreeIndex,mid+1,r);
            segmentTree[treeIndex]=segmentTree[leftTreeIndex]+segmentTree[rightTreeIndex];
        }
        
        private int leftChild(int treeIndex){
            return 2*treeIndex+1;
        }
        
        private int rightChild(int treeIndex){
            return 2*treeIndex+2;
        }
    }

}
