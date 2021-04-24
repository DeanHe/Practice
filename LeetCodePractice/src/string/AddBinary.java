package string;
/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
*/
public class AddBinary {
	public String addBinary(String a, String b) {
        if(a == null || a.length() == 0){
        	return b;
        }
        if(b == null || b.length() == 0){
        	return a;
        }
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = aArr.length - 1;
        int j = bArr.length - 1;
        int carry = 0, sum = 0;
        while(i >= 0 || j >= 0 || carry > 0){
        	sum = carry;
        	if(i >= 0){
				sum += aArr[i--] - '0';
        	}
        	if(j >= 0){
				sum += bArr[j--] - '0';
        	}
        	sb.append(sum % 2);
        	carry = sum / 2;
        }
        return sb.reverse().toString();
    }
}
