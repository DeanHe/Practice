"""
We are given a set of shard ranges and a maximum overlap limit.
We must ensure that at any key position, the number of overlapping shards does not exceed the limit.

If overlap exceeds the limit, we must shrink ranges rather than delete entire shards.

multi-threads support:
Copy-on-write
    Minimize lock holding time
    Rebalance runs outside lock
    Atomic pointer swap
Tradeoff:
    Higher memory usage
    Eventually consistent during rebalance

company: OpenAI
"""
import heapq
import threading
from collections import defaultdict


class Shard:
    def __init__(self, id: str, start: int, end: int):
        self.id = id
        self.start = start
        self.end = end

class Shards:
    def __init__(self, limit: int):
        self.limit = limit
        self._shards = []
        self._lock = threading.RLock()

    def add_shard(self, shard: Shard):
        with self._lock:
            self._shards.append(shard)

    def remove_shard(self, shard_id: str):
        with self._lock:
            self._shards = [s for s in self._shards if s.id != shard_id]

    def get_shards(self):
        with self._lock:
            return list(self._shards)

    def rebalance(self):
        # snapshot
        snapshot = self.get_shards()
        # heavy compute outside of lock
        new_layout =self._compute_rebalance(snapshot)
        # atomic swap
        with self._lock:
            self._shards = new_layout


    def _compute_rebalance(self, shards):
        with self._lock:
            events = []
            active = [] # min heap of shard.end
            shard_segments = defaultdict(list)
            # 1. collect events
            for shard in self._shards:
                events.append((shard.start, 1, shard))
                events.append((shard.end, -1, shard))
            events.sort()
            i = 0
            while i < len(events):
                idx = events[i][0]
                # process all events at this idx
                while i < len(events) and events[i][0] == idx:
                    _, typ, shard = events[i]
                    if typ == 1:
                        heapq.heappush(active, (shard.end, shard))
                    else:
                        # for shard passed, remove it from active
                        active = [(e, s) for (e, s) in active if shard != s]
                        heapq.heapify(active)
                    i += 1
                    if i < len(events):
                        segment_end = events[i][0] - 1
                        if idx <= segment_end:
                            if len(active) < self.limit:
                                for _, shard in active:
                                    shard_segments[shard.id].append((idx, segment_end))
                            else:
                                # keep only earliest ending limit shards
                                keep = heapq.nsmallest(self.limit, active)
                                for _, shard in keep:
                                    shard_segments[shard.id].append((idx, segment_end))
                                # others get removed
            # rebuild shards from shards_segments
            res = []
            for shard in self._shards:
                segments = shard_segments[shard.id]
                for start, end in segments:
                    res.append(shard(shard.id, start, end))
            return res




