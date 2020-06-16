package SweepLine;
/*
Consider an array of size n with all initial values as 0. Perform given ‘m’ add operations from index ‘a’ to ‘b’ and evaluate highest element in array. An add operation adds 1 to all elements from a to b (both inclusive).
Example :
Input : n = 5 // We consider array {0, 0, 0, 0, 0}
        m = 3.
        a = 2, b = 4.
        a = 1, b = 3.
        a = 1, b = 2.
Output : 300

Explanation : 

After I operation -
A : 0 1 1 1 0

After II operation -
A : 1 2 2 1 0

After III operation -
A : 2 3 2 1 0

Highest element : 300
*/
public class HighestElement {
	 int findHighestElement(int[] arr, int[][] operations){
		 for(int[] op : operations){
			 arr[op[0]]++;
			 arr[op[1] + 1]--;
		 }
		 int highest = Integer.MIN_VALUE;
		 int prefixSum = 0;
		 for(int i = 0; i < arr.length; i++){
			 prefixSum += arr[i];
			 if(prefixSum > highest){
				 highest = prefixSum;
			 }
		 }
		 return highest;
	 }
}
