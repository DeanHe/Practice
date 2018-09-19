package LinkedList;

public class ReverseNodesInkGroup {
	//区间的reverse。因为题目要求的是k group逆转嘛。注意reverse返回的是最后一个(last)节点，这样下一个k-group就可以用上了
    public static ListNode reverse(ListNode pre, ListNode post){
        ListNode last = pre.next;
        ListNode curr = last.next;
        while(curr != post){
             last.next = curr.next;
             curr.next = pre.next;
             pre.next = curr;
             curr = last.next;
        }
        return last;
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1){
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode curr = head;
        int count = 0;
        while(curr != null){
            count++;
            ListNode post = curr.next;
            if(count == k){
                pre = reverse(pre, post);
                count = 0;
            }
            curr = post;
        }
        return fakeHead.next;
    }
}
