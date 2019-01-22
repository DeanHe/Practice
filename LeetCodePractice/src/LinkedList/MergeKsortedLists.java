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
}
