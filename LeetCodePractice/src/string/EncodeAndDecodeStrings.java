package string;

import java.util.*;

/*
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
Please implement encode and decode

Example
Example1

Input: ["lint","code","love","you"]
Output: ["lint","code","love","you"]
Explanation:
One possible encode method is: "lint:;code:;love:;you"
Example2

Input: ["we", "say", ":", "yes"]
Output: ["we", "say", ":", "yes"]
Explanation:
One possible encode method is: "we:;say:;:::;yes"
*/
public class EncodeAndDecodeStrings {
	/*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        // write your code here
    	StringBuilder sb = new StringBuilder();
    	for(String s : strs){
    		char[] arr = s.toCharArray();
    		for(char c : arr){
    			if(c == ':'){
    				sb.append("::");
    			} else {
    				sb.append(c);
    			}
    		}
    		sb.append(":;");
    	}
    	return sb.toString();
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        // write your code here
    	List<String> res = new ArrayList<>();
    	char[] arr = str.toCharArray();
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < arr.length;){
    		if(arr[i] == ':'){
    			if(arr[i + 1] == ';'){
    				res.add(sb.toString());
    				sb.setLength(0);
    			} else {
    				//arr[i + 1] == ':'
    				sb.append(':');
    			}
    			i += 2;
    		} else {
    			sb.append(arr[i]);
    			i += 1;
    		}
    	}
    	return res;
    }
}
