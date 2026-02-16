"""
Design a text editor with a cursor that can do the following:

Add text to where the cursor is.
Delete text from where the cursor is (simulating the backspace key).
Move the cursor either left or right.
When deleting text, only characters to the left of the cursor will be deleted. The cursor will also remain within the actual text and cannot be moved beyond it. More formally, we have that 0 <= cursor.position <= currentText.length always holds.

Implement the TextEditor class:

TextEditor() Initializes the object with empty text.
void addText(string text) Appends text to where the cursor is. The cursor ends to the right of text.
int deleteText(int k) Deletes k characters to the left of the cursor. Returns the number of characters actually deleted.
string cursorLeft(int k) Moves the cursor to the left k times. Returns the last min(10, len) characters to the left of the cursor, where len is the number of characters to the left of the cursor.
string cursorRight(int k) Moves the cursor to the right k times. Returns the last min(10, len) characters to the left of the cursor, where len is the number of characters to the left of the cursor.


Example 1:

Input
["TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
[[], ["leetcode"], [4], ["practice"], [3], [8], [10], [2], [6]]
Output
[null, null, 4, null, "etpractice", "leet", 4, "", "practi"]

Explanation
TextEditor textEditor = new TextEditor(); // The current text is "|". (The '|' character represents the cursor)
textEditor.addText("leetcode"); // The current text is "leetcode|".
textEditor.deleteText(4); // return 4
                          // The current text is "leet|".
                          // 4 characters were deleted.
textEditor.addText("practice"); // The current text is "leetpractice|".
textEditor.cursorRight(3); // return "etpractice"
                           // The current text is "leetpractice|".
                           // The cursor cannot be moved beyond the actual text and thus did not move.
                           // "etpractice" is the last 10 characters to the left of the cursor.
textEditor.cursorLeft(8); // return "leet"
                          // The current text is "leet|practice".
                          // "leet" is the last min(10, 4) = 4 characters to the left of the cursor.
textEditor.deleteText(10); // return 4
                           // The current text is "|practice".
                           // Only 4 characters were deleted.
textEditor.cursorLeft(2); // return ""
                          // The current text is "|practice".
                          // The cursor cannot be moved beyond the actual text and thus did not move.
                          // "" is the last min(10, 0) = 0 characters to the left of the cursor.
textEditor.cursorRight(6); // return "practi"
                           // The current text is "practi|ce".
                           // "practi" is the last min(10, 6) = 6 characters to the left of the cursor.


Constraints:

1 <= text.length, k <= 40
text consists of lowercase English letters.
At most 2 * 10^4 calls in total will be made to addText, deleteText, cursorLeft and cursorRight.


hint:
1 Making changes in the middle of some data structures is generally harder than changing the front/back of the same data structure.
2 Can you partition your data structure (text with cursor) into two parts, such that each part changes only near its ends?
3 Can you think of a data structure that supports efficient removals/additions to the front/back?
4 Try to solve the problem with two deques by maintaining the prefix and the suffix separately.

Analysis:
Deque
TC:O(N)
"""
from collections import deque


class TextEditor:

    def __init__(self):
        self.left = []
        self.right = deque([])

    def addText(self, text: str) -> None:
        self.left.append(text)

    def deleteText(self, k: int) -> int:
        removed = 0
        while k:
            if not self.left:
                break
            cur = self.left.pop()
            if len(cur) > k:
                cur = cur[:-k]
                self.left.append(cur)
                removed += k
                break
            k -= len(cur)
            removed += len(cur)
        return removed

    def cursorLeft(self, k: int) -> str:
        while k:
            if not self.left:
                break
            cur = self.left.pop()
            if len(cur) > k:
                self.left.append(cur[:-k])
                self.right.appendleft(cur[-k:])
                break
            self.right.appendleft(cur)
            k -= len(cur)
        return self.read_left_10_char()

    def cursorRight(self, k: int) -> str:
        while k:
            if not self.right:
                break
            cur = self.right.popleft()
            if len(cur) > k:
                self.left.append(cur[:k])
                self.right.appendleft(cur[k:])
                break
            self.left.append(cur)
            k -= len(cur)
        return self.read_left_10_char()

    def read_left_10_char(self):
        res = ''
        i = len(self.left) - 1
        while i >= 0 and len(res) < 10:
            res = self.left[i] + res
            i -= 1
        return res[-10:]

# Your TextEditor object will be instantiated and called as such:
# obj = TextEditor()
# obj.addText(text)
# param_2 = obj.deleteText(k)
# param_3 = obj.cursorLeft(k)
# param_4 = obj.cursorRight(k)