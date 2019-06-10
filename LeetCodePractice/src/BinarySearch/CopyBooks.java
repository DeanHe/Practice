package BinarySearch;
/*Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.

These books list in a row and each person can claim a continous range of books. For example, one copier can copy the books from i-th to j-th continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Return the shortest time that the slowest copier spends.

Example
Example 1:

Input: pages = [3, 2, 4], k = 2
Output: 5
Explanation: 
    First person spends 5 minutes to copy book 1 and book 2.
    Second person spends 4 minutes to copy book 3.
Example 2:

Input: pages = [3, 2, 4], k = 3
Output: 4
Explanation: Each person copies one of the books.
Challenge
O(nk) time*/
public class CopyBooks {
	/**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        int start = 0, end = 0;
        for(int val : pages){
        	start = Math.max(start, val);
        	end += val;
        }
        while(start + 1 < end){
        	int mid = start + (end - start) / 2;
        	if(needPeople(mid, pages) <= k){
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        if(needPeople(start, pages) <= k){
        	return start;
        }
        return end;
    }

	private int needPeople(int days, int[] pages) {
		int people = 0, spend = 0;
		for(int cost : pages){
			if(spend + cost > days){
				people++;
				spend = 0;
			}
			spend += cost;
		}
		if(spend > 0){
			people++;
		}
		return people;
	}
}
