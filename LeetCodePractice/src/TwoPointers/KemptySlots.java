package TwoPointers;

/*Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.
If there isn’t such day, output -1.

Example 1:
Input: 
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
Example 2:
Input: 
flowers: [1,2,3]
k: 1
Output: -1
http://www.cnblogs.com/grandyang/p/8415880.html*/

public class KemptySlots {
	public int kEmptySlots(int[] flowers, int k) {
		int len = flowers.length;
		int[] days = new int[len];
		for(int i = 0; i < len; i++){
			days[flowers[i] - 1] = i + 1; 
		}
		int left = 0, right = k + 1, result = Integer.MAX_VALUE;
		for(int i = 0; right < days.length; i++){
			if(days[i] < days[left] || days[i] <= days[right]){
				if(i == right){
					result = Math.max(days[left], days[right]);
				}
				left = i;
				right = i + k + 1;
			}
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}
}
