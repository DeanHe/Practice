package LinkedList;

import java.util.*;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; next = null; } }
 */
// Divid and Conquer; Heap
public class MergekSortedLists {
	public ListNode mergeKLists(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}

		// PriorityQueue is a sorted queue
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val > o2.val) {
					return 1;
				} else if (o1.val < o2.val) {
					return -1;
				} else {
					return 0;
				}

			}
		});

		// add first node of each list to the queue
		for (ListNode node : lists) {
			if (node != null) {
				queue.add(node);
			}
		}

		ListNode fakeHead = new ListNode(0);
		ListNode curr = fakeHead; // serve as a pointer/cursor
		while (!queue.isEmpty()) {
			ListNode temp = queue.poll();
			curr.next = temp;
			// keep adding next element of each list
			if (temp.next != null) {
				queue.add(temp.next);
			}
			curr = curr.next;
		}

		return fakeHead.next;
	}
}
