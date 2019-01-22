package LinkedList;

public class ReverseAlinkedList {
	
	public ListNode reverse(ListNode head){
    	ListNode preHead = new ListNode(0);
    	ListNode next = null;
    	while(head != null){
    		next = head.next;
    		head.next = preHead.next;
    		preHead.next = head;
    		head = next;
    	}
    	return preHead.next;
    }
	
	 void printList(ListNode node) { 
        while (node != null) { 
            System.out.print(node.val + " "); 
            node = node.next; 
        } 
    } 
  
    public void example() {
    	ReverseAlinkedList list = new ReverseAlinkedList();
    	ListNode head = new ListNode(3);
    	head.next = new ListNode(15); 
    	head.next.next = new ListNode(4); 
    	head.next.next.next = new ListNode(20); 
          
        System.out.println("Given Linked list"); 
        list.printList(head); 
        ListNode reverseHead = list.reverse(head); 
        System.out.println(""); 
        System.out.println("Reversed linked list "); 
        list.printList(reverseHead); 
    } 
}
