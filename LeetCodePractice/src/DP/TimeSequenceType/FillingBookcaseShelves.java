package DP.TimeSequenceType;
/*
We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].

We want to place these books in order onto bookcase shelves that have total width shelf_width.

We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width),
then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.
We repeat this process until there are no more books to place.

Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.
For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf,
the third book on the second shelf, and the fourth and fifth book on the last shelf.

Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.

Example 1:


Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.
 

Constraints:

1 <= books.length <= 1000
1 <= books[i][0] <= shelf_width <= 1000
1 <= books[i][1] <= 1000
*/
public class FillingBookcaseShelves {
	public int minHeightShelves(int[][] books, int shelf_width) {
        int len = books.length;
        int[] dp = new int[len + 1]; // dp[i + 1] means the min height of put first i books on shelves
        dp[0] = 0;
        for(int i = 0; i < len; i++){
        	int w = books[i][0];
        	int h = books[i][1];
        	dp[i + 1] = dp[i] + h; // put books[i] a new level
        	 // put books[i] on a previous level, while books[j : i] are on the same last level
        	for(int j = i - 1; j >= 0; j--){
        		int wj = books[j][0];
        		int hj = books[j][1];
        		w += wj;
        		if(w > shelf_width){
        			break;
        		}
        		h = Math.max(h, hj);
				dp[i + 1]  = Math.min(dp[i + 1], dp[j] + h);
        	}
        }
        return dp[len];
    }
}
