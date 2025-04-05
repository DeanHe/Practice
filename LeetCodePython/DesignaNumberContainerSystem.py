"""
Design a number container system that can do the following:

Insert or Replace a number at the given index in the system.
Return the smallest index for the given number in the system.
Implement the NumberContainers class:

NumberContainers() Initializes the number container system.
void change(int index, int number) Fills the container at index with the number. If there is already a number at that index, replace it.
int find(int number) Returns the smallest index for the given number, or -1 if there is no index that is filled by number in the system.


Example 1:
Input
["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
[[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
Output
[null, -1, null, null, null, null, 1, null, 2]

Explanation
NumberContainers nc = new NumberContainers();
nc.find(10); // There is no index that is filled with number 10. Therefore, we return -1.
nc.change(2, 10); // Your container at index 2 will be filled with number 10.
nc.change(1, 10); // Your container at index 1 will be filled with number 10.
nc.change(3, 10); // Your container at index 3 will be filled with number 10.
nc.change(5, 10); // Your container at index 5 will be filled with number 10.
nc.find(10); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index that is filled with 10 is 1, we return 1.
nc.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20.
nc.find(10); // Number 10 is at the indices 2, 3, and 5. The smallest index that is filled with 10 is 2. Therefore, we return 2.


Constraints:
1 <= index, number <= 10^9
At most 10^5 calls will be made in total to change and find.

hints:
1 Use a hash table to efficiently map each number to all of its indices in the container and to map each index to their current number.
2 In addition, you can use ordered set to store all of the indices for each number to solve the find method. Do not forget to update the ordered set according to the change method.

Analysis:
min heap with lazy update
TC: find O(K*logN) change O(logN)
"""
import heapq
from collections import defaultdict


class NumberContainers:

    def __init__(self):
        self.number_to_indices = defaultdict(list)
        self.index_to_number = {}

    def change(self, index: int, number: int) -> None:
        self.index_to_number[index] = number
        heapq.heappush(self.number_to_indices[number], index)

    def find(self, number: int) -> int:
        if not self.number_to_indices[number]:
            return -1
        while self.number_to_indices[number]:
            smallest_idx = self.number_to_indices[number][0]
            if self.index_to_number[smallest_idx] != number:
                heapq.heappop(self.number_to_indices[number])
            else:
                return smallest_idx
        return -1
