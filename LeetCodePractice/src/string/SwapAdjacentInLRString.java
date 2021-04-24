package string;
/*
In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.



Example 1:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: true
Explanation: We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Example 2:

Input: start = "X", end = "L"
Output: false
Example 3:

Input: start = "LLR", end = "RRL"
Output: false
Example 4:

Input: start = "XL", end = "LX"
Output: true
Example 5:

Input: start = "XLLR", end = "LXLX"
Output: false


Constraints:

1 <= start.length <= 10^4
start.length == end.length
Both start and end will only consist of characters in 'L', 'R', and 'X'.
 */
public class SwapAdjacentInLRString {
    public boolean canTransform(String start, String end){
        if(!start.replace("X", "").equals(end.replace("X", ""))){
            return false;
        }
        int slen = start.length();
        int elen = end.length();
        int i = 0, j = 0;
        while(i < slen && j < elen){
            while(i < slen && start.charAt(i) == 'X'){
                i++;
            }
            while(j < elen && end.charAt(j) == 'X'){
                j++;
            }
            if(i == slen && j == elen){
                return true;
            }
            if(i == slen || j == elen){
                return false;
            }
            char c1 = start.charAt(i);
            char c2 = end.charAt(j);
            if(c1 != c2){
                return false;
            }
            if(c1 == 'L' && i < j){
                return false;
            }
            if(c1 == 'R' && i > j){
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
}
