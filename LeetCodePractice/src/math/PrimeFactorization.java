package math;

import java.util.*;

/*
Prime factorize a given integer.

Example 1:

Input: 10
Output: [2, 5]
Example 2:

Input: 660
Output: [2, 2, 3, 5, 11]
Notice
You should sort the factors in ascending order.
*/
public class PrimeFactorization {
	/**
     * @param num: An integer
     * @return: an integer array
     */
    public List<Integer> primeFactorization(int num) {
        // write your code here
        List<Integer> factors = new ArrayList<>();
        for(int i = 2; i * i <= num; i++){
            while(num % i == 0){
                factors.add(i);
                num /= i;
            }
        }
        if(num != 1){
            factors.add(num);
        }
        return factors;
    }
}
