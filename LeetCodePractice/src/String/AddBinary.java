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
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = ac.length - 1;
        int j = bc.length - 1;
        int carry = 0;
        while(i >= 0 || j >= 0 || carry > 0){
        	int cur_a = 0;
        	int cur_b = 0;
        	int cur = 0;
        	if(i >= 0){
				cur_a = ac[i--] - '0';
        	}
        	if(j >= 0){
				cur_b = bc[j--] - '0';
        	}
        	cur = (cur_a + cur_b + carry) % 2;
        	carry = (cur_a + cur_b + carry) / 2;
        	sb.insert(0, cur);
        }
        return sb.toString();
    }
}
