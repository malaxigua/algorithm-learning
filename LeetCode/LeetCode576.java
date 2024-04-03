package LeetCode;

public class LeetCode576 {
    int mod=(int) 1e9+7;
    
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] f=new int[m*n][maxMove+1];
        
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (i==0) add(i,j,f,n,maxMove);
                if (i==m-1) add(i,j,f,n,maxMove);
                if (j==0) add(i,j,f,n,maxMove);
                if (j==n-1) add(i,j,f,n,maxMove);
            }
        }
        
        int[][] dirs=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        
        for (int step=1;step<=maxMove;step++){
            for (int k=0;k<m*n;k++){
                int x=parseIdx(k,n)[0],y=parseIdx(k,n)[1];
                for (int[] d:dirs){
                    int nx=x+d[0],ny=y+d[1];
                    if (nx>=0&&nx<m&&ny>=0&&ny<n){
                        f[k][step]+=f[getIndex(nx,ny,n)][step-1];
                        f[k][step]%=mod;
                    }
                }
            }
        }
        
        return f[getIndex(startRow,startColumn,n)][maxMove];
    }
    
    void add(int x,int y,int[][] f,int column,int maxMovie){
        int idx=getIndex(x,y,column);
        for (int step=1;step<=maxMovie;step++){
            f[idx][step]++;
        }
    }
    
    int getIndex(int x,int y,int column){
        return x*column+y;
    }
    
    int[] parseIdx(int index,int column){
        return new int[]{index/column,index%column};
    }
}
