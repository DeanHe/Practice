package window;

import java.util.Comparator;
import java.util.TreeSet;

/*Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.

Time Complexity
Priority Queue would have been O(k) for remove(element) giving us an overall time complexity of O(nk)

Using BST remove can be O(logK), which gives total O(nlogK) 
*/
public class SlidingWindowMedianBST {

    public double[] medianSlidingWindow(int[] nums, int k) {
        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (nums[a] != nums[b]) {
                    return Integer.compare(nums[a], nums[b]);
                }
                return a - b;
            }
        };
        TreeSet<Integer> upper = new TreeSet<>(cp);
        TreeSet<Integer> lower = new TreeSet<>(cp.reversed());
        int resLen = nums.length - k + 1;
        double[] res = new double[resLen];
        for (int i = 0; i < nums.length; i++) {
            add(i, upper, lower);
            if(i - k + 1 >= 0){
                res[i - k + 1] = getMedian(nums, upper, lower);
                if(!upper.remove(i - k + 1)){
                    lower.remove(i - k + 1);
                }
            }
        }
        return res;
    }

    private void add(int n, TreeSet<Integer> upper, TreeSet<Integer> lower) {
        lower.add(n);
        upper.add(lower.pollFirst());
        if (upper.size() > lower.size()) {
            lower.add(upper.pollFirst());
        }
    }

    private double getMedian(int[] nums, TreeSet<Integer> upper, TreeSet<Integer> lower) {
        if (upper.size() == lower.size()) {
            return ((double) nums[upper.first()] + nums[lower.first()]) / 2;
        }
        return nums[lower.first()];
    }
}
