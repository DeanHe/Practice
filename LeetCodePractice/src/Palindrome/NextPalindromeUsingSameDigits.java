package Palindrome;
/*
ou are given a numeric string num, representing a very large palindrome.

Return the smallest palindrome larger than num that can be created by rearranging its digits. If no such palindrome exists, return an empty string "".

A palindrome is a number that reads the same backward as forward.

Example 1:

Input: num = “1221”

Output: “2112”

Explanation: The next palindrome larger than “1221” is “2112”.

Example 2:

Input: num = “32123”

Output: “”

Explanation: No palindromes larger than “32123” can be made by rearranging the digits.

Example 3:

Input: num = “45544554”

Output: “54455445”

Explanation: The next palindrome larger than “45544554” is “54455445”.

Constraints:

1 <= num.length <= 10^5
num is a palindrome.
 */
public class NextPalindromeUsingSameDigits {
    public String nextPalindrome(String num) {
        char[] arr = num.toCharArray();
        int len = arr.length;
        boolean flag = nextPermutation(arr, len / 2);
        if(!flag){
            return "";
        }
        StringBuilder sb = new StringBuilder(new String(arr, 0, (len + 1) / 2));
        for(int i = len / 2 - 1; i >= 0; i--){
            sb.append(sb.charAt(i));
        }
        return sb.toString();
    }

    private boolean nextPermutation(char[] arr, int end) {
        int change = -1;
        for(int i = end - 2; i >= 0; i--){
            if(arr[i] < arr[i + 1]){
                change = i;
                break;
            }
        }
        if(change == -1){
            return false;
        }
        int next = -1;
        for(int i = end - 1; i > change; i--){
            if(arr[i] > arr[change]){
                next = i;
                break;
            }
        }
        char tmp = arr[change];
        arr[change] = arr[next];
        arr[next] = tmp;
        reverse(arr, change + 1, end - 1);
        return true;
    }

    private void reverse(char[] arr, int s, int e){
        while(s < e){
            char tmp = arr[s];
            arr[s] = arr[e];
            arr[e] = tmp;
            s++;
            e--;
        }
    }
}
