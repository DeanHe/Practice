package LinkedList;

public class IntersectionOfTwoLinkedLists {
	class ListNode {
		  int val;
		  ListNode next;
		  ListNode(int x) {
		      val = x;
		      next = null;
		  }
	}
	
	/**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode 
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Write your code here
        if(headA == null || headB == null){
            return null;
        }
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        ListNode longer, shorter;
        if(lenA > lenB){
            longer = headA;
            shorter = headB;
        } else {
            longer = headB;
            shorter = headA;
        }
        longer = getKthNode(longer, Math.abs(lenA - lenB));
        while(longer != shorter){
            longer = longer.next;
            shorter = shorter.next;
        }
        return longer;
        
    }
    private int getLength(ListNode n){
        int size = 0;
        while(n != null){
            n = n.next;
            size++;
        }
        return size;
    }
    private ListNode getKthNode(ListNode n, int size){
        ListNode cur = n;
        for(int i = 0; i < size; i++){
            cur = cur.next;
        }
        return cur;
    }
	
}
