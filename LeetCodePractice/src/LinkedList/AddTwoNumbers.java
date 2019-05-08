package LinkedList;

/*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
similar: Add Strings*/
public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode res = new ListNode(0);
		ListNode curr1 = l1, curr2 = l2, currRes = res;
		while (curr1 != null || curr2 != null) {
			if (curr1 != null) {
				carry += curr1.val;
				curr1 = curr1.next;
			}
			if (curr2 != null) {
				carry += curr2.val;
				curr2 = curr2.next;
			}
			currRes.next = new ListNode(carry % 10);
			currRes = currRes.next;
			carry /= 10;
		}
		if (carry == 1) {
			currRes.next = new ListNode(1);
		}
		return res.next;
	}
}
