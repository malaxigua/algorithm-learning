package LeetCode;

public class LeetCode416 {
    public boolean canPartition(int[] nums){
        int n=nums.length;
        int sum=0;
        for (int num : nums) {
            sum+=num;
        }
        int target=sum/2;
        if (target*2!=sum) return false;
        
        int[] f=new int[target+1];
        for (int j=0;j<target;j++){
            f[j]= j>=nums[0]?nums[0]:0;
        }
        
        for (int i=1;i<n;i++){
            int t=nums[i];
            for (int j=target;j>=0;j--){
                int no=f[j];
                int yes=j>=t?f[j-t]+t:0;
                f[j]=Math.max(no,yes);
            }
        }
        return f[target]==target;
    }
}
