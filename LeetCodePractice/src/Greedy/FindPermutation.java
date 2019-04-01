package Greedy;
/*By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.

On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.

Example
Example 1:
	Input:  str = "DI"
	Output:  [2,1,3]

Example 2:
	Input: str = "I"
	Output:  [1,2]
	
Notice
The input string will only contain the character 'D' and 'I'.
The length of input string is a positive integer and will not exceed 10,000.*/
//when reach an 'I' in position i, update res[Didx:i] to {i+1, i, i - 1...}
public class FindPermutation {
	/**
     * @param s: a string
     * @return: return a list of integers
     */
    public int[] findPermutation(String s) {
        // write your code here
    	int[] res = new int[s.length() + 1];
    	int Didx = 0;
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) == 'I'){
    			res[Didx] = i + 1;
    			Didx++;
    		    while(Didx <= i){
    		    	res[Didx] = res[Didx - 1] - 1;
    		    	Didx++;
    		    }
    		}
    	}
    	res[Didx] = s.length() + 1;
    	Didx++;
    	while(Didx <= s.length()){
	    	res[Didx] = res[Didx - 1] - 1;
	    	Didx++;
	    }
    	return res;
    }
}
