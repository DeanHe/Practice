package PrefixSum;
/*
There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles that are in the substring. A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.

For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
Return an integer array answer where answer[i] is the answer to the ith query.

Example 1:

ex-1
Input: s = "**|**|***|", queries = [[2,5],[5,9]]
Output: [2,3]
Explanation:
- queries[0] has two plates between candles.
- queries[1] has three plates between candles.
Example 2:

ex-2
Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
Output: [9,0,0,0,0]
Explanation:
- queries[0] has nine plates between candles.
- The other queries have zero plates between candles.


Constraints:
3 <= s.length <= 10^5
s consists of '*' and '|' characters.
1 <= queries.length <= 10^5
queries[i].length == 2
0 <= lefti <= righti < s.length

analysis:
for each query, find the left bound closet right candle index, and the right bound the nearest left candle index
TC O(N + K), where K is the number of queries, and N - the length of the string.
TC O(N + K)
 */
public class PlatesBetweenCandles {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] arr = s.toCharArray();
        int[] res = new int[queries.length];
        int[] leftCandleIdx = new int[arr.length];
        int[] rightCandleIdx = new int[arr.length];
        int[] candlePreSum = new int[arr.length];
        int idx = -1, cnt = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == '|'){
                idx = i;
                cnt++;
            }
            leftCandleIdx[i] = idx;
            candlePreSum[i] = cnt;
        }
        idx = -1;
        for(int i = arr.length - 1; i >= 0; i--){
            if(arr[i] == '|'){
                idx = i;
            }
            rightCandleIdx[i] = idx;
        }
        for(int i = 0; i < queries.length; i++){
            int[] query = queries[i];
            int l = query[0];
            int r = query[1];
            int leftCandle = rightCandleIdx[l];
            int rightCandle = leftCandleIdx[r];
            if(leftCandle == -1 || rightCandle == -1){
                res[i] = 0;
            } else {
                if(rightCandle > leftCandle + 1){
                    res[i] = rightCandle - leftCandle - (candlePreSum[rightCandle] - candlePreSum[leftCandle]);
                }
            }
        }
        return res;
    }
}
