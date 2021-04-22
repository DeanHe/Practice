package hashMap;

import java.util.HashMap;
import java.util.Map;

/*Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.

Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].

Example 1:

Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
Output: 1*/
public class NumberOfEquivalentDominoPairs {
	public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] domino : dominoes){
        	int key = Math.min(domino[0], domino[1]) * 10 + Math.max(domino[0], domino[1]);
        	map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int res = 0;
        for(int val : map.values()){
        	res += val * (val - 1) / 2;
        }
        return res;
    }
}
