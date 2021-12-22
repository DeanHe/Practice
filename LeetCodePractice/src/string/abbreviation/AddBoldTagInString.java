package string.abbreviation;
/*
Given a string s and a list of strings dict, you need to add a closed pair of bold tag and to wrap the substrings in s that exist in dict. If two such substrings overlap,
you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example
Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
Notice
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].

analysis:
first loop mark which char needs to be bold
second loop generate the string
 */
public class AddBoldTagInString {
    /**
     * @param s: a string
     * @param dict: a list of strings
     * @return: return a string
     */
    public String addBoldTag(String s, String[] dict) {
        // write your code here
        int len = s.length();
        boolean[] bold = new boolean[len];
        for(int i = 0, end = 0; i < len; i++){
            for(String tag : dict){
                if(s.startsWith(tag, i)){
                    end = Math.max(end, i + tag.length());
                }
            }
            bold[i] = end > i;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            if(bold[i]){
                int end = i;
                while(end < len && bold[end]){
                    end++;
                }
                sb.append("<b>" + s.substring(i, end) + "</b>");
                i = end - 1;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
