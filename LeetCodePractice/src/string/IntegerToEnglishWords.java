package string;

/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

        Example 1:

        Input: 123
        Output: "One Hundred Twenty Three"
        Example 2:

        Input: 12345
        Output: "Twelve Thousand Three Hundred Forty Five"
        Example 3:

        Input: 1234567
        Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
        Example 4:

        Input: 1234567891
        Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

        analysis:
        recursive, divide to group: [0 ~ 9, 10 ~ 19, 20 ~ 90]
*/

public class IntegerToEnglishWords {
    private final String[] belowTen = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "zero";
        }
        return helper(num);
    }

    public String helper(int num) {
        String res;
        if (num < 10) {
            res = belowTen[num];
        } else if (num < 20) {
            res = belowTwenty[num];
        } else if (num < 100) {
            res = belowHundred[num / 10] + " " + helper(num % 10);
        } else if (num < 1000) {
            res = helper(num / 100) + " Hundred " + helper(num % 100);
        } else if (num < 1000000) {
            res = helper(num / 1000) + " Thousand " + helper(num % 1000);
        } else if (num < 1000000000) {
            res = helper(num / 1000000) + " Million " + helper(num % 1000000);
        } else {
            res = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
        }
        return res.trim();
    }
}
