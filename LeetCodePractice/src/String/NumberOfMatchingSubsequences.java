package String;

import java.util.*;


public class NumberOfMatchingSubsequences {
    public int numMatchingSubseq(String S, String[] words) {
    	int len = S.length();
    	ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    	for(int i= 0; i < 26; i++){
    		map.add(new ArrayList<Integer>());
    	}
        char[] arr = S.toCharArray();
        for(int i = 0; i < len; i++){
        	map.get(arr[i] - 'a').add(i);
        }
        int count = 0;
        for(String word : words){
        	if(isSub(word, map)){
        		count++;
        	}
        }
        return count;
    }
    private boolean isSub(String word, List<ArrayList<Integer>> map){
    	char[] arr = word.toCharArray();
    	int len = arr.length;
    	int pos = 0;
    	for(int i = 0; i < len; i++){
    		int index = Collections.binarySearch(map.get(arr[i] - 'a'), pos);
    		// for not found, get its insert position's upper element
    		if(index < 0){
    			index = -(index + 1);
    			if(index >= map.get(arr[i] - 'a').size()){
                    return false;
                }
    		}
    		pos = map.get(arr[i] - 'a').get(index) + 1;
    	}
    	return true;
    	
    }
}
