package LinkedList;

import java.util.*;
/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.



Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []


Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.

analysis:
TC O(N log K)
SC O(N)
 */
public class MergeKsortedLists {

	/**
	 * @param lists:
	 *            a list of ListNode
	 * @return: The head of one sorted list.
	 */

	public ListNode mergeKLists(ListNode[] lists) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
		for(ListNode n : lists){
			if(n != null){
				pq.offer(n);
			}
		}
		while(!pq.isEmpty()){
			ListNode temp = pq.poll();
			cur.next = temp;
			temp = temp.next;
			if(temp != null){
				pq.offer(temp);
			}
			cur = cur.next;
		}
		return dummy.next;
	}
//   method 2 by divide and conquer	
	MergeTwoSortedLists mergeTwo = new MergeTwoSortedLists();
	public ListNode mergeKListsDC(List<ListNode> lists) {
		// write your code here
		if (lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();
		return merge(lists, 0, size - 1);
	}
	
	private ListNode merge(List<ListNode> lists, int start, int end) {
		if(start > end){
			return null;
		}
		if(start == end){
			return lists.get(start);
		}
		int mid = start + (end - start) / 2;
		ListNode l1 = merge(lists, start, mid);
		ListNode l2 = merge(lists, mid + 1, end);
		return mergeTwo.mergeTwoLists(l1, l2);
	}
}
