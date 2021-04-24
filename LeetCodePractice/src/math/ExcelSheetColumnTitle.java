package math;
/*
#168

Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...


Example 1:
Input: columnNumber = 1
Output: "A"

Example 2:
Input: columnNumber = 28
Output: "AB"

Example 3:
Input: columnNumber = 701
Output: "ZY"

Example 4:
Input: columnNumber = 2147483647
Output: "FXSHRXW"


Constraints:

1 <= columnNumber <= 2^31 - 1

 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int columnNumber) {
        int letters = 26;
        StringBuilder sb = new StringBuilder();
        while(columnNumber > 0){
            char col = (char)('A' + (columnNumber - 1) % letters);
            sb.insert(0, col);
            columnNumber = (columnNumber - 1) / letters;
        }
        return sb.toString();
    }
}
