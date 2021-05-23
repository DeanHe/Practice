package math;

import java.util.ArrayList;

/*
Given 0, 1,..., n - 1 total n numbers forms a ring, start from 0, every time remove the mth number
(next time start from following number). find out the last number remain in the ring

for example, 0, 1, 2, 3, 4 total 5 numbers in a circle, start from 0 every time remove the 3rd number,
the numbers to remove are 2, 0, 4, 1 so that the last one is 3

Ex1:
input n = 5, m = 3
output 3

Ex2:
input n = 10, m = 17
output 2

constraint：
1 <= n <= 10^5
1 <= m <= 10^6

tag: Joseph Ring
approach 1:
simulate with list, TC O(N ^ 2)
approach 2:
recursion TC O(N)
 */
public class LastNumberInRing {
    public int lastRemaining(int n, int m) {
        ArrayList<Integer> ls = new ArrayList<>();
        for(int i = 0; i < n; i++){
            ls.add(i);
        }
        int i = 0;
        while(ls.size() > 1){
            ls.remove(i);
            i = (i + m  - 1) % ls.size();
        }
        return ls.get(0);
    }

    public int lastRemaining2(int n, int m) {
        return dfs(n, m);
    }

    private int dfs(int n, int m) {
        if(n == 1){
            return 0;
        }
        int x = dfs(n - 1, m);
        return (x + m) % n;
    }
}
