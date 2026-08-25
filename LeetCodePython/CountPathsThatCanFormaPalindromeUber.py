"""
char(a-z)在node而不是在edge上，input arguments也不同:
- int n: number of nodes of the tree
- char[] nodes: node char, nodes[0] is root
- int[] nodeFrom: start node
- int[] nodeTo: end node, nodeFrom has an edge to nodeTo[i]
- int[] queries: query to find number of paths that can form palindrome, where query[i] is the start node

这个query说得非常的绕，举个例子treeNodes = 4, nodes = [z, a, a, a], nodeFrom = [0, 0, 1], nodeTo = [1, 2, 3], queries = [3]
- 你要从node 3(a)为起点，the path to root: 3(a) -> 1(a) -> 0(z), 能够形成palindrome的有
- end node = 3(a), form palindrome "a" (node本身肯定是个palindrome)
- end node = 1(a), form palindrome "aa"
- end node = 0(z), form palindrome "aza" (所以regardless of order，只要可以arrange成palindrome就行)
- So expected result is int[] res = [3]

the nodes form a Direct Acyclic Graph, and roots on 0
"""
from collections import defaultdict


class CountPathsThatCanFormaPalindromeUber:
    def countPalindromePaths(self, n, nodes, nodeFrom, nodeTo, queries):
        graph = defaultdict(list)
        for a, b in zip(nodeFrom, nodeTo):
            graph[a].append(b)
        mask_cnt = defaultdict(int)
        mask_cnt[0] = 1

        # counts number of paths can form palindrome start from node cur
        def dfs(cur, parent, state):
            val = ord(nodes[cur]) - ord('a')
            state ^= 1 << val
            ret = mask_cnt[state]
            for i in range(26):
                ret += mask_cnt[state ^ (1 << i)]
            for nb in graph[cur]:
                if nb != parent:
                    ret += dfs(nb, cur, state)
            state ^= 1 << val
            return ret

        dfs(0, -1, 0)
        res = []
        for q in queries:
            res.append(res[q])
        return res
