package designDataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/*
You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.

The MKAverage can be calculated using these steps:
If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
Remove the largest k percents elements from the container.
Calculate the average value for the rest of the elements rounded down to the nearest integer.
Implement the MKAverage class:

MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
void addElement(int num) Inserts a new element num into the stream.
int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.


Example 1:

Input
["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
Output
[null, null, null, -1, null, 3, null, null, null, 5]

Explanation
MKAverage obj = new MKAverage(3, 1);
obj.addElement(3);        // current elements are [3]
obj.addElement(1);        // current elements are [3,1]
obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
obj.addElement(10);       // current elements are [3,1,10]
obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
                          // After removing smallest and largest 1 element the container will be [3].
                          // The average of [3] equals 3/1 = 3, return 3
obj.addElement(5);        // current elements are [3,1,10,5]
obj.addElement(5);        // current elements are [3,1,10,5,5]
obj.addElement(5);        // current elements are [3,1,10,5,5,5]
obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
                          // After removing smallest and largest 1 element the container will be [5].
                          // The average of [5] equals 5/1 = 5, return 5


Constraints:

3 <= m <= 10^5
1 <= k*2 < m
1 <= num <= 10^5
At most 10^5 calls will be made to addElement and calculateMKAverage.

hint:
1 At each query, try to save and update the sum of the elements needed to calculate MKAverage.
2 You can use BSTs for fast insertion and deletion of the elements.

analysis:TreeMap
TC O(logN)
 */
public class MKAverageGoogle {
    TreeMap<Integer, Integer> top = new TreeMap<>();
    TreeMap<Integer, Integer> mid = new TreeMap<>();
    Queue<Integer> q = new LinkedList<>();
    int m, topCnt, topCntLimit;
    double k;
    long midSum;
    public MKAverageGoogle(int m, double k) {
        this.m = m;
        this.k = k;
        midSum = 0;
    }

    public void addElement(int num) {
        // remove out of range item from map first
        if(q.size() == m){
            int head = q.poll();
            if(top.containsKey(head)){
                remove(top, head);
                topCnt--;
            } else if(mid.containsKey(head)){
                remove(mid, head);
                midSum -= head;
            }
        }
        q.offer(num);
        midSum += num;
        // insert to middle first
        update(mid, num);
        topCntLimit = (int)(q.size() * k);
        // move item from middle to top, to fill k slots
        while(topCnt < topCntLimit && !mid.isEmpty()){
            midSum -= mid.lastKey();
            update(top, remove(mid, mid.lastKey()));
            topCnt++;
        }
        // balance middle and top
        while(!mid.isEmpty() && !top.isEmpty() && top.firstKey() < mid.lastKey()){
            midSum += top.firstKey();
            update(mid, remove(top, top.firstKey()));
            midSum -= mid.lastKey();
            update(top, remove(mid, mid.lastKey()));
        }
    }

    public int calculateMKAverage() {
        if(q.size() == m){
            return (int)(midSum / (m - topCntLimit));
        }
        return -1;
    }

    private void update(TreeMap<Integer, Integer> map, int target) {
        map.put(target, map.getOrDefault(target, 0) + 1);
    }

    private int remove(TreeMap<Integer, Integer> map, int target){
        map.put(target, map.get(target) - 1);
        if(map.get(target) == 0){
            map.remove(target);
        }
        return target;
    }
}
/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */

