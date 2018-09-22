package TwoPointers;

import java.util.*;

//https://www.geeksforgeeks.org/count-number-of-substrings-with-exactly-k-distinct-characters/
public class SubstringsWithExactlyKdistinctCharacters {
	// Function to count number of substrings
    // with exactly k unique characters
    int countkDist(String str, int k){
    	// Initialize result
    	int res = 0;
    	int len = str.length();
    	// To store count of characters from 'a' to 'z'
    	int[] cnt = new int[26];
    	// Consider all substrings beginning with
        // str[i]
    	for(int i = 0; i < len; i++){
    		int dist_count = 0;
    		// Initializing count array with 0
    		Arrays.fill(cnt, 0);
    		// Consider all substrings between str[i..j]
    		for(int j = i; j < len; j++){
    			// If this is a new character for this
                // substring, increment dist_count.
    			if(cnt[str.charAt(i) - 'a'] == 0){
    				dist_count++;
    			}
    			// Increment count of current character
    			cnt[str.charAt(i) - 'a']++;
    			// If distinct character count becomes k,
                // then increment result.
    			if(dist_count == k){
    				res++;
    			}
    		}
    	}
    	return res;
    }
}
