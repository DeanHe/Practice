"""
Write an API that generates fancy sequences using the append, addAll, and multAll operations.

Implement the Fancy class:
Fancy() Initializes the object with an empty sequence.
void append(val) Appends an integer val to the end of the sequence.
void addAll(inc) Increments all existing values in the sequence by an integer inc.
void multAll(m) Multiplies all existing values in the sequence by an integer m.
int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.

Example 1:
Input
["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
[[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
Output
[null, null, null, null, null, 10, null, null, null, 26, 34, 20]

Explanation
Fancy fancy = new Fancy();
fancy.append(2);   // fancy sequence: [2]
fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
fancy.append(7);   // fancy sequence: [5, 7]
fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
fancy.getIndex(0); // return 10
fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
fancy.append(10);  // fancy sequence: [13, 17, 10]
fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
fancy.getIndex(0); // return 26
fancy.getIndex(1); // return 34
fancy.getIndex(2); // return 20

Constraints:
1 <= val, inc, m <= 100
0 <= idx <= 10^5
At most 10^5 calls total will be made to append, addAll, multAll, and getIndex.

hints:
1 Use two arrays to save the cumulative multipliers at each time point and cumulative sums adjusted by the current multiplier.
2 The function getIndex(idx) ask to the current value modulo 10^9+7. Use modular inverse and both arrays to calculate this value.

analysis:
Prefix sum
TC:O(N)
"""
import math


class Fancy:
    def __init__(self):
        self.res = []
        self.add = [0]
        self.multi = [1]
        self.MOD = 10 ** 9 + 7

    def append(self, val: int) -> None:
        self.res.append(val)
        self.add.append(self.add[-1])
        self.multi.append(self.multi[-1])

    def addAll(self, inc: int) -> None:
        self.add[-1] = (self.add[-1] + inc) % self.MOD

    def multAll(self, m: int) -> None:
        self.add[-1] = (self.add[-1] * m) % self.MOD
        self.multi[-1] = (self.multi[-1] * m) % self.MOD

    def getIndex(self, idx: int) -> int:
        if idx > len(self.res):
            return -1
        multi = self.multi[-1] * pow(self.multi[idx], self.MOD - 2, self.MOD)
        inc = self.add[-1] - self.add[idx] * multi
        return (self.res[idx] * multi + inc) % self.MOD




# Your Fancy object will be instantiated and called as such:
# obj = Fancy()
# obj.append(val)
# obj.addAll(inc)
# obj.multAll(m)
# param_4 = obj.getIndex(idx)