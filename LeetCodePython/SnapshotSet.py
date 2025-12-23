"""
DataBrick interview:
the space requirement is that we cannot create a copy (snapshot) of the entire list of keys when calling iterator.

To avoid copying the entire set on snapshot, you need versioning.
Instead of storing elements in the snapshot, you store a reference to a version of the data structure.
"""


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class SnapshotSet:
    def __init__(self):
        self.root = None

    def _contains(self, node, num):
        while node:
            if num < node.val:
                node = node.left
            elif num > node.val:
                node = node.right
            else:
                return True
        return False

    def _insert(self, node, num):
        if not node:
            return TreeNode(val=num)
        if num < node.val:
            return TreeNode(val=node.val, left=self._insert(node.left, num), right=node.right)
        elif num > node.val:
            return TreeNode(val=node.val, left=node.left, right=self._insert(node.right, num))
        else:
            return node

    def _find_min(self, node):
        while node.left:
            node = node.left
        return node.val

    def _remove(self, node, num):
        if not node:
            return None
        if num < node.val:
            return TreeNode(val=node.val, left=self._remove(node.left, num), right=node.right)
        elif num > node.val:
            return TreeNode(val=node.val, left=node.left, right=self._remove(node.right, num))
        else:
            # No child
            if not node.left and not node.right:
                return None
            # one child
            if not node.left:
                return node.right
            if not node.right:
                return node.left
            # two children: replace val with min of right subtree
            min_right_tree = self._find_min(node.right)
            return TreeNode(val=min_right_tree, left=node.left, right=self._remove(node.right, min_right_tree))

    def add(self, val):
        self.root = self._insert(self.root, val)

    def remove(self, val):
        self.root = self._remove(self.root, val)

    def contains(self, val):
        return self._contains(self.root, val)

    def iterator(self):
        snap_root = self.root
        return SnapshotIterator(snap_root)


class SnapshotIterator:
    def __init__(self, root):
        self.stack = []
        self._push_left(root)

    def _push_left(self, node):
        while node:
            self.stack.append(node)
            node = node.left

    def __iter__(self):
        return self

    def __next__(self):
        if not self.stack:
            raise StopIteration
        node = self.stack.pop()
        if node.right:
            self._push_left(node.right)
        return node.val
