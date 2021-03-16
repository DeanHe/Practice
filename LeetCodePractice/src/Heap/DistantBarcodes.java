package Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.

 

Example 1:

Input: [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,2,1,2,1]
 

Note:

1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000

We want to always choose the most common or second most common element to write next. What data structure allows us to query this effectively?
*/
public class DistantBarcodes {
	public int[] rearrangeBarcodes(int[] barcodes) {
		int len = barcodes.length;
		int[] res = new int[len];
		Map<Integer, Integer> freq = new HashMap<>();
		for (int n : barcodes) {
			freq.put(n, freq.getOrDefault(n, 0) + 1);
		}
		List<Map.Entry<Integer, Integer>> ls = new ArrayList<>(freq.entrySet());
		Collections.sort(ls, (a, b) -> b.getValue() - a.getValue() == 0 ? a.getKey() - b.getKey() : b.getValue() - a.getValue());
		int i = 0;
		for(Map.Entry<Integer, Integer> entry : ls){
			int cnt = entry.getValue();
			while(cnt > 0){
				res[i] = entry.getKey();
				i += 2;
				if(i >= len){
					i = 1;
				}
				cnt--;
			}
		}
		return res;
	}
}
