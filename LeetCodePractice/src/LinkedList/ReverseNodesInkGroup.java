package LinkedList;

public class ReverseNodesInkGroup {
	class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) {
			 val = x;
			 next = null;
		 }
	}

	public ListNode reverse(ListNode pre, ListNode post){
        ListNode last = pre.next;
        ListNode cur = last.next;
        while(cur != post){
             last.next = cur.next;
             cur.next = pre.next;
             pre.next = cur;
             cur = last.next;
        }
        return last;
    }
	/**
     * @param head a ListNode
     * @param k an integer
     * @return a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1){
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode cur = head;
        int count = 0;
        while(cur != null){
            count++;
            ListNode post = cur.next;
            if(count == k){
                pre = reverse(pre, post);
                count = 0;
            }
            cur = post;
        }
        return fakeHead.next;
    }
}
