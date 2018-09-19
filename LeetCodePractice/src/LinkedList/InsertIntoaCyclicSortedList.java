// https://www.lintcode.com/problem/insert-into-a-cyclic-sorted-list/description
public class InsertIntoaCyclicSortedList {
	/*
     * @param node: a list node in the list
     * @param x: An integer
     * @return: the inserted new list node
     */
    public ListNode insert(ListNode node, int x) {
        // write your code here
        if(node == null){
            node = new ListNode(x);
            node.next = node;
            return node;
        }
        ListNode post = node;
        ListNode pre = null;
        do{
            pre = post;
            post = post.next;
            if(pre.val <= x && x <= post.val){
                break;
            }
            if((pre.val > post.val) && (pre.val < x || x < post.val)){
                break;
            }
        } while (post != node);
        ListNode res = new ListNode(x);
        res.next = post;
        pre.next = res;
        return res;
    }
}
