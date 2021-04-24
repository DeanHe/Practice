package string;
/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
 */
public class RomantoInteger {
    public int romanToInt(String s) {
        int res = 0;
        char[] temp = s.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            switch (temp[i]) {
                case 'I':
                    if (i < temp.length - 1 && temp[i + 1] == 'V') {
                        i++;
                        res += 4;
                    } else if (i < temp.length - 1 && temp[i + 1] == 'X') {
                        i++;
                        res += 9;
                    } else {
                        res += 1;
                    }
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    if (i < temp.length - 1 && temp[i + 1] == 'L') {
                        i++;
                        res += 40;
                    } else if (i < temp.length - 1 && temp[i + 1] == 'C') {
                        i++;
                        res += 90;
                    } else {
                        res += 10;
                    }
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    if (i < temp.length - 1 && temp[i + 1] == 'D') {
                        i++;
                        res += 400;
                    } else if (i < temp.length - 1 && temp[i + 1] == 'M') {
                        i++;
                        res += 900;
                    } else {
                        res += 100;
                    }
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
                default:
                    break;
            }
        }
        return res;
    }
}
