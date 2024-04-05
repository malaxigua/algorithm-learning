package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode699
 * @Description TODO
 * @Author 15279
 * @Date 2024/4/6, 0006 上午 12:23
 **/
public class LeetCode699 {
    class Solution{
        int N=(int) 1e9;
        class Node{
            Node ls,rs;
            int val,add;
        }
        Node root=new Node();
        void update(Node node,int lc,int rc,int l,int r,int v){
            if (l<=lc&&rc<=r){
                node.add=v;
                node.val=v;
                return;
            }
            pushdown(node);
            int mid=lc+rc>>1;
            if (l<=mid) update(node.ls,lc,mid,l,r,v);
            if (r>mid) update(node.rs,mid+1,rc,l,r,v);
            pushup(node);
        }

        int query(Node node,int lc,int rc,int l,int r){
            if (l<=lc&&rc<=r) return node.val;
            pushdown(node);
            int mid=lc+rc>>1,ans=0;
            if (l<=mid) ans=query(node.ls,lc,mid,l,r);
            if (r>mid) ans=Math.max(ans,query(node.rs,mid+1,rc,l,r));
            return ans;
        }

        void pushdown(Node node){
            if (node.ls==null) node.ls=new Node();
            if (node.rs==null) node.rs=new Node();
            if (node.add==0) return;
            node.ls.add=node.add;
            node.rs.add=node.add;
            node.ls.val=node.add;
            node.rs.val=node.add;
            node.add=0;
        }

        void pushup(Node node){
            node.val=Math.max(node.ls.val,node.rs.val);
        }

        public List<Integer> fallingSquares(int[][] ps){
            List<Integer> ans=new ArrayList<>();
            for (int[] info : ps) {
                int x=info[0],h=info[1],cur=query(root,0,N,x,x+h-1);
                update(root,0,N,x,x+h-1,cur+h);
                ans.add(root.val);
            }
            return ans;
        }
    }
}
