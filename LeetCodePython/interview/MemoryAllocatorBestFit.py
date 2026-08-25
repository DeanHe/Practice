from sortedcontainers import SortedDict

"""
Design and implement a simplified memory allocator
exposing malloc(size) and free(ptr) over a single fixed-size heap (a contiguous byte array).
The allocator must not request more memory from the operating system after initialization — it only manages the bytes it was given.
Walk from a straightforward correct implementation to an optimized one, and analyze the trade-offs.

The heap is one contiguous region, e.g. byte[N] , with N fixed at init.
malloc(size) returns a pointer/offset to a block of at least size usable bytes, or fails (returns null / sentinel) if no block can satisfy the request.
free(ptr) marks a previously-allocated block reusable. You may assume ptr was returned by a prior malloc (but discuss defending against misuse).
Single-threaded for the core problem; concurrency is a follow-up.
Allocations should be aligned to a machine-friendly boundary (e.g. 8 or 16 bytes).

analysis:
Best Fit to find the smallest sufficient free block
TC: 
malloc O(logN)
free O(1)
https://prachub.com/interview-questions/implement-a-simple-memory-allocator
"""


class Block:
    def __init__(self, start: int, size: int):
        self.start = start
        self.size = size
        self.pre = None
        self.next = None
        self.used = False


class Allocator:

    def __init__(self, n: int):
        # head is a linklist of the free & used blocks
        self.head = Block(0, n)
        # sorted key by size
        self.free_blocks = SortedDict()
        self._free_blocks_add(self.head)
        self.used = {}

    def malloc(self, size: int) -> int:
        if size <= 0:
            return -1
        idx = self.free_blocks.bisect_left(size)
        if idx == len(self.free_blocks):
            return -1
        block_size = self.free_blocks.keys()[idx]
        free_blocks_ge_size = self.free_blocks[block_size]
        cur = next(iter(free_blocks_ge_size))
        self._free_blocks_remove(cur)
        if cur.size > size:
            # split
            remaining = Block(cur.start + size, cur.size - size)
            remaining.next = cur.next
            cur.next = remaining
            remaining.pre = cur
            if remaining.next:
                remaining.next.pre = remaining
            self._free_blocks_add(remaining)
        self.used[cur.start] = cur
        return cur.start

    def free(self, ptr: int):
        if ptr not in self.used:
            raise Exception("Invalid free")
        cur = self.used[ptr]
        pre_block = cur.pre
        next_block = cur.next
        # merge next free block
        if next_block and next_block.start not in self.used:
            cur.size += next_block.size
            cur.next = next_block.next
            if next_block.next:
                next_block.next.pre = cur
            self._free_blocks_remove(next_block)
            self._free_blocks_add(cur)
        # merge previous free block
        if pre_block and pre_block.start not in self.used:
            pre_block.size += cur.size
            pre_block.next = cur.next
            if cur.next:
                cur.next.pre = pre_block
            self._free_blocks_remove(cur)
            self._free_blocks_add(pre_block)
        del self.used[ptr]

    def _free_blocks_add(self, block):
        if block.size not in self.free_blocks:
            self.free_blocks[block.size] = set()
        self.free_blocks[block.size].add(block)

    def _free_blocks_remove(self, block):
        blocks = self.free_blocks[block.size]
        blocks.remove(block)
        if not blocks:
            del self.free_blocks[block.size]
