package common;

import java.util.Random;

public class Sort {
    /**
     * 选择排序
     * @param arr
     */
    static void selectionSort(int[] arr){
        int n=arr.length;
        for (int i=0;i<n;i++){
            int cur=i;
            for (int j=i+1;j<n;j++){
                if (arr[j]<arr[cur]){
                    cur=j;
                }
            }
            swap(arr,i,cur);
        }
    }
    
    static void swap(int[] arr,int a,int b){
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }
    
    /**
     * 冒泡排序
     * @param arr
     */
    static void bubbleSort(int[] arr){
        int n=arr.length;
        boolean flag=true;
        while (flag){
            flag=false;
            for (int i=0;i<n-1;i++){
                if (arr[i]>arr[i+1]){
                    flag=true;
                    swap(arr,i,i+1);
                }
            }
        }
    }
    
    /**
     * 插入排序
     * @param arr
     */
    static void insertionSort(int[] arr){
        int n=arr.length;
        for (int i=1;i<n;i++){
            int key=arr[i];
            int j=i-1;
            while (j>=0&&arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }
    
    /**
     * 计数排序
     * @param arr
     */
    static void countingSort(int[] arr){
        int n=arr.length;
        int N=100010;
        int W=100010;
        int w=0;
        int[] b=new int[N];
        for (int a:arr) w=Math.max(w,a);
        int[] cnt=new int[W];
        for (int i=0;i<n;i++) cnt[arr[i]]++;
        for (int i=0;i<=w;i++) cnt[i]+=cnt[i-1];
        for (int i=n-1;i>=0;i--) {
            b[cnt[arr[i]]]=arr[i];
            cnt[arr[i]]--;
        }
    }
    
    /**
     * 基数排序
     * @param arr
     */
    static void radixSort(int[] arr){
    
    }
    
    /**
     * 快速排序
     * 三路快速排序在随机选取分界点 m 后，将待排数列划分为三个部分：小于 m、等于 m 以及大于 m。
     * 这样做即实现了将与分界元素相等的元素聚集在分界元素周围这一效果。
     * @param arr
     */
    @Deprecated
    static void quickSort(int[] arr,int start,int end){
        int n=end-start+1;
        if (n<=1) return;
        int pivot=arr[start];
        // i：当前操作的元素下标
        // arr[0, j)：存储小于 pivot 的元素
        // arr[k, len)：存储大于 pivot 的元素
        int i=0,j=0,k=n;
        // 完成一趟三路快排，将序列分为：
        // 小于 pivot 的元素 | 等于 pivot 的元素 | 大于 pivot 的元素
        while (i<k){
            if (arr[i]<pivot){
                swap(arr,i,j);
                j++;
            }else if (arr[i]>pivot){
                swap(arr,i,--k);
            }else i++;
        }
        
        quickSort(arr,0,j);
        quickSort(arr,k,n-k);
    }
}
