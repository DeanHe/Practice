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
First Fit by scanning the linklist to find first sufficient free block
TC:
malloc O(N)
free O(1)
"""


class Block:
    def __init__(self, start: int, size: int):
        self.start = start
        self.size = size
        self.pre = None
        self.next = None


class Allocator:

    def __init__(self, n: int):
        # head is a linklist of the free & used blocks
        self.head = Block(0, n)
        self.used = {}

    def malloc(self, size: int) -> int:
        cur = self.head
        while cur:
            if cur.size >= size:
                if cur.size > size:
                    # split blocks
                    next_block = Block(cur.start + size, cur.size - size)
                    next_block.next = cur.next
                    cur.next = next_block
                    next_block.pre = cur
                    if next_block.next:
                        next_block.next.pre = next_block
                self.used[cur.start] = cur
                return cur.start
            cur = cur.next
        return -1

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
        # merge previous free block
        if pre_block and pre_block.start not in self.used:
            pre_block.size += cur.size
            pre_block.next = cur.next
            if cur.next:
                cur.next.pre = pre_block
        del self.used[ptr]
