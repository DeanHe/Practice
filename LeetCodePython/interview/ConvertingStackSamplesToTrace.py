from dataclasses import dataclass
from typing import List, Tuple


@dataclass
class Sample:
    timestamp: int
    stack: list

class Span:
    name: str
    start: int
    end: int
    depth: int

def longest_common_prefix(a, b):
    n = min(len(a), len(b))
    i = 0
    while i < n and a[i] == b[i]:
        i += 1
    return i
def stack_samples_to_trace(samples: List[Sample]) -> List[Span]:
    res: List[Span] = []
    active: List[Tuple[str, int, int]] = []
    prev_stack = []
    for timestamp, stack in samples:
        # 1. common prefix
        prefix = longest_common_prefix(prev_stack, stack)

        # 2. close frames no longer present
        while len(active) > prefix:
            name, start, depth = active.pop()
            res.append(Span(name, start, timestamp, depth))

        # 3. open new frames
        for i in range(prefix, len(stack)):
            active.append((stack[i], timestamp, i))
        prev_stack =stack

    # 4. close remaining spans at final timestamp
    while active:
        name, start, depth = active.pop()
        res.append(Span(name, start, samples[-1].timestamp, depth))

    # optional: sort by start time for readability
    res.sort(key=lambda s: (s.start, s.depth))
    return res

# demo
samples = [
    Sample(0,  ["A"]),
    Sample(10, ["A", "B"]),
    Sample(20, ["A", "B", "C"]),
    Sample(30, ["A", "D"]),
    Sample(40, []),
]

trace = stack_samples_to_trace(samples)
for s in trace:
    print(s)

for s in trace:
    print(s)
