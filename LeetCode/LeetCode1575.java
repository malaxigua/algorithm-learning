package LeetCode;

import java.util.Arrays;

public class LeetCode1575 {
    int mod= (int) (1e9+7);
    int[][] cache;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n= locations.length;
        cache=new int[n][fuel+1];
        for (int[] ints : cache) {
            Arrays.fill(ints,-1);
        }
        return dfs(locations,start,finish,fuel);
    }
    
    int dfs(int[] ls,int u,int end,int fuel){
        if (cache[u][fuel]!=-1) return cache[u][fuel];
        
        int n=ls.length;
        if (fuel==0&&u!=end){
            cache[u][fuel]=0;
            return 0;
        }
        boolean hasNext=false;
        for (int i=0;i<n;i++){
            if (i!=u){
                int need=Math.abs(ls[u]-ls[i]);
                if (fuel>=need){
                    hasNext=true;
                    break;
                }
            }
        }
        if (fuel!=0&&!hasNext){
            int a=cache[u][fuel]=u==end?1:0;
            return a;
        }
        
        int sum= u==end?1:0;
        for (int i=0;i<n;i++){
            if (i!=u){
                int need=Math.abs(ls[i]-ls[u]);
                if (fuel>=need){
                    sum+=dfs(ls,i,end,fuel-need);
                    sum%=mod;
                }
            }
        }
        cache[u][fuel]=sum;
        return sum;
    }
}
