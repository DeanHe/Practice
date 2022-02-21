package binaryIndexedTree;

/*
TC: O(NlogN)
SC: O(n)
 */
public class CountSubarraysWithMoreOnesThanZeros {
    int[] bit;
    int MOD = (int) (1e9 + 7);
    int len;

    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        int res = 0;
        len = nums.length;
        bit = new int[2 * len + 1];
        int sum = 0;
        for (int n : nums) {
            if (n == 1) {
                sum += 1;
            } else {
                sum -= 1;
            }
            res += getPrefixSum(sum - 1);
            res %= MOD;
            update(sum, 1);
        }
        return res;
    }

    private void update(int idx, int val) {
        idx += len + 1;
        for (int i = idx + 1; i < bit.length; i += lowbit(i)) {
            bit[i] = val;
        }
    }

    private int getPrefixSum(int idx) {
        int res = 0;
        for (int i = idx + 1; i > 0; i -= lowbit(i)) {
            res += bit[i];
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }
}
