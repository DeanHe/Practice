package contest;

/*
Given an array of positive integers target and an array initial of same size with all zeros.

Return the minimum number of operations to form a target array from initial if you are allowed to do the following operation:

Choose any subarray from initial and increment each value by one.
The answer is guaranteed to fit within the range of a 32-bit signed integer.


Example 1:

Input: target = [1,2,3,2,1]
Output: 3
Explanation: We need at least 3 operations to form the target array from the initial array.
[0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
[1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
[1,2,2,2,1] increment 1 at index 2.
[1,2,3,2,1] target array is formed.
Example 2:

Input: target = [3,1,1,2]
Output: 4
Explanation: (initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2] (target).
Example 3:

Input: target = [3,1,5,4,2]
Output: 7
Explanation: (initial)[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1]
                                  -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2] (target).
Example 4:

Input: target = [1,1,1,1]
Output: 1


Constraints:

1 <= target.length <= 10^5
1 <= target[i] <= 10^5

analysis:
Start with first position a[0]. We know that it will take overall a[0] operations to reach a[0] from 0.

Now, 2 things can happen from here:

We encounter a number less than a[0] (a[1] < a[0]): In this case we can simply reuse the same operations that we did for a[0]. i.e If array was (3, 2), we can first perform 3 operations and then use 2 of the same operations in next term. However, going forward, we will only have a[1] operations available for reuse.

We encounter a number greater than a[0] (a[1] > a[0]): In this case we can simply reuse the same operations that we did for a[0]. And additionally, we will perform a[1] - a[0] more operation to reach a[1]. Again, going forward, we will have a[1] operations available for reuse.
 */

public class MinimumNumberOfIncrementsOnSubarraysToFormaTargetArray {
    public int minNumberOperations(int[] target) {
        int totalOperations = target[0];
        for(int i = 1; i < target.length; i++){
            if(target[i - 1] < target[i]){
                totalOperations += target[i] - target[i - 1];
            }
        }
        return totalOperations;
    }
}
