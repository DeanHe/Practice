package LinkedList;
/*iven a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example
Example1

Input: 1 -> 2 -> 3 -> null
Output: 1 -> 2 -> 4 -> null
Explanation:
123 + 1 = 124
Example2

Input: 9 -> 9 -> null
Output: 1 -> 0 -> 0 -> null
Explanation:
99 + 1 = 100*/
public class PlusOneLinkedList {
	/**
     * @param head: the first Node
     * @return: the answer after plus one
     */
    public ListNode plusOne(ListNode head) {
        // Write your code here
    	ListNode dummyHead = new ListNode(0);
    	dummyHead.next = head;
    }
}
