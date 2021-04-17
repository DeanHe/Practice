package dp;

import java.util.Arrays;

public class BoxStacking {
	/* Representation of a box */
	static class Box implements Comparable<Box> {
		// h --> height, w --> width, 
        // d --> depth
		int h, w, d, area;
		
		// for simplicity of solution, 
        // always keep w <= d 
		
		/*Constructor to initialize object*/
		public Box(int h, int w, int d) {
			this.h = h;
			this.w = w;
			this.d = d;
		}
		
		/*To sort the box array on the basis 
        of area in decreasing order of area */
		@Override
		public int compareTo(Box o) {
			return o.area - this.area;
		}
	}
	
	/* Returns the height of the tallest 
    stack that can be formed with give  
    type of boxes */
	public static int maxStackHeight(Box[] arr, int n) {
		Box[] rotation = new Box[n * 3];
		/* New array of boxes is created -
        considering all 3 possible rotations,  
        with width always greater than equal 
        to width */
		for(int i= 0; i < n; i++){
			Box box = arr[i];
			rotation[3*i] = new Box(box.h, Math.max(box.w, box.d), Math.min(box.w, box.d));
			rotation[3*i + 1] = new Box(box.w, Math.max(box.h, box.d), Math.min(box.h, box.d));
			rotation[3*i + 2] = new Box(box.d, Math.max(box.w, box.h), Math.min(box.w, box.h));
		}
		/* Calculating base area of  
        each of the boxes.*/
		for(int i = 0; i < rotation.length; i++){
			rotation[i].area = rotation[i].w * rotation[i].d;
		}
		/* Sorting the Boxes on the bases  
        of Area in non Increasing order.*/
		Arrays.sort(rotation);
		
		/* Initialize msh values for all  
        indexes  
        mem[i] --> Maximum possible Stack Height
                   with box i on top */
		int[] dp = new int[3*n];
		for(int i = 0; i < 3*n; i++){
			dp[i] = rotation[i].h;
		}
		int max = 0;
		for(int i = 0; i < 3*n; i++){
			for(int j = 0; j < i; j++){
				if(rotation[i].w < rotation[j].w && rotation[i].d < rotation[j].d){
					dp[i] = Math.max(dp[i], rotation[i].h + dp[j]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
	
	/* Driver program to test above function */
    /*public static void main(String[] args) { 
          
        Box[] arr = new Box[4]; 
        arr[0] = new Box(4, 6, 7); 
        arr[1] = new Box(1, 2, 3); 
        arr[2] = new Box(4, 5, 6); 
        arr[3] = new Box(10, 12, 32); 
          
        System.out.println("The maximum possible "+ 
                           "height of stack is " +  
                           maxStackHeight(arr,4)); 
    }*/ 
}
