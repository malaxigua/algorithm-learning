package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastReader {
    StringTokenizer st;
    BufferedReader br;
    
    public FastReader(){
        br=new BufferedReader(new InputStreamReader(System.in));
    }
    
    private String next(){
        while (st==null|| !st.hasMoreElements()){
            try{
                st=new StringTokenizer(br.readLine());
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
