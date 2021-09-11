package unionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/*
You are given an integer array nums, and you can perform the following operation any number of times on nums:

Swap the positions of two elements nums[i] and nums[j] if gcd(nums[i], nums[j]) > 1 where gcd(nums[i], nums[j]) is the greatest common divisor of nums[i] and nums[j].
Return true if it is possible to sort nums in non-decreasing order using the above swap method, or false otherwise.



Example 1:

Input: nums = [7,21,3]
Output: true
Explanation: We can sort [7,21,3] by performing the following operations:
- Swap 7 and 21 because gcd(7,21) = 7. nums = [21,7,3]
- Swap 21 and 3 because gcd(21,3) = 3. nums = [3,7,21]
Example 2:

Input: nums = [5,2,6,2]
Output: false
Explanation: It is impossible to sort the array because 5 cannot be swapped with any other element.
Example 3:

Input: nums = [10,5,9,3,15]
Output: true
We can sort [10,5,9,3,15] by performing the following operations:
- Swap 10 and 15 because gcd(10,15) = 5. nums = [15,5,9,3,10]
- Swap 15 and 3 because gcd(15,3) = 3. nums = [3,5,9,15,10]
- Swap 10 and 15 because gcd(10,15) = 5. nums = [3,5,9,10,15]


Constraints:

1 <= nums.length <= 3 * 10^4
2 <= nums[i] <= 10^5

analysis:
union find
Use sieve with time complexity O(N) to calculate spf[x] array, where spf[x] is the smallest prime factor of number x, where x >= 2

 */
public class GCDSortOfAnArray {
    int[] parent, size, spf;
    public boolean gcdSort(int[] nums) {
        int len = nums.length;
        int most = Arrays.stream(nums).max().getAsInt();
        parent = new int[most];
        size = new int[most];
        for(int i = 0; i < most; i++){
            parent[i] = i;
            size[i] = 1;
        }
        // pre compute the spf and union-find
        sieve(most);
        for(int n : nums){
            for(int factor : getFactors(n)){
                union(factor, n);
            }
        }
        int[] sorted = Arrays.copyOf(nums, len);
        Arrays.sort(sorted);
        for(int i = 0; i < len; i++){
            int root_s = findRoot(sorted[i]);
            int root_o = findRoot(nums[i]);
            if(root_o != root_s){
                // can't swap nums[i] to sortedArr[i]
                return false;
            }
        }
        return true;
    }

    private void sieve(int n){
        spf = new int[n + 1];
        for(int i = 2; i <= n; i++){
            spf[i] = i;
        }
        for(int i = 2; i * i <= n; i++){
            if(spf[i] == i){
                for(int j = i * i; j <= n; j += i){
                    if(spf[j] == j){
                        spf[j] = i;
                    }
                }
            }
        }
    }

    private int findRoot(int x){
        int root = x;
        while(parent[root] != root){
            root = parent[root];
        }
        while(parent[x] != root){
            int fa =parent[x];
            parent[x] = root;
            x = fa;
        }
        return root;
    }

    private boolean union(int a, int b){
        int root_a = findRoot(a);
        int root_b = findRoot(b);
        if(root_a == root_b){
            return false;
        }
        if(size[root_a] < size[root_b]){
            size[root_b] += size[root_a];
            size[root_a] = 0;
            parent[root_a] = root_b;
        } else {
            size[root_a] += size[root_b];
            size[root_b] = 0;
            parent[root_b] = root_a;
        }
        return true;
    }

    private List<Integer> getFactors(int n){
        // O(logN)
        List<Integer> factors = new ArrayList<>();
        while(n > 1){
            factors.add(spf[n]);
            n /= spf[n];
        }
        return factors;
    }

}

