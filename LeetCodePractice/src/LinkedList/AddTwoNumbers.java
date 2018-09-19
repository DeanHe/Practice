package LinkedList;

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
       ListNode res = new ListNode(0);
       ListNode curr1 = l1, curr2 = l2, currRes = res;
       while(curr1 != null || curr2 != null){
           if(curr1 != null){
               carry += curr1.val;
               curr1 = curr1.next;
           }
           if(curr2 != null){
               carry += curr2.val;
               curr2 = curr2.next;
           }
           currRes.next = new ListNode(carry%10);
           currRes = currRes.next;
           carry /= 10;
       }
       if(carry == 1){
           currRes.next = new ListNode(1);
       }
       return res.next;
	}
}

class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) {
		 val = x;
		 next = null;
	 }
}
