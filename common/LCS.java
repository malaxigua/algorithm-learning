package common;

public class LCS {
    public int longestCommonSubsequence(String text1, String text2) {
        int n=text1.length(),m=text2.length();
        text1=" "+text1;
        text2=" "+text2;
        char[] cs1=text1.toCharArray(),cs2=text2.toCharArray();
        int[][] f=new int[n+1][m+1];
        
        for (int i=0;i<=n;i++) f[i][0]=1;
        for (int j=0;j<=m;j++) f[0][j]=1;
        
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (cs1[i]==cs2[j]) f[i][j]=f[i-1][j-1]+1;
                else f[i][j]=Math.max(f[i-1][j],f[i][j-1]);
            }
        }
        return f[n][m]-1;
    }
}
