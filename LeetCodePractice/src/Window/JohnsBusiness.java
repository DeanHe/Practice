package Window;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*
There are n cities on an axis, numbers from 0 ~ n - 1. John intends to do business in these n cities, He is interested in Armani's shipment.
Each city has a price for these goods prices [i].
For city x, John can buy the goods from the city numbered from x - k to x + k, and sell them to city x. We want to know how much John can earn at most in each city?

Example
Example1

Input: prices = [1, 3, 2, 1, 5] and k = 2
Output: [0, 2, 1, 0, 4]
Explanation:
i = 0, John can go to the city 0 ~ 2. He can not make money because the prices in city 1 and city 2 are both higher than the price in city 0, that is, ans[0] = 0;
i = 1, John can go to the city 0~3. He can buy from city 0 or city 3 to earn the largest price difference. That is, ans[1] = 2.
i = 2, John can go to the city 0~4. Obviously, he can earn the largest price difference by buying from city 3. That is, ans[2] = 1.
i = 3, John can go to the city 1~4. He can not make money cause city 3 has the lowest price. That is, ans[3] = 0.
i = 4, John can go to the city 2~4. He can earn the largest price difference by buying from city 3. That is, ans[4] = 4.
Example2

Input: prices = [1, 1, 1, 1, 1] and k = 1
Output: [0, 0, 0, 0, 0]
Explanation:
All cities are the same price, so John can not make money, that is, all ans are 0.
Notice
prices.length range is [2, 100000], k <= 100000.
*/
public class JohnsBusiness {
    /**
     * @param A: The prices [i]
     * @param k:
     * @return: The ans array
     */
    public int[] business(int[] A, int k) {
        int len = A.length;
        int[] res = new int[len];
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < len && i <= k; i++){
            while(!deque.isEmpty() && A[i] < A[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        for(int i = 0; i < len; i++){
            int s = i - k;
            int e = i + k;
            if(deque.peekFirst() == s - 1){
                deque.pollFirst();
            }
            if(e < len){
                while(!deque.isEmpty() && A[e] < A[deque.peekLast()]){
                    deque.pollLast();
                }
                deque.offerLast(e);
            }
            res[i] = A[i] - A[deque.peekFirst()];
        }
        return res;
    }
}
