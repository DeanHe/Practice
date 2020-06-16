package BinarySearch;

/*
You are given a sorted without any duplicate integer array, can you write code to tell me what is the Nth missing integer.

        For example original array:[2,4,7,8,9,15], missing array: [3,5,6,10,11,12,13,14]
        the 1st missing integer is 3,
        the 2nd missing integer is 5,
        the 3rd missing integer is 6
*/

public class FindKthMissingNumber {
    public int findMissing(int[] input, int k){
        int start  = 0, end = input.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            int missingCnt = (input[mid] - input[start] + 1) - (mid - start + 1); // all cnt - origin cnt
            if(k > missingCnt){
                start = mid;
                k -= missingCnt;
            } else {
                end = mid;
            }
        }
        if(input[start] + k >= input[end]){
            return -1;
        }
        return input[start] + k;
    }
}
