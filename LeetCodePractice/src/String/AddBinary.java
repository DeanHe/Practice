package String;
/*Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"*/
public class AddBinary {
	public String addBinary(String a, String b) {
        if(a == null || a.length() == 0){
        	return b;
        }
        if(b == null || b.length() == 0){
        	return a;
        }
        StringBuilder sb = new StringBuilder();
        int lastA = a.length() - 1;
        int lastB = b.length() - 1;
        int carry = 0;
        while(lastA >= 0 || lastB >= 0 || carry > 0){
        	int temp1 = 0;
        	int temp2 = 0;
        	int current = 0;
        	if(lastA >= 0){
        		temp1 = a.charAt(lastA--) - '0';
        	}
        	if(lastB >= 0){
        		temp2 = a.charAt(lastB--) - '0';
        	}
        	current = (temp1 + temp2 + carry) % 2;
        	carry = (temp1 + temp2 + carry) / 2;
        	sb.insert(0, current);
        }
        return sb.toString();
    }
}
