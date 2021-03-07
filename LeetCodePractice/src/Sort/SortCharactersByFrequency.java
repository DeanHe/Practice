package Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/*Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.*/
public class SortCharactersByFrequency {
	public String frequencySort(String s) {
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        for(char c : arr){
        	map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        while(!pq.isEmpty()){
        	Entry<Character, Integer> cur = pq.poll();
        	int freq = cur.getValue();
        	char c = cur.getKey();
        	for(int i = 0; i < freq; i++){
        		sb.append(c);
        	}
        }
        return sb.toString();
    }
	
	public String frequencyBucketSort(String s) {
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> freqMap = new HashMap<>();
        char[] arr = s.toCharArray();
        for(char c : arr){
        	freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        List<Character>[] bucket = new List[s.length() + 1];
        for(Entry<Character, Integer> entry : freqMap.entrySet()){
        	int freq = entry.getValue();
        	char c = entry.getKey();
        	if(bucket[freq] == null){
        		bucket[freq] = new ArrayList<>();
        	}
        	bucket[freq].add(c);
        }
        for(int freq = s.length(); freq > 0; freq--){
        	if(bucket[freq] != null){
        		List<Character> ls = bucket[freq];
        		for(char c : ls){
        			for(int i = 0; i < freq; i++){
            			sb.append(c);
            		}
        		}
        	}
        }
        return sb.toString();
    }
}
