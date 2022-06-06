"""
A concert hall has n rows numbered from 0 to n - 1, each with m seats, numbered from 0 to m - 1. You need to design a ticketing system that can allocate seats in the following cases:

If a group of k spectators can sit together in a row.
If every member of a group of k spectators can get a seat. They may or may not sit together.
Note that the spectators are very picky. Hence:

They will book seats only if each member of their group can get a seat with row number less than or equal to maxRow. maxRow can vary from group to group.
In case there are multiple rows to choose from, the row with the smallest number is chosen. If there are multiple seats to choose in the same row, the seat with the smallest number is chosen.
Implement the BookMyShow class:

BookMyShow(int n, int m) Initializes the object with n as number of rows and m as number of seats per row.
int[] gather(int k, int maxRow) Returns an array of length 2 denoting the row and seat number (respectively) of the first seat being allocated to the k members of the group, who must sit together. In other words, it returns the smallest possible r and c such that all [c, c + k - 1] seats are valid and empty in row r, and r <= maxRow. Returns [] in case it is not possible to allocate seats to the group.
boolean scatter(int k, int maxRow) Returns true if all k members of the group can be allocated seats in rows 0 to maxRow, who may or may not sit together. If the seats can be allocated, it allocates k seats to the group with the smallest row numbers, and the smallest possible seat numbers in each row. Otherwise, returns false.


Example 1:

Input
["BookMyShow", "gather", "gather", "scatter", "scatter"]
[[2, 5], [4, 0], [2, 0], [5, 1], [5, 1]]
Output
[null, [0, 0], [], true, false]

Explanation
BookMyShow bms = new BookMyShow(2, 5); // There are 2 rows with 5 seats each
bms.gather(4, 0); // return [0, 0]
                  // The group books seats [0, 3] of row 0.
bms.gather(2, 0); // return []
                  // There is only 1 seat left in row 0,
                  // so it is not possible to book 2 consecutive seats.
bms.scatter(5, 1); // return True
                   // The group books seat 4 of row 0 and seats [0, 3] of row 1.
bms.scatter(5, 1); // return False
                   // There is only one seat left in the hall.


Constraints:

1 <= n <= 5 * 104
1 <= m, k <= 109
0 <= maxRow <= n - 1
At most 5 * 104 calls in total will be made to gather and scatter.

hint:
1 Since seats are allocated by smallest row and then by smallest seat numbers, how can we keep a record of the smallest seat number vacant in each row?
2 How can range max query help us to check if contiguous seats can be allocated in a range?
3 Similarly, can range sum query help us to check if enough seats are available in a range?
4 Which data structure can be used to implement the above?
"""
from typing import List

class Node:
    def __init__(self, s, e):
        self.start = s
        self.end = e
        self.left = None
        self.right = None
        self.total = 0
        self.most = 0

class SegmentTree:
    def __init__(self, s, e, val):

        def build(l, r):
            if l > r:
                return None
            if l == r:
                node = Node(l, r)
                node.total = val
                node.most = val
                return node
            node = Node(l, r)
            mid = l + (r - l) // 2
            node.left = Node(l, mid)
            node.right = Node(mid + 1, r)
            node.most = max(node.left.most, node.right.most)
            node.total = node.left.total + node.right.total
            return node

        self.root = build(s, e)

    # update the total remain seats and the max remain seats for each node (range) in the segment tree
    def update(self, idx, val):

        def update_helper(node):
            if node.start == node.end == idx:
                node.total -= val
                node.most -= val
                return
            mid = (node.start + node.end) // 2
            if idx <= mid:
                update_helper(node.left)
            else:
                update_helper(node.right)
            node.most = max(node.left.most, node.right.most)
            node.total = node.left.total + node.right.total

        update_helper(self.root)

    def query_most(self, k, max_rows, seats):

        def query_helper(node):
            if node.start == node.right:
                # check if the row number is less than maxRow and the number of remains seats is greater or equal than k
                if node.end > max_rows or node.total < k:
                    return []
                else:
                    return [node.end, seats - node.total]
            # we want to greedily search the left subtree to get the smallest row which has enough remain seats
            if node.left.most >= k:
                return query_helper(node.left)
            else:
                return query_helper(node.right)

        return query_helper(self.root)

    def query_sum(self, end_row):

        def query_helper(node, s, e):
            if s <= node.start and node.end <= e:
                return node.total
            mid = (node.start + node.end) // 2
            if e <= mid:
                return query_helper(node.left, s, e)
            elif s > mid:
                return query_helper(node.right, s, e)
            else:
                return query_helper(node.left, s, mid) + query_helper(node.right, mid + 1, e)

        return query_helper(self.root, 0, end_row)


class BookMyShow:

    def __init__(self, n: int, m: int):
        self.rows = n
        self.cols = m
        self.seg = SegmentTree(0, n - 1, m)
        # record remain seats at each row
        self.seats = [m] * n
        # record the index of the smallest row that has remain seats > 0
        self.start_row = 0

    def gather(self, k: int, maxRow: int) -> List[int]:
        res = self.seg.query_most(k, maxRow, self.cols)
        if res:
            r = res[0]
            self.seg.update(r, k)
            self.seats[r] -= k
        return res

    def scatter(self, k: int, maxRow: int) -> bool:
        if self.seg.query_sum(maxRow) < k:
            return False
        else:
            i = self.start_row
            total = 0
            while total < k:
                prev_total = total
                total += self.seats[i]
                if total < k:
                    # use up all the seats at ith row
                    self.seg.update(i, self.seats[i])
                    self.seats[i] = 0
                    i += 1
                    self.start_row = i
                else:
                    # occupy (k - prevTotal) seats at ith row
                    self.seg.update(i, k - prev_total)
                    self.seats[i] -= k - prev_total
            return True


# Your BookMyShow object will be instantiated and called as such:
# obj = BookMyShow(n, m)
# param_1 = obj.gather(k,maxRow)
# param_2 = obj.scatter(k,maxRow)