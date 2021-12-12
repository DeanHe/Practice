package monotonicQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/*
given an array of integer represent the color of the fence, the larger value means the color is deeper
you can paint a deeper color over a light color, but can't vice verse.
ask what is the minimum operations of paint needed ?
 */
public class PaintFenceGoogle {
    public int minPaint(int[] fence){
        int res = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for(int n : fence){
            while(!deque.isEmpty() && deque.peekLast() >= n){
                if(deque.peekLast() > n){
                    res++;
                }
                deque.pollLast();
            }
            deque.offerLast(n);
        }
        return res + deque.size();
    }
}
