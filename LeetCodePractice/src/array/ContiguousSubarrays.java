package array;

/*
You are given an array arr of N integers. For each index i, you are required to determine the number of contiguous subarrays that fulfills the following conditions:
The value at index i must be the maximum element in the contiguous subarrays, and
These contiguous subarrays must either start from or end on index i.
Signature
int[] countSubarrays(int[] arr)
Input
array arr is a non-empty list of unique integers that range between 1 to 1,000,000,000
Size N is between 1 and 1,000,000
Output
An array where each index i contains an integer denoting the maximum number of contiguous subarrays of arr[i]
Example:
arr = [3, 4, 1, 6, 2]
output = [1, 3, 1, 5, 1]
Explanation:
For index 0 - [3] is the only contiguous subarray that starts (or ends) with 3, and the maximum value in this subarray is 3.
For index 1 - [4], [3, 4], [4, 1]
For index 2 - [1]
For index 3 - [6], [6, 2], [1, 6], [4, 1, 6], [3, 4, 1, 6]
For index 4 - [2]
So, the answer for the above input is [1, 3, 1, 5, 1]
 */
public class ContiguousSubarrays {
    int[] countSubarrays(int[] arr) {
        int len = arr.length;
        int[] res = new int[len];
        int[] l = new int[len];
        int[] r = new int[len];
        int maxVal = arr[0], maxIdx = 0, accum = 0;
        for(int i = 1; i < len; i++){
            int cur = arr[i];
            int pre = arr[i - 1];
            if(cur > maxVal){
                maxVal = cur;
                l[i] = l[maxIdx] + i - maxIdx;
                maxIdx = i;
                accum = 0;
            } else {
                if(cur > pre){
                    l[i] = accum;
                }
                accum++;
            }
        }
        maxVal = arr[len - 1];
        maxIdx = len - 1;
        accum = 0;
        for(int i = len - 2; i >= 0; i--){
            int cur = arr[i];
            int pre = arr[i + 1];
            if(cur > maxVal){
                maxVal = cur;
                r[i] = r[maxIdx] + maxIdx - i;
                maxIdx = i;
                accum = 0;
            } else {
                if(cur > pre){
                    r[i] = accum;
                }
                accum++;
            }
        }
        for(int i = 0; i < len; i++){
            res[i] = l[i] + r[i] + 1;
        }
        return res;
    }
}
