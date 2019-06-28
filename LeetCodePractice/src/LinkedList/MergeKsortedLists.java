package LinkedList;

import java.util.*;

public class MergeKsortedLists {

	/**
	 * @param lists:
	 *            a list of ListNode
	 * @return: The head of one sorted list.
	 */
	private Comparator<ListNode> comparator = new Comparator<ListNode>() {
		public int compare(ListNode a, ListNode b) {
			if (a == null) {
				return 1;
			} else {
				if (b == null) {
					return -1;
				} else {
					return a.val - b.val;
				}
			}
		}
	};

	public ListNode mergeKLists(List<ListNode> lists) {
		// write your code here
		if (lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();
		PriorityQueue<ListNode> queue = new PriorityQueue<>(size, comparator);
		for (ListNode n : lists) {
			if (n != null) {
				queue.offer(n);
			}
		}
		ListNode preHead = new ListNode(0);
		ListNode cur = preHead;
		while (!queue.isEmpty()) {
			ListNode temp = queue.poll();
			cur.next = temp;
			cur = cur.next;
			if (temp.next != null) {
				queue.offer(temp.next);
			}
		}
		return preHead.next;
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
