package common;

import java.util.Arrays;

public class UnionFind {
    
    int[] parent;
    int[] rank;
    
    public UnionFind(int size){
        parent=new int[size];
        rank=new int[size];
        Arrays.fill(rank,1);
        
        for (int i=0;i<size;i++){
            parent[i]=i;
        }
    }
    
    public int find(int x){
        if (parent[x]!=x){
            parent[x]=find(parent[x]);
        }
        return parent[x];
    }
    
    public void union(int x,int y){
        int rootX=find(x);
        int rootY=find(y);
        
        if (rootX!=rootY){
            if (rank[rootX]>rank[rootY]){
                parent[rootY]=rootX;
            }else if (rank[rootX]<rank[rootY]){
                parent[rootX]=rootY;
            }else{
                parent[rootY]=rootX;
                rank[rootX]++;
            }
        }
    }
    
    public boolean isConnected(int x,int y){
        return find(x)==find(y);
    }
}
