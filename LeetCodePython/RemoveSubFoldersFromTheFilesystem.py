"""
Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.

If a folder[i] is located within another folder[j], it is called a sub-folder of it.

The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.

Example 1:
Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.

Example 2:
Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".

Example 3:
Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]


Constraints:
1 <= folder.length <= 4 * 10^4
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'.
folder[i] always starts with the character '/'.
Each folder name is unique.

hints:
1 Sort the folders lexicographically.
2 Insert the current element in an array and then loop until we get rid of all of their subfolders, repeat this until no element is left.

analysis:
Trie
TC: O(N * L) where N is number of folders and L is maximum length of a folder path
"""
from typing import List

class TrieNode:
    def __init__(self):
        self.children = {}
        self.isEnd = False

class RemoveSubFoldersFromTheFilesystem:
    def removeSubfolders(self, folder: List[str]) -> List[str]:
        res = []
        self.root = TrieNode()
        for path in folder:
            cur = self.root
            dirs = path.split('/')
            for dir in dirs:
                if dir != '':
                    if dir not in cur.children:
                        cur.children[dir] = TrieNode()
                    cur = cur.children[dir]
            cur.isEnd = True
        for path in folder:
            is_sub = False
            cur = self.root
            dirs = path.split('/')
            for i, dir in enumerate(dirs):
                if dir != '':
                    child = cur.children[dir]
                    if child.isEnd and i != len(dirs) - 1:
                        is_sub = True
                        break
                    cur = child
            if not is_sub:
                res.append(path)
        return res





    def removeSubfoldersBySort(self, folder: List[str]) -> List[str]:
        visited = set()
        folder.sort(key=len)
        for f in folder:
            if not any(f[i] == '/' and f[:i] in visited for i in range(2, len(f))):
                visited.add(f)
        return list(visited)

