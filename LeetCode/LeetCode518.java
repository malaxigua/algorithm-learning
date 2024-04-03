package LeetCode;

public class LeetCode518 {
    public int change(int cnt,int[] cs){
        int n=cs.length;
        int[] f=new int[cnt+1];
        f[0]=1;
        for (int i=1;i<=n;i++){
            int val=cs[i-1];
            for (int j=val;j<=cnt;j++){
                f[j]+=f[j-val];
            }
        }
        return f[cnt];
    }
}
