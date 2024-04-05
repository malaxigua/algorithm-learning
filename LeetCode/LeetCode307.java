package LeetCode;

/**
 * @ClassName LeetCode307
 * @Description TODO
 * @Author 15279
 * @Date 2024/4/5, 0005 下午 12:47
 **/
public class LeetCode307 {
    /**
     * 树状数组
     * @Param
     * @return
     **/
    class NumArray{
        int[] tr;
        int[] nums;
        int n;
        /**
         * 取得x在二进制下最低的一位1及后面的0所组成的十进制数值
         * @Param
         * @return int
         **/
        int lowbit(int x){
            return x&-x;
        }
        /**
         * 单点添加
         * @Param
         * @return void
         **/
        void add(int x,int u){
            for (int i=x;i<=n;i+=lowbit(i))
                tr[i]+=u;
        }
        /**
         * 单点查询
         * @Param
         * @return int
         **/
        int query(int x){
            int ans=0;
            for (int i=x;i>0;i-=lowbit(i))
                ans+=tr[i];
            return ans;
        }
        /**
         * 用int数组初始化tr数组，tr数组下标从1开始
         * @Param
         * @return
         **/
        public NumArray(int[] _nums){
            nums=_nums;
            n=nums.length;
            tr=new int[n+10];
            for (int i=0;i<n;i++)
                add(i+1,nums[i]);
        }
        /**
         * 单点更新
         * @Param
         * @return void
         **/
        public void update(int index,int val){
            add(index+1,val-nums[index]);
            nums[index]=val;
        }
        /**
         * 范围和，用右边的范围和减去左边的范围和
         * @Param
         * @return int
         **/
        public int sumRange(int left,int right){
            return query(right+1)-query(left);
        }
    }
}
