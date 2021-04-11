package Greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
You want to form a target string of lowercase letters.

At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.

On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.  You can make up to 10 * target.length turns.

For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)

If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  If the sequence is not possible to stamp, return an empty array.

For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".

Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.



Example 1:

Input: stamp = "abc", target = "ababc"
Output: [0,2]
([1,0,2] would also be accepted as an answer, as well as some other answers.)
Example 2:

Input: stamp = "abca", target = "aabcaca"
Output: [3,0,1]


Note:

1 <= stamp.length <= target.length <= 1000
stamp and target only contain lowercase letters.

analysis:
Greedy
try to stamp from left to right as long as you can, can character stamped to ?
*/
public class StampingTheSequence {
    public int[] movesToStamp(String stamp, String target) {
        int sLen = stamp.length(), tLen = target.length();
        char[] tArr = target.toCharArray();
        Stack<Integer> st = new Stack<>();
        boolean changed = true;
        while(changed){
            changed = false;
            for(int i = 0; i + sLen <= tLen; i++){
                if(canStampAt(stamp, tArr, i)){
                    st.add(i);
                    changed = true;
                }
            }
        }
        for(char c : tArr){
            if(c != '?'){
                return new int[0];
            }
        }
        int[] res = new int[st.size()];
        int i = 0;
        while(!st.isEmpty()){
            res[i] = st.pop();
            i++;
        }
        return res;
    }

    private boolean canStampAt(String stamp, char[] arr, int idx) {
        int sLen = stamp.length();
        boolean change = false;
        for(int i = 0; i < sLen; i++){
            if(arr[i + idx] == '?'){
                continue;
            } else if(arr[i + idx] != stamp.charAt(i)){
                return false;
            } else {
                change = true;
            }
        }
        if(change){
            for(int i = 0; i < sLen; i++){
                arr[i + idx] = '?';
            }
        }
        return change;
    }
}
