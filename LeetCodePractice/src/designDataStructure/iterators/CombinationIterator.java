package designDataStructure.iterators;

import java.util.LinkedList;
import java.util.Queue;

/*
Design an Iterator class, which has:

A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
A function next() that returns the next combination of length combinationLength in lexicographical order.
A function hasNext() that returns True if and only if there exists a next combination.


Example:

CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

iterator.next(); // returns "ab"
iterator.hasNext(); // returns true
iterator.next(); // returns "ac"
iterator.hasNext(); // returns true
iterator.next(); // returns "bc"
iterator.hasNext(); // returns false


Constraints:

1 <= combinationLength <= characters.length <= 15
There will be at most 10^4 function calls per test.
It's guaranteed that all calls of the function next are valid.

hint:
1 Generate all combinations as a preprocessing.
2 Use bit masking to generate all the combinations.

analysis:
approach 2 generate next combination on next TC O(N). no need for precompute all combination
*/
public class CombinationIterator {

    String characters;
    int charactersLength, combinationLength, mask;
    public CombinationIterator(String characters, int combinationLength) {
        this.characters = characters;
        this.combinationLength = combinationLength;
        charactersLength = characters.length();
        mask = (1 << charactersLength) - 1;
    }

    public String next() {
        while(mask > 0 && Integer.bitCount(mask) != combinationLength){
            mask--;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < charactersLength; i++){
            if((1 & (mask >> i)) == 1){
                sb.append(characters.charAt(i));
            }
        }
        mask--;
        return sb.toString();
    }

    public boolean hasNext() {
        while(mask > 0 && Integer.bitCount(mask) != combinationLength){
            mask--;
        }
        if(mask == 0){
            return false;
        }
        return true;
    }

    /*
    Queue<String> q = new LinkedList<>();
    public CombinationIterator(String characters, int combinationLength) {
        dfs(characters, combinationLength, new StringBuilder(),0);
    }

    public String next() {
        return q.poll();
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }

    private void dfs(String str, int len, StringBuilder sb, int idx){
        if(sb.length() == len){
            q.offer(sb.toString());
            return;
        }
        for(int i = idx; i < str.length(); i++) {
            sb.append(str.charAt(i));
            dfs(str, len, sb, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    */
}
/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * string param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
