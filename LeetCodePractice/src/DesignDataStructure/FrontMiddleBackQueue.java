package DesignDataStructure;
/*
Design a queue that supports push and pop operations in the front, middle, and back.

Implement the FrontMiddleBack class:

FrontMiddleBack() Initializes the queue.
void pushFront(int val) Adds val to the front of the queue.
void pushMiddle(int val) Adds val to the middle of the queue.
void pushBack(int val) Adds val to the back of the queue.
int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:

Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].


Example 1:

Input:
["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
[[], [1], [2], [3], [4], [], [], [], [], []]
Output:
[null, null, null, null, null, 1, 3, 4, 2, -1]

Explanation:
FrontMiddleBackQueue q = new FrontMiddleBackQueue();
q.pushFront(1);   // [1]
q.pushBack(2);    // [1, 2]
q.pushMiddle(3);  // [1, 3, 2]
q.pushMiddle(4);  // [1, 4, 3, 2]
q.popFront();     // return 1 -> [4, 3, 2]
q.popMiddle();    // return 3 -> [4, 2]
q.popMiddle();    // return 4 -> [2]
q.popBack();      // return 2 -> []
q.popFront();     // return -1 -> [] (The queue is empty)


Constraints:

1 <= val <= 10^9
At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.
 */
public class FrontMiddleBackQueue {
    Node head, tail, mid;
    int size;

    public FrontMiddleBackQueue() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    public void pushFront(int val) {
        Node cur = new Node(val);
        size++;
        cur.next = head.next;
        cur.pre = head;
        head.next.pre = cur;
        head.next = cur;
        if (size == 1) {
            mid = cur;
        } else {
            if (size % 2 == 0) {
                mid = mid.pre;
            }
        }
    }

    public void pushMiddle(int val) {
        Node cur = new Node(val);
        if (size == 0) {
            cur.pre = head;
            cur.next = tail;
            head.next = cur;
            tail.pre = cur;
            mid = cur;
        } else if (size % 2 == 0) {
            // put behind mid
            cur.next = mid.next;
            cur.pre = mid;
            mid.next.pre = cur;
            mid.next = cur;
            mid = cur;
        } else {
            // put before mid
            cur.next = mid;
            cur.pre = mid.pre;
            mid.pre.next = cur;
            mid.pre = cur;
            mid = cur;
        }
        size++;
    }

    public void pushBack(int val) {
        Node cur = new Node(val);
        cur.next = tail;
        cur.pre = tail.pre;
        tail.pre.next = cur;
        tail.pre = cur;
        size++;
        if(size == 1){
            mid = cur;
        } else {
            if(size % 2 == 1){
                mid = mid.next;
            }
        }
    }

    public int popFront() {
        if(size > 0){
            Node cur = head.next;
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            size--;
            if(size % 2 == 1){
                mid = mid.next;
            }
            return cur.val;
        }
        return -1;
    }

    public int popMiddle() {
        if(size > 0){
            Node cur = mid;
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            size--;
            if(size % 2 == 0){
                mid = mid.pre;
            } else {
                mid = mid.next;
            }
            return cur.val;
        }
        return -1;
    }

    public int popBack() {
        if(size > 0){
            Node cur = tail.pre;
            cur.pre.next = tail;
            tail.pre = cur.pre;
            size--;
            if(size % 2 == 0){
                mid = mid.pre;
            }
            return cur.val;
        }
        return -1;
    }

    class Node {
        int val;
        Node pre, next;
        public Node(int val){
            this.val = val;
        }
    }
}
/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */