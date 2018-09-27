package TwoPointers;
//http://www.cnblogs.com/springfor/p/3877101.html
public class TrappingRainWater {
    public int trap(int[] A) {
         if(A == null || A.length == 0){
        	 return 0;
         }
         int max, total = 0;
         int[] left = new int[A.length];
         int[] right = new int[A.length];
         
         //from left to right
         left[0] = A[0];
         max = A[0];
         for(int i = 1; i < A.length; i++){
        	 left[i] = Math.max(max, A[i]);
        	 max = left[i];
         }
         
         //from right to left
         right[A.length-1] = A[A.length-1];
         max = A[A.length-1];
         for(int i = A.length-2; i>=0; i--){
        	 right[i] = Math.max(max, A[i]);
        	 max= right[i];
         }
         
         // trap water
         for(int i = 1; i < A.length-1; i++){
        	int bit = Math.min(left[i], right[i]) - A[i];
        	total += bit;
         }
      
         return total;
	}
}
