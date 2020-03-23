package TwoPointers;
/*
        Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

        Example 1:
        Input: S = "ab#c", T = "ad#c"
        Output: true
        Explanation: Both S and T become "ac".

        Example 2:
        Input: S = "ab##", T = "c#d#"
        Output: true
        Explanation: Both S and T become "".

        Example 3:
        Input: S = "a##c", T = "#a#c"
        Output: true
        Explanation: Both S and T become "c".

        Example 4:
        Input: S = "a#c", T = "b"
        Output: false
        Explanation: S becomes "c" while T becomes "b".
        Note:

        1 <= S.length <= 200
        1 <= T.length <= 200
        S and T only contain lowercase letters and '#' characters.
        Follow up:

        Can you solve it in O(N) time and O(1) space?
*/
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        return getStr(S).equals(getStr(T));
    }
    private String getStr(String s){
        int deletes = 0;
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = arr.length - 1; i >= 0; i--){
            if(arr[i] == '#'){
                deletes++;
            } else {
                if(deletes > 0){
                    deletes--;
                } else {
                    sb.append(arr[i]);
                }
            }
        }
        return sb.toString();
    }
}
