package string;
/*
Given a string, you can apply deletion two times,
and the deletion rule is that can only remove concatenated same characters
find the shortest string length after operations

ex:
1
input aabcccbb
delete ccc
delete bbb
remain aa
return 2

2
aabaa
delete b
delete aaaa
remain ""
return 0
 */
public class MinimumStringLengthAfterTwoDeletion {
    public int minimumLengthAfterDeletion(String str){
        int res = str.length();
        res = Math.min(res, deleteSeparate(str));
        res = Math.min(res, deleteConcatenate(str));
        return res;
    }

    private int deleteSeparate(String str){
        int first = 0, second = 0, len = str.length(), curLen = 0;
        Character cur = null;
        for(int i = 0; i < len; i++){
            char c = str.charAt(i);
            if(cur == null){
                cur = c;
                curLen = 1;
            } else {
                if(c == cur){
                    curLen++;
                } else {
                    // cur != c
                   if(curLen > first){
                       if(first != 0){
                           second = first;
                       }
                      first =curLen;
                   } else if(curLen > second){
                       second = curLen;
                   }
                    cur = c;
                    curLen = 1;
                }
            }
        }
        return len - (first + second);
    }

    private int deleteConcatenate(String str){
        int res = 0, len = str.length(), preLen = 0, curLen = 0;
        Character pre = null, cur = null;
        for(int i = 0; i < len; i++){
            char c = str.charAt(i);
            if(cur == null){
                cur = c;
                curLen = 1;
            } else {
                if(c == cur){
                    curLen++;
                } else {
                    // cur != c
                    if(pre != null && c == pre){
                        preLen++;
                        continue;
                    }
                    res = Math.max(res, preLen + curLen);
                    pre = cur;
                    preLen = curLen;
                    cur = c;
                    curLen = 1;
                }
            }
        }
        res = Math.max(res, preLen + curLen);
        return len - res;
    }
}
