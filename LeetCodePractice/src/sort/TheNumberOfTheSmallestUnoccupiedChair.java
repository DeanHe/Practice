package sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of chairs in this party that are numbered from 0 to infinity. When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.

For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives at that same moment, they can sit in that chair.

You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.

Return the chair number that the friend numbered targetFriend will sit on.



Example 1:

Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
Output: 1
Explanation:
- Friend 0 arrives at time 1 and sits on chair 0.
- Friend 1 arrives at time 2 and sits on chair 1.
- Friend 1 leaves at time 3 and chair 1 becomes empty.
- Friend 0 leaves at time 4 and chair 0 becomes empty.
- Friend 2 arrives at time 4 and sits on chair 0.
Since friend 1 sat on chair 1, we return 1.
Example 2:

Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
Output: 2
Explanation:
- Friend 1 arrives at time 1 and sits on chair 0.
- Friend 2 arrives at time 2 and sits on chair 1.
- Friend 0 arrives at time 3 and sits on chair 2.
- Friend 1 leaves at time 5 and chair 0 becomes empty.
- Friend 2 leaves at time 6 and chair 1 becomes empty.
- Friend 0 leaves at time 10 and chair 2 becomes empty.
Since friend 0 sat on chair 2, we return 2.


Constraints:

n == times.length
2 <= n <= 104
times[i].length == 2
1 <= arrivali < leavingi <= 105
0 <= targetFriend <= n - 1

hint:
Sort times by arrival time.
for each arrival_i find the smallest unoccupied chair and mark it as occupied until leaving_i.

analysis: two priority queue
sort student of [startTime: endTime: id]
pq available store all available seat numbers;
pq occupy store all occupied seats with info [student endTime ,seat number];
 */
public class TheNumberOfTheSmallestUnoccupiedChair {
    public int smallestChair(int[][] times, int targetFriend) {
        int len = times.length;
        int[][] students = new int[len][3];
        for(int i = 0; i < len; i++){
            students[i][0] = times[i][0];
            students[i][1] = times[i][1];
            students[i][2] = i;
        }
        Arrays.sort(students, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < len; ++ i) {
            available.offer(i);
        }
        PriorityQueue<int[]> occupy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int i = 0; i < len; i++){
            while (!occupy.isEmpty() && occupy.peek()[0] <= students[i][0]){
                available.offer(occupy.poll()[1]);
            }
            if(students[i][2] == targetFriend){
                return available.peek();
            }
            occupy.offer(new int[]{students[i][1], available.poll()});
        }
        return -1;
    }
}

