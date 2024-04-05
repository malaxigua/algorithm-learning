package lanqiao;

import java.io.*;
import java.util.*;

public class Main {
    static class FastReader{
        StringTokenizer st;
        BufferedReader br;
        
        public FastReader() {
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        
        private String next(){
            try {
                if (st == null||!st.hasMoreElements()) {
                    st=new StringTokenizer(br.readLine());
                }
            }catch (IOException e){
                e.printStackTrace();
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
        
        String readLine(){
            String str="";
            try{
                str=br.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }

    static class Pair implements Comparable<Pair>{
        long first;
        int second;

        public Pair(long first,int second){
            this.first=first;
            this.second=second;
        }

        public int compareTo(Pair o){
            if (Long.compare(this.first,o.first)>0){
                return 1;
            }else if (Long.compare(this.first,o.first)==0){
                return Integer.compare(this.second,o.second);
            }else{
                return -1;
            }

        }
    }

    static FastReader in=new FastReader();
    public static void main(String[] args) {
        int n=in.nextInt();
        int k=in.nextInt();

        PriorityQueue<Pair> q = new PriorityQueue<>();
        int[] pre=new int[n+2];
        int[] ne=new int[n+2];
        long[] cnt=new long[n+2];
        long[] a=new long[n+2];

        for (int i=1;i<=n;i++){
            long t=in.nextLong();
            q.add(new Pair(t,i));
            pre[i]=i-1;
            ne[i]=i+1;
        }

        while (q.size()>n-k){
            long x=q.peek().first;
            int id=q.peek().second;
            q.poll();
            if (cnt[id]!=0){
                q.add(new Pair(x+cnt[id],id));
                cnt[id]=0;
            }else{
                int left=pre[id];
                int right=ne[id];
                cnt[left]+=x;
                cnt[right]+=x;
                ne[left]=right;
                pre[right]=left;
            }
        }

        while (!q.isEmpty()){
            long x=q.peek().first;
            int id=q.peek().second;
            q.poll();
            a[id]=x+cnt[id];
        }

        for (int i=1;i<=n;i++){
            if (a[i]!=0){
                System.out.print(a[i]+" ");
            }
        }

    }
}
