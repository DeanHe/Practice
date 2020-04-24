package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

If there is no way to make arr1 strictly increasing, return -1.

 

Example 1:

Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
Output: 1
Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
Example 2:

Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
Output: 2
Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
Example 3:

Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
Output: -1
Explanation: You can't make arr1 strictly increasing.
 

Constraints:

1 <= arr1.length, arr2.length <= 2000
0 <= arr1[i], arr2[i] <= 10^9*/
public class MakeArrayStrictlyIncreasing {
	public int makeArrayIncreasing(int[] arr1, int[] arr2) {
		int kInf = 1_000_000;
		// make arr2 sorted and unique
        Arrays.sort(arr2);
        List<Integer> temp2 = new ArrayList<Integer>();
        for(int i = 0; i < arr2.length; i++){
        	if(i + 1 < arr2.length && arr2[i] == arr2[i + 1]){
        		continue;
        	}
        	temp2.add(arr2[i]);
        }
        int[] arr2unique = new int[temp2.size()];
        for(int i = 0; i < temp2.size(); i++){
        	arr2unique[i] = temp2.get(i);
        }
        arr2 = arr2unique;
        //mem
        int[] keep = new int[arr1.length];
        int[][] swap = new int[arr1.length][arr2.length];
        Arrays.fill(keep, kInf);
        keep[0] = 0;
        for(int[] arr : swap){
        	Arrays.fill(arr, kInf);
        }
        Arrays.fill(swap[0], 1);
        for(int i = 1; i < arr1.length; i++){
        	int min_keep = kInf;
        	int min_swap = kInf;
        	for(int j = 0; j < arr2.length; j++){
        		//case 4: b[j] > b[k] always true
        		if(j > 0){
        			min_swap = Math.min(min_swap, swap[i - 1][j - 1] + 1);
        		}
        		//case 3: a[i] > b[j]
        		if(arr1[i] > arr2[j]){
        			min_keep = Math.min(min_keep, swap[i - 1][j]);
        		}
        		//case 1: a[i] > a[i - 1]
        		if(arr1[i] > arr1[i - 1]){
        			keep[i] = keep[i - 1];
        		}
        		//case 2: b[j] > a[i - 1]
        		if(arr2[j] > arr1[i - 1]){
        			swap[i][j] = keep[i - 1] + 1;
        		}
        		keep[i] = Math.min(keep[i], min_keep);
        		swap[i][j] = Math.min(swap[i][j], min_swap);
        	}
        }
        int resBySwap = kInf;
        for(int i = 0; i < arr2.length; i++){
        	resBySwap = Math.min(resBySwap, swap[arr1.length - 1][i]);
        }
        int resByKeep = keep[arr1.length - 1];
        int res = Math.min(resByKeep, resBySwap);
        return res >= kInf ? -1 : res;
    }
}
