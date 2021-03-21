package binarySearch;

import java.util.*;

/*Give two Lists, give a maximum value, find a bunch in each of the two lists, and find all the pairs that are closest to the maximum but not larger than the maximum.

Example
Give a=[2,3,4,5,6], b=[4,5,7], x=8', return [[3,5],[4,4]].

Explanation:
the sum of [3,5] or [4,4] is 8, which is no larger than 8. 
Give a=[2,3,4,5,6], b=[4,5,7], x=10', return [[3,7],[5,5],[6,4]].

Explanation:
the sum of [3,7],[5,5],[6,4] is 10, which is no larger than 10.*/
public class MaxPair {
	/**
     * @param a: the first list
     * @param b: the second list
     * @param x: the max sum
     * @return: the pairs whose sum are not exceed x
     */
    public int[][] getAns(int[] a, int[] b, int x) {
    	ArrayList<int[]> list = new ArrayList<>();
    	Arrays.sort(a);
    	Arrays.sort(b);
    	int aLen = a.length;
    	int bLen = b.length;
    	int sum = 0;
    	int bLastPos = bLen - 1;
    	for(int i = 0; i < aLen; i++){
    		for(int j = bLastPos; j >= 0; j--){
    			if(a[i] + b[j] <= x){
    				if(a[i] + b[j] > sum){
    					sum = a[i] + b[j];
    					list.clear();
    					list.add(new int[] {a[i], b[j]});
    				} else if(a[i] + b[j] == sum){
    					list.add(new int[] {a[i], b[j]});
    				}
    				bLastPos = j;
    				break;
    			}
    		}
    	}
    	int[][] res = new int[list.size()][2];
    	for(int i = 0; i < list.size(); i++){
    		res[i] = list.get(i);
    	}
    	return res;
    }
}
