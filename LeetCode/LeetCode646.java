package LeetCode;

import java.util.Arrays;

public class LeetCode646 {
    public int findLongestChain(int[][] pairs) {
        boolean flag=true;
        if (flag) solution1(pairs);
        else solution2(pairs);
        return 0;
    }
    int solution1(int[][] pairs){
        Arrays.sort(pairs,(a,b)->a[0]-b[0]);
        int n= pairs.length,ans=1;
        int[] f=new int[n];
        for (int i=0;i<n;i++){
            f[i]=1;
            for (int j=i-1;j>=0&&f[i]==1;j--){
                if (pairs[j][1]<pairs[i][0]) f[i]=f[j]+1;
            }
            ans=Math.max(ans,f[i]);
        }
        return ans;
    }
    
    int solution2(int[][] pairs){
        Arrays.sort(pairs,(a,b)->a[0]-b[0]);
        int n= pairs.length,ans=1;
        int[] g=new int[n+10];
        Arrays.fill(g,0x3f3f3f3f);
        for (int i=0;i<n;i++){
            int l=1,r=i+1;
            while (l<r){
                int mid=l+r>>1;
                if (g[mid]>=pairs[i][0]) r=mid;
                else l=mid+1;
            }
            g[r]=Math.min(g[r],pairs[i][1]);
            ans=Math.max(ans,r);
        }
        return ans;
    }
}
