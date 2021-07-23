package array;
/*
You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.

If it is possible, return any [i, j] with i + 1 < j, such that:

arr[0], arr[1], ..., arr[i] is the first part,
arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
All three parts have equal binary values.
If it is not possible, return [-1, -1].

Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.



Example 1:

Input: arr = [1,0,1,0,1]
Output: [0,3]
Example 2:

Input: arr = [1,1,0,1,1]
Output: [-1,-1]
Example 3:

Input: arr = [1,1,0,0,1]
Output: [0,2]


Constraints:

3 <= arr.length <= 3 * 10^4
arr[i] is 0 or 1

 */
public class ThreeEqualParts {
    public int[] threeEqualParts(int[] arr) {
        int len = arr.length;
        int ones = 0;
        for(int n : arr){
            if(n == 1){
                ones++;
            }
        }
        if(ones == 0){
            return new int[]{0, len - 1};
        }
        if(ones % 3 != 0){
            return new int[]{-1, -1};
        }
        int partOnes = ones / 3;
        int k = len - 1, cnt = 0;
        while(cnt < partOnes){
            if(arr[k] == 1){
                cnt++;
            }
            k--;
        }
        k++;
        int i = findEndIdx(arr, 0, k);
        if(i == -1){
            return new int[]{-1, -1};
        }
        int j = findEndIdx(arr, i + 1, k);
        if(j == -1){
            return new int[]{-1, -1};
        }
        return new int[]{i, j + 1};
    }

    private int findEndIdx(int[] arr, int s, int e) {
        while(arr[s] == 0){
            s++;
        }
        while(e < arr.length){
            if(arr[s] != arr[e]){
                return -1;
            }
            s++;
            e++;
        }
        return s - 1;
    }
}

