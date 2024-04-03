package lanqiao;
import java.io.*;
import java.util.*;
public class MainTwo{
    static int maxn = 200005,n,m,inf=(int)1e9;
    static long INF = (long)2e18,ans = 0,mod = (int)1e9+7;
    static Scanner sc = new Scanner (System.in);
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st  =new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[]args) throws IOException{
        int T = 1;
        while(T-->0) solve();
        pw.flush();
    }
    static final int I() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
    static class node{
        int to;
        int w;
        public node(int a,int b) {
            to=a;w=b;
        }
    }
    static Vector<Vector<node>> g =new Vector<>();
    static int k=0;
    static int d[][] = new int [1001][11];
    static int cnt=0;
    static void dij() {
        for(int i=0;i<n;i++)
            for(int j=0;j<=k;j++) d[i][j] = inf;
        d[0][0]=0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        while(!q.isEmpty()) {
            int []p = q.poll();
            int x=p[0],j=p[1];
            for(node o:g.get(x)) {
                int y = o.to,w = o.w;
                if(j<k){
                    if(d[y][j+1] > d[x][j]){
                        d[y][j+1] = d[x][j];
                        q.add(new int[]{y,j+1});
                    }
                }
                if(j==0||j==k){
                    if(d[y][j] > d[x][j]+w){
                        d[y][j] = d[x][j]+w;
                        q.add(new int[]{y,j});
                    }
                }
            }
        }
    }
    static void solve() throws IOException{
        n=I();k=I();m=I();
        for(int i=0;i<n;i++) g.add(new Vector<>());
        while(m-->0) {
            int u=I(),v=I(),w=I();
            g.get(u).add(new node(v,w));
            g.get(v).add(new node(u,w));
        }
        dij();
        pw.println(Math.min(d[n-1][0], d[n-1][k]));
    }
}
