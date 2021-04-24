package string;
/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.



Example 1:

Input: num1 = "2", num2 = "1"
Output: "2"
Example 2:

Input: num1 = "2021", num2 = "12"
Output: ""102022""


Constraints:

1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only and base on 3.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class MultiplyStringsBaseThree {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] temp = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                temp[i + j + 1] += a * b;
            }
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = temp.length - 1; i >= 0; i--) {
            temp[i] += carry;
            sb.insert(0, temp[i] % 3);
            carry = temp[i] / 3;
        }
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        if (sb.length() == 0) {
            return "0";
        } else {
            return sb.toString();
        }
    }
}
