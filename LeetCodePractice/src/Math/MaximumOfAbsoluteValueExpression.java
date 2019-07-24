package Math;
/*Given two arrays of integers with equal lengths, return the maximum value of:

|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|

where the maximum is taken over all 0 <= i, j < arr1.length.

Example 1:

Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
Output: 13
Example 2:

Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
Output: 20
 

Constraints:

2 <= arr1.length == arr2.length <= 40000
-10^6 <= arr1[i], arr2[i] <= 10^6

|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
can be (if i < j):
1 arr1[i] + arr2[i] - i - arr1[j] - arr2[j] + j
2 arr1[i] - arr2[i] - i - arr1[j] + arr2[j] + j
3 -arr1[i] + arr2[i] - i + arr1[j] - arr2[j] + j
4 -arr1[i] - arr2[i] - i + arr1[j] + arr2[j] + j
*/
public class MaximumOfAbsoluteValueExpression {
	public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int len = arr1.length, res = 0;
        int max_mm = Integer.MIN_VALUE / 2;
        int max_mp = Integer.MIN_VALUE / 2;
        int max_pm = Integer.MIN_VALUE / 2;
        int max_pp = Integer.MIN_VALUE / 2;
        for(int i = 0; i < len; i++){
        	max_mm = Math.max(max_mm, -arr1[i] - arr2[i] - i);
        	max_mp = Math.max(max_mp, -arr1[i] + arr2[i] - i);
        	max_pm = Math.max(max_pm, arr1[i] - arr2[i] - i);
        	max_pp = Math.max(max_pp, arr1[i] + arr2[i] - i);
        	res = Math.max(res, max_mm + arr1[i] + arr2[i] + i);
        	res = Math.max(res, max_mp + arr1[i] - arr2[i] + i);
        	res = Math.max(res, max_pm - arr1[i] + arr2[i] + i);
        	res = Math.max(res, max_pp - arr1[i] - arr2[i] + i);
        }
        return res;
    }
}
