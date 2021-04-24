package math;
/*In the following, every capital letter represents some hexadecimal digit from 0 to f.

The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand. For example, "#15c" is shorthand for the color "#1155cc".

Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.

Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand (that is, it can be represented as some "#XYZ")

Example
Example 1:

Input: color = "#09f166"
Output: "#11ee66"
Explanation:  
The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
This is the highest among any shorthand color.
Example 2:

Input: color = "#010000"
Output: "#000000"
Explanation:  
The similarity is -(0x01 - 0x10)^2 -(0x00 - 0x00)^2 - (0x00 - 0x00)^2 = -1 -0 -0 = -1.
This is the highest among any shorthand color.
Notice
color is a string of length 7.
color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
Any answer which has the same (highest) similarity as the best answer will be accepted.
All inputs and outputs should use lowercase letters, and the output is 7 characters.*/
public class SimilarRGBcolor {
	/**
     * @param color: the given color
     * @return: a 7 character color that is most similar to the given color
     */
    public String similarRGB(String color) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for(int i = 1; i < color.length(); i += 2){
        	sb.append(getHexDigits(color.charAt(i), color.charAt(i + 1)));
        }
        return sb.toString();
    }
    private String getHexDigits(char c1, char c2) {
		int d1 = hexToDecimal(c1);
		int d2 = hexToDecimal(c2);
		int sum = d1 * 16 + d2;
		int num = sum / 17; // [ 0x00(0) , 0x11(17), 0x22(34),  0x33(51), ........., 0xff(255) ]
		int remainder = sum % 17;
		if(remainder > 17 / 2){
			num++;
		}
		String hex = decimalToHex(num);
		return hex + hex;
	}
	private int hexToDecimal(char c){
    	if(Character.isDigit(c)){
    		return c - '0';
    	} else {
    		return c - 'a' + 10;
    	}
    }
    private String decimalToHex(int num){
    	if(num >= 0 && num <= 9){
    		return String.valueOf(num);
    	} else {
    		char temp = (char)('a' + num - 10);
    		return String.valueOf(temp);
    	}
    }
}
