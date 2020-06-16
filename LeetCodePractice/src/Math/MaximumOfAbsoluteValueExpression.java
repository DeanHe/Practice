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

analysis:
Maximum Manhattan Distance

|Intuition
Take |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| as Manhattan distance of two points.
arr1 is the coordinate of points on the x-axis,
arr2 is the coordinate of points on the y-axis.

Explanation
For 3 points on the plane, we always have |AO| - |BO| <= |AB|.
When AO and BO are in the same direction, we have ||AO| - |BO|| = |AB|.

We take 4 points for point O, left-top, left-bottom, right-top and right-bottom.
Each time, for each point B, and find the closest A point to O,

Complexity
Time O(N) for 4 passes
Space O(1)
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
