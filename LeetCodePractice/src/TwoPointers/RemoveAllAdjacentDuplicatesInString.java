package TwoPointers;
/*Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

 

Example 1:

Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".*/
public class RemoveAllAdjacentDuplicatesInString {
	public String removeDuplicates(String S) {
        char[] arr = S.toCharArray();
        int last = 0;
        for(int i = 1; i < arr.length; i++){
            if(last >= 0 && arr[last] == arr[i]){
                last--;
            } else {
                last++;
                arr[last] = arr[i];
            }
        }
        return String.valueOf(arr, 0, last + 1);
    }
}
