package sort.twoDimensions;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
You will start on the 1st day and you cannot take two or more courses simultaneously.
Return the maximum number of courses that you can take.


Example 1:
Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
Output: 3
Explanation:
There are totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
Example 2:

Input: courses = [[1,2]]
Output: 1
Example 3:

Input: courses = [[3,2],[4,3]]
Output: 0


Constraints:

1 <= courses.length <= 10^4
1 <= durationi, lastDayi <= 10^4

hint:
During iteration, say I want to add the current course, currentTotalTime being total time of all courses taken till now, but adding the current course might exceed my deadline or it doesn’t.
1. If it doesn’t, then I have added one new course. Increment the currentTotalTime with duration of current course.

2. If it exceeds deadline, I can swap current course with current courses that has biggest duration.
* No harm done and I might have just reduced the currentTotalTime, right?
* What preprocessing do I need to do on my course processing order so that this swap is always legal?
*
analysis:
As is often the case in a scheduling-type problem, this leads to a particular issue: we'll want to sort the data in two distinctly different ways.
Since we'll be progressing through C as if we're progressing through time, we'll want to sort C based on the courses' cutoffs (end),
but when we backtrack to potentially remove a course, we'll want to sort the courses we've accepted by their duration (dur).

Time Complexity: O(N * log N) where N is the length of courses, due to both the sort and the priority queue / heap implementation
Space Complexity: O(N) due to the priority queue / heap data

tag: sort + heap
 */
public class CourseScheduleIII {
    public int scheduleCourse(int[][] courses) {
        int cur = 0, len = courses.length;
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < len; i++){
            int duration  = courses[i][0];
            int deadline = courses[i][1];
            if(cur + duration <= deadline){
                cur += duration;
                pq.offer(duration);
            } else {
                if(!pq.isEmpty() && pq.peek() > duration){
                    cur -= pq.poll();
                    cur += duration;
                    pq.offer(duration);
                }
            }
        }
        return pq.size();
    }
}

