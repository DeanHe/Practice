package designDataStructure;

import java.util.*;

/*
Design a data structure to find the frequency of a given value in a given subarray.

The frequency of a value in a subarray is the number of occurrences of that value in the subarray.

Implement the RangeFreqQuery class:

RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).



Example 1:

Input
["RangeFreqQuery", "query", "query"]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
Output
[null, 1, 2]

Explanation
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i], value <= 10^4
0 <= left <= right < arr.length
At most 10^5 calls will be made to query

analysis:
binary search
 */
public class RangeFreqQuery {
    int len;
    Map<Integer, List<Integer>> map;
    public RangeFreqQuery(int[] arr) {
        len = arr.length;
        map = new HashMap<>();
        for(int i = 0; i < len; i++){
            int n = arr[i];
            map.computeIfAbsent(n, x -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        if(!map.containsKey(value)){
            return 0;
        }
        List<Integer> ls = map.get(value);
        int rightIdx = search(ls, right);
        if(rightIdx == -1){
            return 0;
        }
        int leftIdx = search(ls, left - 1);
        if(leftIdx == -1){
            return rightIdx + 1;
        }
        return rightIdx - leftIdx;
    }

    public int search(List<Integer> ls, int val){
        int len = ls.size();
        int s = 0, e = len - 1;
        while(s + 1 < e){
            int mid = s + (e - s) / 2;
            if(val == ls.get(mid)){
                return mid;
            } else if(ls.get(mid) < val){
                s = mid;
            } else {
                e = mid;
            }
        }
        if(val >= ls.get(e)){
            return e;
        }
        if(val >= ls.get(s)){
            return s;
        }
        return -1;
    }
}
/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
