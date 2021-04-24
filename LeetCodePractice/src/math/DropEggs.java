package math;
/*
You're given two eggs, Find k while minimize the number of drops for the worst case. Return the number of drops in the worst case.

        Example
        Example 1:

        Input: 100
        Output: 14
        Example 2:

        Input: 10
        Output: 4
        Clarification
        For n = 10, a naive way to find k is drop egg from 1st floor, 2nd floor ... kth floor. But in this worst case (k = 10), you have to drop 10 times.

        Notice that you have two eggs, so you can drop at 4th, 7th & 9th floor, in the worst case (for example, k = 9) you have to drop 4 times.
*/
public class DropEggs {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int dropEggs(int n) {
        // write your code here
        long sum = 0;
        int i = 1;
        while(sum < n){
            sum += i;
            i++;
        }
        i--;
        return i;
    }
}
