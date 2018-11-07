package TwoPointers;
/*Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.*/

//http://www.cnblogs.com/springfor/p/3877101.html
public class TrappingRainWater {
    public int trap(int[] A) {
         if(A == null || A.length == 0){
        	 return 0;
         }
         int total = 0, len = A.length;
         int[] left = new int[len];
         int[] right = new int[len];
         
         //from left to right
         left[0] = A[0];
         for(int i = 1; i < len; i++){
        	 left[i] = Math.max(left[i - 1], A[i]);
         }
         
         //from right to left
         right[len-1] = A[len-1];
         for(int i = len-2; i>=0; i--){
        	 right[i] = Math.max(right[i + 1], A[i]);
         }
         
         // trap water
         for(int i = 1; i < len-1; i++){
        	int bit = Math.min(left[i], right[i]) - A[i];
        	total += bit;
         }
      
         return total;
	}
}
