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
    
    static FastReader in=new FastReader();
    static PrintWriter pw=new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) {
        int T=1;
        while (T-->0) new Main().run();
        pw.flush();
    }
    
    class Node{
        int to;
        int weight;
        public Node(int v, int w){
            to=v;
            weight=w;
        }
    }
    int[][] dp;
    int N,M,K;
    ArrayList<ArrayList<Node>> graph=new ArrayList<>();
    void dijkstra(){
        for (int[] array : dp) {
            Arrays.fill(array,Integer.MAX_VALUE);
        }
        dp[0][0]=0;
        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{0,0});
        while (!queue.isEmpty()){
            int[] p=queue.poll();
            int u=p[0],j=p[1];
            for (Node node:graph.get(u)){
                int v=node.to,w=node.weight;
                if (j<K){
                    if (dp[v][j+1]>dp[u][j]){
                        dp[v][j+1]=dp[u][j];
                        queue.add(new int[]{v,j+1});
                    }
                }
                if (j==0||j==K){
                    if (dp[v][j]>dp[u][j]+w){
                        dp[v][j]=dp[u][j]+w;
                        queue.add(new int[]{v,j});
                    }
                }
            }
        }
    }
    void run(){
        N=in.nextInt();
        K=in.nextInt();
        M=in.nextInt();
        dp=new int[N+1][K+1];
        for (int i=0;i<N;i++) graph.add(new ArrayList<>());
        while (M-->0){
            int u=in.nextInt();
            int v=in.nextInt();
            int w=in.nextInt();
            graph.get(u).add(new Node(v,w));
            graph.get(v).add(new Node(u,w));
        }
        dijkstra();
        System.out.println(Math.min(dp[N-1][0],dp[N-1][K]));
    }
}
