"""
You are given an integer array nums of length n.

You start at index 0, and your goal is to reach index n - 1.

From any index i, you may perform one of the following operations:

Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that nums[j] % p == 0.
Return the minimum number of jumps required to reach index n - 1.

Example 1:
Input: nums = [1,2,4,6]
Output: 2
Explanation:
One optimal sequence of jumps is:
Start at index i = 0. Take an adjacent step to index 1.
At index i = 1, nums[1] = 2 is a prime number. Therefore, we teleport to index i = 3 as nums[3] = 6 is divisible by 2.
Thus, the answer is 2.

Example 2:
Input: nums = [2,3,4,7,9]
Output: 2
Explanation:
One optimal sequence of jumps is:
Start at index i = 0. Take an adjacent step to index i = 1.
At index i = 1, nums[1] = 3 is a prime number. Therefore, we teleport to index i = 4 since nums[4] = 9 is divisible by 3.
Thus, the answer is 2.

Example 3:
Input: nums = [4,6,5,8]
Output: 3
Explanation:
Since no teleportation is possible, we move through 0 → 1 → 2 → 3. Thus, the answer is 3.

Constraints:
1 <= n == nums.length <= 10^5
1 <= nums[i] <= 10^6

hints:
1 Use a breadth-first search.
2 Precompute prime factors of each nums[i] via a sieve, and build a bucket bucket[p] mapping each prime p to all indices j with nums[j] % p == 0.
3 During the BFS, when at index i, enqueue its adjacent steps (i+1 and i-1) and all indices in bucket[p] for each prime p dividing nums[i], then clear bucket[p] so each prime's bucket is visited only once.

analysis:
prime preprocessing
TC:O(NlogN)
"""
from collections import defaultdict, deque
from typing import List


class MinimumJumpsToReachEndViaPrimeTeleportation:
    def minJumps(self, nums: List[int]) -> int:
        n = len(nums)
        res = 0

        # build smallest prime factor for numbers
        most = max(nums)
        smallest_prime_factor = list(range(most + 1))
        smallest_prime_factor[0] = 0
        smallest_prime_factor[1] = 1
        for i in range(2, most + 1):
            if smallest_prime_factor[i] == i:
                for j in range(i * i, most + 1, i):
                    if smallest_prime_factor[j] == j:
                        smallest_prime_factor[j] = i

        # build prime number its divisor number neighbors
        prime_factor_to_indices = defaultdict(list)
        for i in range(n):
            tmp = nums[i]
            prime_factors = set()
            while tmp > 1:
                p = smallest_prime_factor[tmp]
                prime_factors.add(p)
                while tmp % p == 0:
                    tmp //= p
            for p in prime_factors:
                prime_factor_to_indices[p].append(i)

        visited = set()
        q = deque([0])
        visited.add(0)
        visited_primes = set()
        while q:
            q_len = len(q)
            for _ in range(q_len):
                i = q.popleft()
                cur = nums[i]
                if i == n - 1:
                    return res
                if i + 1 < n and i + 1 not in visited:
                    q.append(i + 1)
                    visited.add(i + 1)
                if 0 <= i - 1 and i - 1 not in visited:
                    q.append(i - 1)
                    visited.add(i - 1)
                # check if current is prime
                if smallest_prime_factor[cur] == cur:
                    if cur not in visited_primes:
                        visited_primes.add(cur)
                        for nb in prime_factor_to_indices[cur]:
                            if nb not in visited:
                                q.append(nb)
                                visited.add(nb)
            res += 1
        return -1
