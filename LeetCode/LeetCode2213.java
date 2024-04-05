package LeetCode;

/**
 * @ClassName LeetCode2213
 * @Description TODO
 * @Author 15279
 * @Date 2024/4/5, 0005 下午 11:21
 **/
public class LeetCode2213 {
    class Solution{
        class Node{
            int l,r,prefix,suffix,val;
            Node(int _l,int _r){
                l=_l;
                r=_r;
                prefix=suffix=val=1;
            }
        }

        char[] cs;
        Node[] tr;
        void build(int u,int l,int r){
            tr[u]=new Node(l,r);
            if (l==r) return;
            int mid=l+r>>1;
            build(u<<1,l,mid);
            build(u<<1|1,mid+1,r);
        }

        void update(int u,int x,char c){
            if (tr[u].l==x&&tr[u].r==x){
                cs[x-1]=c;
                return;
            }
            int mid=tr[u].l+tr[u].r>>1;
            if (x<=mid) update(u<<1,x,c);
            else update(u<<1|1,x,c);
            pushup(u);

        }

        void pushup(int u){
            Node left=tr[u<<1],right=tr[u<<1|1];
            int aLen=left.r-left.l+1,bLen=right.r-right.l+1;
            char ac=cs[left.r-1],bc=cs[right.l-1];
            tr[u].prefix=left.prefix;
            tr[u].suffix=right.suffix;
            tr[u].val=Math.max(left.val,right.val);
            if (ac==bc){
                if (left.prefix==aLen) tr[u].prefix=aLen+right.prefix;
                if (right.prefix==bLen) tr[u].suffix=bLen+left.suffix;
                tr[u].val=Math.max(tr[u].val,left.suffix+right.prefix);
            }
        }

        int query(int u,int l,int r){
            if (l<=tr[u].l&&tr[u].r<=r) return tr[u].val;
            int ans=0;
            int mid=tr[u].l+tr[u].r>>1;
            if (l<=mid) ans=query(u<<1,l,r);
            if (r>mid) ans=Math.max(ans,query(u<<1|1,l,r));
            return ans;
        }

        public int[] longestRepaeating(String s,String queryCharacters, int[] queryIndices){
            cs=s.toCharArray();
            int n=cs.length,m=queryCharacters.length();
            tr=new Node[n*4];
            build(1,1,n);
            for (int i=0;i<n;i++) update(1,i+1,cs[i]);
            int[] ans=new int[m];
            for (int i=0;i<m;i++){
                update(1,queryIndices[i]+1,queryCharacters.charAt(i));
                ans[i]=query(1,1,n);
            }
            return ans;
        }

    }

}
