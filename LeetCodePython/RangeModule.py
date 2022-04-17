"""
A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.

A half-open interval [left, right) denotes all the real numbers x where left <= x < right.

Implement the RangeModule class:

RangeModule() Initializes the object of the data structure.
void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).


Example 1:
Input
["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
Output
[null, null, null, true, false, true]

Explanation
RangeModule rangeModule = new RangeModule();
rangeModule.addRange(10, 20);
rangeModule.removeRange(14, 16);
rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)


Constraints:

1 <= left < right <= 10^9
At most 10^4 calls will be made to addRange, queryRange, and removeRange.

hint:
1 Maintain a sorted set of disjoint intervals. addRange and removeRange can be performed with time complexity linear to the size of this set;
queryRange can be performed with time complexity logarithmic to the size of this set.
"""
import bisect


class RangeModule:

    def __init__(self):
        self.axis = []

    def addRange(self, left: int, right: int) -> None:
        ll = bisect.bisect_left(self.axis, left)
        rr = bisect.bisect_right(self.axis, right)
        tmp = []
        if ll % 2 == 0:
            tmp.append(left)
        if rr % 2 == 0:
            tmp.append(right)
        self.axis[ll:rr] = tmp

    def queryRange(self, left: int, right: int) -> bool:
        lr = bisect.bisect_right(self.axis, left)
        rl = bisect.bisect_left(self.axis, right)
        return lr == rl and lr % 2 == 1

    def removeRange(self, left: int, right: int) -> None:
        ll = bisect.bisect_left(self.axis, left)
        rr = bisect.bisect_right(self.axis, right)
        tmp = []
        if ll % 2 == 1:
            tmp.append(left)
        if rr % 2 == 1:
            tmp.append(right)
        self.axis[ll:rr] = tmp

# Your RangeModule object will be instantiated and called as such:
# obj = RangeModule()
# obj.addRange(left,right)
# param_2 = obj.queryRange(left,right)
# obj.removeRange(left,right)
