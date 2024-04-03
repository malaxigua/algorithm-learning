package LeetCode;

import java.util.Arrays;

public class LeetCode926 {
    public int minFlipsMonoIncr(String s) {
        boolean flag=false;
        if (flag)
            return solution1(s);
        else
            return solution2(s);
    }
    int solution1(String s){
        char[] cs=s.toCharArray();
        int n=cs.length,ans=0;
        int[] g=new int[n+10];
        Arrays.fill(g,n+10);
        for (int i=0;i<n;i++){
            int t=s.charAt(i)-'0';
            int l=1,r=i+1;
            while (l<r){
                int mid=l+r>>1;
                if (g[mid]>t) r=mid;
                else l=mid+1;
            }
            g[r]=t;
            ans=Math.max(ans,r);
        }
        return n-ans;
    }
    
    int solution2(String s){
        char[] cs=s.toCharArray();
        int n=cs.length,ans=n;
        int[] sum=new int[n+10];
        for (int i=1;i<=n;i++) sum[i]=sum[i-1]+(cs[i-1]-'0');
        for (int i=1;i<=n;i++){
            int l=sum[i-1],r=(n-i)-(sum[n]-sum[i]);
            ans=Math.min(ans,l+r);
        }
        return ans;
    }
}
