package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static class FastReader{
        StringTokenizer st;
        BufferedReader br;
        
        public FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next(){
            while (st==null||!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        int nextInt(){
            return Integer.parseInt(next());
        }
        
        long nextLong(){
            return Long.parseLong(next());
        }
        
        double nextDouble(){
            return Double.parseDouble(next());
        }
        
        String nextLine(){
            String str="";
            try{
                str=br.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
    
    static PrintWriter out=new PrintWriter(System.out);
    static FastReader in =new FastReader();
    int mod=(int) 1e9+7;
    public int findTargetSumWays(int[] nums, int target) {
        int n=nums.length;
        int s=0;
        for (int i:nums) s+=Math.abs(i);
        if (Math.abs(target)>s) return 0;
        int[][] f=new int[n+1][2*s+1];
        f[0][0+s]=1;
        for (int i=1;i<=n;i++){
            int x=nums[i-1];
            for (int j=-s;j<=s;j++){
                if ((j-x)+s>=0) f[i][j+s]+=f[i-1][(j-x)+s];
                if ((j+x)+s<=2*s) f[i][j+s]+=f[i-1][(j+x)+s];
            }
        }
        return f[n][target+s];
    }
}
