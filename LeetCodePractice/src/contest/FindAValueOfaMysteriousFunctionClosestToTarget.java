package contest;
/*
func(arr, l, r) {
    if(r < l){
        return -1000000000;
    }
    ans = arr[l];
    for(i = l + 1; i <= r; i++){
        ans = ans & arr[i];
    }
    return ans;
}
Winston was given the above mysterious function func. He has an integer array arr and an integer target and he wants to find the values l and r that make the value |func(arr, l, r) - target| minimum possible.

Return the minimum possible value of |func(arr, l, r) - target|.

Notice that func should be called with the values l and r where 0 <= l, r < arr.length.



Example 1:

Input: arr = [9,12,3,7,15], target = 5
Output: 2
Explanation: Calling func with all the pairs of [l,r] = [[0,0],[1,1],[2,2],[3,3],[4,4],[0,1],[1,2],[2,3],[3,4],[0,2],[1,3],[2,4],[0,3],[1,4],[0,4]], Winston got the following results [9,12,3,7,15,8,0,3,7,0,0,3,0,0,0]. The value closest to 5 is 7 and 3, thus the minimum difference is 2.
Example 2:

Input: arr = [1000000,1000000,1000000], target = 1
Output: 999999
Explanation: Winston called the func with all possible values of [l,r] and he always got 1000000, thus the min difference is 999999.
Example 3:

Input: arr = [1,2,4,8,16], target = 0
Output: 0


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^6
0 <= target <= 10^7
 */
public class FindAValueOfaMysteriousFunctionClosestToTarget {
    public int closestToTarget(int[] arr, int target) {
        int len = arr.length, res = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++){
            int sum = arr[i];
            for(int j = i; j < len; j++){
                sum = sum & arr[j];
                res = Math.min(res, Math.abs(target - sum));
                if(res == 0){
                    return 0; // best is 0;
                }
                if(sum < target){ // sum is decreasing within inner loop. and now sum < t, res won't be lower.
                    break;
                }
            }
            if(sum > target){
                break;
            }
            // the future sum won't be smaller than this sum, so res won't be smaller
            //current sum = arr[i] & ... & arr[n -1], which is the smallest, any further sum after this loop,
            // which will be arr[k] & ... & arr[n -1], where k > i, will be larger than current sum;
        }
        return res;
    }
}
