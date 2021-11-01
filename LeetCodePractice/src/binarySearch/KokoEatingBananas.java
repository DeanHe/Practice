package binarySearch;
/*
Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.

Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer K such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23


Constraints:

1 <= piles.length <= 10^4
piles.length <= h <= 10^9
1 <= piles[i] <= 10^9
 */
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int start = 1;
        int end = 0;
        for(int n : piles){
            end = Math.max(end, n);
        }
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(canFinish(piles, mid, h)){
                end = mid;
            } else {
                start = mid;
            }
        }
        if(canFinish(piles, start, h)){
            return start;
        }
        if(canFinish(piles, end, h)){
            return end;
        }
        return -1;
    }

    private boolean canFinish(int[] piles, int speed, int h) {
        int time = 0;
        for(int n : piles){
            time += (n + speed - 1) / speed;
            if(time > h){
                return false;
            }
        }
        return true;
    }
}
