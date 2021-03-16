package Heap;

import java.util.PriorityQueue;

/*

There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.



Example 1:

Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
Example 2:

Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485


Constraints:

1 <= classes.length <= 10^5
classes[i].length == 2
1 <= passi <= totali <= 10^5
1 <= extraStudents <= 10^5

analysis:
sort by improvement
TC O(NlgN)
 */
public class MaximumAveragePassRatio {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int len = classes.length;
        double res = 0;
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        for (int[] cls : classes) {
            double[] tmp = new double[]{improvement(cls[0], cls[1]), cls[0], cls[1]};
            maxHeap.offer(tmp);
        }
        while (extraStudents > 0) {
            double[] tmp = maxHeap.poll();
            double numerator = tmp[1];
            double denominator = tmp[2];
            tmp[0] = improvement(numerator + 1, denominator + 1);
            tmp[1] = numerator + 1;
            tmp[2] = denominator + 1;
            maxHeap.offer(tmp);
            extraStudents--;
        }
        while (!maxHeap.isEmpty()) {
            double[] tmp = maxHeap.poll();
            double numerator = tmp[1];
            double denominator = tmp[2];
            res += numerator / denominator;
        }
        return res / len;
    }

    private double improvement(double numerator, double denominator) {
        return (numerator + 1) / (denominator + 1) - numerator / denominator;
    }
}
