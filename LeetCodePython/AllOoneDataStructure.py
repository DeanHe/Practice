"""
Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".

Note that each function must run in O(1) average time complexity.

Example 1:
Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"

Constraints:
1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 10^4 calls will be made to inc, dec, getMaxKey, and getMinKey.

Analysis:
Double linkedlist
"""

class Node:
    def __init__(self, freq):
        self.freq = freq
        self.pre = None
        self.next = None
        self.keys = set()

class AllOne:

    def __init__(self):
        self.head = Node(0)
        self.tail = Node(0)
        self.head.next = self.tail
        self.tail.pre = self.head
        self.map = {} # key to node

    def inc(self, key: str) -> None:
        if key in self.map:
            node = self.map[key]
            freq = node.freq
            node.keys.remove(key)
            next_node = node.next
            if next_node == self.tail or next_node.freq != freq + 1:
                # create a new node
                new_node = Node(freq + 1)
                new_node.keys.add(key)
                new_node.next = next_node
                node.next = new_node
                next_node.pre = new_node
                new_node.pre = node
                self.map[key] = new_node
            else:
                next_node.keys.add(key)
                self.map[key] = next_node
            if not node.keys:
                self.removeNode(node)
        else:
            first_node = self.head.next
            if first_node == self.tail or first_node.freq > 1:
                # create a first node
                new_node = Node(1)
                new_node.keys.add(key)
                new_node.next = first_node
                self.head.next = new_node
                first_node.pre = new_node
                new_node.pre = self.head
                self.map[key] = new_node
            else:
                first_node.keys.add(key)
                self.map[key] = first_node

    def dec(self, key: str) -> None:
        if key not in self.map:
            return

        node = self.map[key]
        freq = node.freq
        node.keys.remove(key)
        if freq == 1:
            del self.map[key]
        else:
            pre_node = node.pre
            if pre_node == self.head or pre_node.freq != freq - 1:
                # create a new node
                new_node = Node(freq - 1)
                new_node.keys.add(key)
                new_node.next = node
                node.pre = new_node
                pre_node.next = new_node
                new_node.pre = pre_node
                self.map[key] = new_node
            else:
                pre_node.keys.add(key)
                self.map[key] = pre_node

        if not node.keys:
            self.removeNode(node)

    def getMaxKey(self) -> str:
        if self.tail.pre == self.head:
            return ''
        return next(iter(self.tail.pre.keys))


    def getMinKey(self) -> str:
        if self.head.next == self.tail:
            return ''
        return next(iter(self.head.next.keys))

    def removeNode(self, node):
        pre_node = node.pre
        next_node = node.next
        pre_node.next = next_node
        next_node.pre = pre_node