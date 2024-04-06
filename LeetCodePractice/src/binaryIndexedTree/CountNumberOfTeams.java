package binaryIndexedTree;

import java.util.Arrays;

/*
There are n soldiers standing in a line.Each soldier is assigned a unique rating value.
You have to form a team of 3soldiers amongst them under the following rules:

Choose 3 soldiers with index(i,j,k)with rating(rating[i],rating[j],rating[k]).
A team is valid if:(rating[i]<rating[j]<rating[k])or(rating[i]>rating[j]>rating[k])where(0<=i<j<k<n).
Return the number of teams you can form given the conditions.(soldiers can be part of multiple teams).


Example 1:
Input:rating=[2,5,3,4,1]
Output:3
Explanation:We can form three teams given the conditions.(2,3,4),(5,4,1),(5,3,1).
Example 2:

Input:rating=[2,1,3]
Output:0
Explanation:We can't form any team given the conditions.
Example 3:

Input:rating=[1,2,3,4]
Output:4


Constraints:
n==rating.length
1<=n<=200
1<=rating[i]<=10^5

analysis:
TC: O(N log N)
*/
public class CountNumberOfTeams {
    int[] left, right;
    public int numTeams(int[] rating) {
        int res = 0, len = rating.length;
        left = new int[len + 1];
        right = new int[len + 1];
        compress(rating);
        for(int i = 0; i < len; i++){
            update(right, rating[i] , 1);
        }
        for(int i = 0; i < len; i++){
            update(right, rating[i] , -1);
            int leftSmaller = getPreSum(left,rating[i] - 1);
            int leftBigger = getSufSum(left,rating[i] - 1);;
            int rightSmaller = getPreSum(right, rating[i]);
            int rightBigger = getSufSum(right, rating[i]);
            res += leftSmaller * rightBigger + leftBigger * rightSmaller;
            update(left, rating[i], 1);
        }
        return res;
    }

    private void compress(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        for(int i = 0; i < nums.length; i++){
            nums[i] = Arrays.binarySearch(sorted, nums[i]);
        }
    }

    private void update(int[] arr, int idx, int val){
        for(int i = idx + 1; i < arr.length; i += lowbit(i)){
            arr[i] += val;
        }
    }

    private int getPreSum(int[] arr, int idx){
        int sum = 0;
        for(int i = idx + 1; i > 0; i -= lowbit(i)){
            sum += arr[i];
        }
        return sum;
    }

    private int getSufSum(int[] arr, int idx){
        return getPreSum(arr, arr.length - 2) - getPreSum(arr, idx);
    }

    private int lowbit(int x){
        return x & -x;
    }
}
