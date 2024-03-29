package heap;

import java.util.*;

/*In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

 

Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.

Note:

1 <= N <= 10^9
ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.*/
public class ExamRoom {
	PriorityQueue<Interval> pq;
    int N;

    class Interval {
        int s, e, dist;
        public Interval(int s, int e) {
            this.s = s;
            this.e = e;
            if(e < s){
                this.dist = -10000;
                return;
            }
            if (s == 0) {
                this.dist = e - s;
            } else if (e == N - 1) {
                this.dist = e - s;
            } else {
                this.dist = (e - s) / 2;
            }
        }
    }
    
	public ExamRoom(int N) {
		this.pq = new PriorityQueue<>((a, b) -> a.dist != b.dist? b.dist - a.dist : a.s - b.s);
        this.N = N;
        pq.add(new Interval(0, N - 1));
    }
    
	// O(logn): poll top candidate, split into two new intervals
    public int seat() {
    	int seat = 0;
        Interval interval = pq.poll();
        if (interval.s == 0) seat = 0;
        else if (interval.e == N - 1) seat = N - 1;
        else seat = (interval.s + interval.e) / 2;
        
        pq.offer(new Interval(interval.s, seat - 1));
        pq.offer(new Interval(seat + 1, interval.e));
            
        return seat;
    }
    
 // O(n)Find head and tail based on p. Delete and merge two ends
    public void leave(int p) {
    	Interval pre = null, post = null;
        List<Interval> intervals = new ArrayList<>(pq);
        for (Interval interval : intervals) {
            if (interval.e == p - 1) pre = interval;
            if (interval.s == p + 1) post = interval;
            if (pre != null && post != null) break;
        }
        // Delete
        pq.remove(pre);
        pq.remove(post);
        // Merge
        pq.offer(new Interval(pre.s, post.e));
    }
}
/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
