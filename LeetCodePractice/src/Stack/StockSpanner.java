package Stack;

import java.util.*;

// https://leetcode.com/problems/online-stock-span/description/
// stack prices maintains decreasing order
class StockSpanner {
    Stack<Integer> prices, weights;
    public StockSpanner() {
        prices = new Stack<>();
        weights = new Stack<>();
    }
    
    public int next(int price) {
        int w = 1;
        while(!prices.isEmpty() && prices.peek() <= price){
            prices.pop();
            w += weights.pop();
        }
        weights.push(w);
        prices.push(price);
        return w;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
