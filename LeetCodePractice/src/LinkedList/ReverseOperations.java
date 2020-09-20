package LinkedList;

/*
You are given a singly-linked list that contains N integers. A subpart of the list is a contiguous set of even elements, bordered either by the end of the list or an odd element. For example, if the list is [1, 2, 8, 9, 12, 16], the subparts of the list are [2, 8] and [12, 16].
Then, for each subpart, the order of the elements is reversed. In the example, this would result in the new list, [1, 8, 2, 9, 16, 12].
The goal of this question is: given a resulting list, determine the original order of the elements.
Implementation detail:
You must use the following definition for elements in the linked list:
class Node {
    int data;
    Node next;
}
Signature
Node reverse(Node head)
Constraints
1 <= N <= 1000, where N is the size of the list
1 <= Li <= 10^9, where Li is the ith element of the list
Example
Input:
N = 6
list = [1, 2, 8, 9, 12, 16]
Output:
[1, 8, 2, 9, 16, 12]


list = {2, 18, 24, 3, 5, 7, 9, 6, 12}
Output:
{24, 18, 2, 3, 5, 7, 9, 12, 6}

analysis:
pre -> (a ,b, c) -> post
pre -> (c, b, a) -> post; and a is last
 */
public class ReverseOperations {
    ListNode dfs(ListNode pre, ListNode post) {
        ListNode last = pre.next;
        ListNode cur = last.next;
        while (cur != post) {
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
    }

    ListNode reverse(ListNode head) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead;
        while (head != null) {
            if (head.val % 2 == 1) {
                pre = head;
                head = head.next;
            } else {
                ListNode post = head;
                while (post != null && post.val % 2 == 0) {
                    post = post.next;
                }
                pre = dfs(pre, post);
                head = post;
            }
        }
        return preHead.next;
    }
}
