package com.duanyi.array;

/**
 * 快慢双指针
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[]  nums = {1,1,2};
        System.out.println(removeDuplicates(nums));
    }

    public static  int removeDuplicates(int[] nums) {
           int i = 0;
                for(int j=1; j<nums.length; j++){
                    if(nums[i] != nums[j]){
                        i++;
                        nums[i] = nums[j];
                    }
                }

            return i+1;
    }
}
