package LinkedList;

import java.util.*;

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
