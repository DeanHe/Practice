import threading
from typing import Optional, List, Dict


class Entity:
    """Abstract base class for all file system components."""

    def __init__(self, entity_id: str, name: str):
        self.id: str = entity_id
        self.name: str = name
        self.parent: Optional['Directory'] = None  # Q3: Pointer for upward traversal

    def get_size(self) -> int:
        raise NotImplementedError

    def is_directory(self) -> bool:
        raise NotImplementedError


class File(Entity):
    """Represents a leaf node (file) containing an actual size."""

    def __init__(self, entity_id: str, name: str, size: int):
        super().__init__(entity_id, name)
        self._size: int = size
        # File level lock to protect size updates
        self._lock = threading.RLock()

    def get_size(self) -> int:
        with self._lock:
            return self._size

    def is_directory(self) -> bool:
        raise False


class Directory(Entity):
    """Represents a composite node (directory) that can contain children."""
    def __init__(self, entity_id: str, name: str):
        super().__init__(entity_id, name)
        self.children: List[Entity] = []
        # Q2 Optimization: Memoization cache (-1 means uncalculated/invalidated)
        self._cached_size: int = -1
        # Node-level lock protects this directory's children array and its cache state
        self._lock = threading.RLock()

    def add_child(self, child: Entity):
        # Always acquire locks top-to-bottom to prevent deadlocks
        with self._lock:
            self.children.append(child)
            child.parent = self
            self.invalidate_cache()

    def get_size(self) -> int:
        with self._lock:
            if self._cached_size != -1:
                return self._cached_size
            total_size = sum(child.get_size() for child in self.children)
            self._cached_size = total_size
            return total_size

    def invalidate_cache(self):
        """"Recursively invalidates parent caches when a change occurs."""
        with self._lock:
            self._cached_size = -1
            if self.parent:
                self.parent.invalidate_cache()


class FileSystem:
    def __init__(self):
        # Global map to locate any entity in O(1) time
        self.entity_map: Dict[str, Entity] = {}
        self._map_lock = threading.RLock() # Protects the registry map
        self.root = Directory("root", "/")
        self.entity_map["root"] = self.root

    def register_entity(self, entity: Entity):
        with self._map_lock:
            self.entity_map[entity.id] = entity

    # ================= Q1 & Q2: Get Entity Size =================
    def get_entity_size(self, entity_id: str):
        with self._map_lock:
            entity = self.entity_map.get(entity_id)
            if not entity:
                return 0
            return entity.get_size()

    # ================= Q3: Absolute Path Tracing =================
    def get_absolute_path(self, entity_id: str) -> str:
        with self._map_lock:
            entity = self.entity_map.get(entity_id)
            if not entity:
                return ""
            path = []
            cur = entity
            # Bottom-up trace to root using parent pointers
            while cur:
                with cur._lock:
                    path.append(cur.name)
                    cur = cur.parent
            # Reverse components to go from top to bottom
            path.reverse()
            return '/'.join(path)
