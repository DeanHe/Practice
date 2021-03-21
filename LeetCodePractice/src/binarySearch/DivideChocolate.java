package binarySearch;

/*
You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.

Example 1:
Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

Example 2:
Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.

Example 3:
Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]

Constraints:
0 <= K < sweetness.length <= 10^4
1 <= sweetness[i] <= 10^5
*/
public class DivideChocolate {
    /**
     * @param sweetness: an integer array
     * @param K:         an integer
     * @return: return the maximum total sweetness of the piece
     */
    public int maximizeSweetness(int[] sweetness, int K) {
        int start = Integer.MAX_VALUE, end = 0;
        for (int sweet : sweetness) {
            start = Math.min(start, sweet);
            end += sweet;
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(sweetness, mid) >= K + 1) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (count(sweetness, end) >= K + 1) {
            return end;
        } else if (count(sweetness, start) >= K + 1) {
            return start;
        } else {
            return 0;
        }
    }

    private int count(int[] sweetness, int lowestMax) {
        int cnt = 0, sum = 0;
        for (int sweet : sweetness) {
            sum += sweet;
            if (sum >= lowestMax) {
                cnt++;
                sum = 0;
            }
        }
        return cnt;
    }
}
