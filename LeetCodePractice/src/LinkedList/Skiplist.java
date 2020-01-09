package LinkedList;
/*Design a Skiplist without using any built-in libraries.

        A Skiplist is a data structure that takes O(log(n)) time to add, erase and search. Comparing with treap and red-black tree which has the same function and performance, the code length of Skiplist can be comparatively short and the idea behind Skiplists are just simple linked lists.

        For example: we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it. The Skiplist works this way:


        Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons

        You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top layers, add , erase and search can be faster than O(n). It can be proven that the average time complexity for each operation is O(log(n)) and space complexity is O(n).

        To be specific, your design should include these functions:

        bool search(int target) : Return whether the target exists in the Skiplist or not.
        void add(int num): Insert a value into the SkipList.
        bool erase(int num): Remove a value in the Skiplist. If num does not exist in the Skiplist, do nothing and return false. If there exists multiple num values, removing any one of them is fine.
        See more about Skiplist : https://en.wikipedia.org/wiki/Skip_list

        Note that duplicates may exist in the Skiplist, your code needs to handle this situation.



        Example:

        Skiplist skiplist = new Skiplist();

        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.search(0);   // return false.
        skiplist.add(4);
        skiplist.search(1);   // return true.
        skiplist.erase(0);    // return false, 0 is not in skiplist.
        skiplist.erase(1);    // return true.
        skiplist.search(1);   // return false, 1 has already been erased.


        Constraints:

        0 <= num, target <= 20000
        At most 50000 calls will be made to search, add, and erase.*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
public class Skiplist {
    // Sentinel
    //          \
    // level 3: -Inf ----------------------------> 4
    // level 2: -Inf ------------> 2 ------------> 4
    // level 1: -Inf ----> 1 ----> 2 ------------> 4
    // level 0: -Inf ----> 1 ----> 2 ----> 3 ----> 4 : this level is the most concrete level
    private static final double DEFAULT_PROB = 0.5;
    private final Random rand;
    private final List<SkipListNode> sentinels;
    public Skiplist() {
        rand = new Random();
        sentinels = new ArrayList<>();
        sentinels.add(new SkipListNode(Integer.MIN_VALUE));
    }

    public boolean search(int target) {
        SkipListNode cur = getSmallerOrEquals(target);
        return cur.val == target;
    }

    public void add(int num) {
        SkipListNode pre = getSmallerOrEquals(num);
        SkipListNode cur = new SkipListNode(num);
        append(pre, cur);
        populateLevelUp(cur);
    }

    public boolean erase(int num) {
        SkipListNode cur = getSmallerOrEquals(num);
        if(cur.val != num){
            return false;
        }
        while(cur != null){
            SkipListNode pre = cur.left, post = cur.right;
            pre.right = post;
            if(post != null){
                post.left = pre;
            }
            cur = cur.up;
        }
        return true;
    }

    private boolean flipCoin(){
        return rand.nextDouble() < DEFAULT_PROB;
    }

    private SkipListNode getSentinel(){
        return sentinels.get(sentinels.size() - 1);
    }

    private SkipListNode getSmallerOrEquals(int target){
        SkipListNode cur = getSentinel();
        while(cur != null){
            if(cur.right == null || cur.right.val > target){
                if(cur.down == null){
                    break;
                }
                cur = cur.down;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }

    private void append(SkipListNode pre, SkipListNode cur){
        SkipListNode next = pre.right;
        pre.right = cur;
        cur.left = pre;
        if(next != null){
            cur.right = next;
            next.left = cur;
        }
    }

    private void populateLevelUp(SkipListNode cur){
        SkipListNode pre = cur.left;
        while(flipCoin()){
            while(pre.left != null && pre.up == null){
                pre = pre.left;
            }
            if(pre.up == null){
                SkipListNode newSentinel = new SkipListNode(Integer.MIN_VALUE);
                pre.up = newSentinel;
                newSentinel.down = pre;
                sentinels.add(newSentinel);
            }
            pre = pre.up;
            SkipListNode toInsert = new SkipListNode(cur.val);
            cur.up = toInsert;
            toInsert.down = cur;
            pre.right = toInsert;
            toInsert.left = pre;
            cur = toInsert;
        }
    }
}
