"""
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.


Example 1:
Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3


Constraints:
0 <= capacity <= 10^4
0 <= key <= 10^5
0 <= value <= 10^9
At most 2 * 10^5 calls will be made to get and put.
"""
import collections


class DNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.freq = 1
        self.pre = self.next = None


class DLinkedList:
    def __init__(self):
        self._dummy = DNode(None, None)
        self._dummy.next = self._dummy.pre = self._dummy
        self._size = 0

    def __len__(self):
        return self._size

    def append(self, node):
        node.next = self._dummy.next
        node.pre = self._dummy
        node.next.pre = node
        self._dummy.next = node
        self._size += 1

    def pop(self, node=None):
        if self._size == 0:
            return
        if not node:
            node = self._dummy.pre
        node.pre.next = node.next
        node.next.pre = node.pre
        self._size -= 1
        return node


class LFUCache:

    def __init__(self, capacity: int):
        self._size = 0
        self._capacity = capacity

        self._key_map = {}
        self._freq_map = collections.defaultdict(DLinkedList)
        self._min_freq = 0

    def _increment(self, node):
        self._freq_map[node.freq].pop(node)
        if self._min_freq == node.freq and not self._freq_map[node.freq]:
            self._min_freq += 1
        node.freq += 1
        self._freq_map[node.freq].append(node)

    def get(self, key: int) -> int:
        if key not in self._key_map:
            return -1
        node = self._key_map[key]
        self._increment(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if self._capacity == 0:
            return
        if key in self._key_map:
            node = self._key_map[key]
            self._increment(node)
            node.val = value
        else:
            if self._size == self._capacity:
                least = self._freq_map[self._min_freq].pop()
                del self._key_map[least.key]
                self._size -= 1
            node = DNode(key, value)
            self._key_map[key] = node
            self._min_freq = 1
            self._freq_map[self._min_freq].append(node)
            self._size += 1

# Your LFUCache object will be instantiated and called as such:
# obj = LFUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
