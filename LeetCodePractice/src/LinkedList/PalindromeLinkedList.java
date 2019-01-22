package LinkedList;
/*Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true*/

public class PalindromeLinkedList {
	 /**
	 * @param head a ListNode
	 * @return a boolean
	 */
	 public boolean isPalindrome(ListNode head) {
		 if(head == null){
			 return true;
		 }
		 ListNode middle = findMiddle(head);
		 ListNode reversed = reverse(middle);
		 while(head != null && reversed != null){
			 if(head.val != reversed.val){
				 return false;
			 }
			 head = head.next;
			 reversed = reversed.next;
		 }
		 return true;
	 }
	 
	 private ListNode findMiddle(ListNode head){
		 ListNode fast = head.next;
		 ListNode slow = head;
		 while(fast != null && fast.next != null){
			 fast = fast.next.next;
			 slow = slow.next;
		 }
		 return slow;
	 }
	 
	 private ListNode reverse(ListNode head){
		 ListNode preHead = new ListNode(0);
		 ListNode post = null;
		 while(head != null){
			 post = head.next;
			 head.next = preHead.next;
			 preHead.next = head;
			 head = post;
		 }
		 return preHead.next;
	 }
}
