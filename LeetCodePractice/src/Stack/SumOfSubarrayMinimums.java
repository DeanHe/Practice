package Stack;
// https://leetcode.com/problems/sum-of-subarray-minimums/solution/
// Approach 2: Maintain Stack of Minimums
import java.util.*;

public class SumOfSubarrayMinimums {
	public int sumSubarrayMins(int[] A) {
        int MOD = 1_000_000_007;
        int res = 0, temp = 0;
        Stack<RepInteger> stack = new Stack<>();
        for(int j = 0; j < A.length; j++){
            int count = 1;
            while(!stack.isEmpty() && stack.peek().val >= A[j]){
                RepInteger rep = stack.pop();
                count += rep.count;
                temp -= rep.count * rep.val;
            }
            stack.push(new RepInteger(A[j], count));
            temp += count * A[j];
            res += temp;
            res %= MOD;
        }
        return res;
    }
    class RepInteger {
        int val, count;
        public RepInteger(int v, int c){
            val = v;
            count = c;
        }
    }
}
