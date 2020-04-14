package Greedy;
/*
        A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.
        Given three integers a, b and c, return any string s, which satisfies following conditions:

        s is happy and longest possible.
        s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
        s will only contain 'a', 'b' and 'c' letters.
        If there is no such string s return the empty string "".

        Example 1:
        Input: a = 1, b = 1, c = 7
        Output: "ccaccbcc"
        Explanation: "ccbccacc" would also be a correct answer.

        Example 2:
        Input: a = 2, b = 2, c = 1
        Output: "aabbc"

        Example 3:
        Input: a = 7, b = 1, c = 0
        Output: "aabaa"
        Explanation: It's the only correct answer in this case.

        Constraints:
        0 <= a, b, c <= 100
        a + b + c > 0

Intuition: this is almost identical to 984. String Without AAA or BBB. We just need to ignore the smallest count in each round.
Aassuming a >= b >= c: always try to add 'aa'. If a - 2 >= b, add 'b' (if not, the next round will add 'bb'). Repeat recursivelly for the remaining counts.

In other words, we are greedily use two characters from the largest pile. We cusion these two characters with a character from the medium pile.
For [11,5,3], as an example, we first generate 'aabaabaab', and our piles become [5,2,3]. At this time, c becomes the medium pile, and we generate '..aac' ([3,2,2]).
After we add one more '..aa', c becomes the largest pile and we pull two characters from it '..cc' ([1,2,0]). We add the rest '..bba', and the resulting string is 'aabaabaabaacaaccbba'.
*/
public class LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        return dfs(a, b, c, "a", "b", "c");
    }

    private String dfs(int a, int b, int c, String as, String bs, String cs){
        if(a < b){
            return dfs(b, a, c, bs, as, cs);
        }
        if(b < c){
            return dfs(a, c, b, as, cs, bs);
        }
        // make sure order a >= b >= c
        StringBuilder sb = new StringBuilder();
        int a_use = Math.min(a, 2);
        for(int i = 0; i < a_use; i++){
            sb.append(as);
        }
        if(b == 0){
            return sb.toString();
        }
        int b_use = a - a_use >= b ? 1 : 0;
        for(int i = 0; i < b_use; i++){
            sb.append(bs);
        }
        sb.append(dfs(a - a_use, b - b_use, c, as, bs, cs));
        return sb.toString();
    }
}
