"""

"""
import collections

class FieldValue:
    def __init__(self, value, timestamp, ttl=None):
        self.value = value
        self.timestamp = timestamp
        self.ttl = ttl
        self.expiry = timestamp + ttl if ttl is not None else float('inf')

class InMemoryDatabase:
    def __init__(self):
        # database[key][field] = FieldValue object
        self.database = {}
        self.backups = {}
        self.backup_counter = 0

    def _is_valid(self, field_val, current_time):
        return field_val.expiry > current_time

    def _clean_expired(self, key, timestamp):
        if key not in self.database:
            return
        fields = self.database[key]
        expired_fields = [f for f, obj in fields.items() if not self._is_valid(obj, timestamp)]
        for f in expired_fields:
            del fields[f]
        if not self.database[key]:
            del self.database[key]

    # --- Level 1 & 3 (Unified for TTL) ---

    def SET_FIELD(self, key, field, value, timestamp=0, ttl=None):
        if key not in self.database:
            self.database[key] = {}
        self.database[key][field] = FieldValue(value, timestamp, ttl)
        return value

    def GET_FIELD(self, key, field, timestamp=float('inf')):
        if key in self.database and field in self.database[key]:
            f_obj = self.database[key][field]
            if self._is_valid(f_obj, timestamp):
                return f_obj.value
        return ""

    def DELETE_FIELD(self, key, field, timestamp=float('inf')):
        if key in self.database and field in self.database[key]:
            if self._is_valid(self.database[key][field], timestamp):
                del self.database[key][field]
                if not self.database[key]:
                    del self.database[key]
                return "true"
        return "false"

    def GET(self, key, timestamp=float('inf')):
        if key not in self.database:
            return ""
        self._clean_expired(key, timestamp)
        if key not in self.database:
            return ""

        fields = self.database[key]
        sorted_fields = sorted(fields.keys())
        res = [f"{f}({fields[f].value})" for f in sorted_fields]
        return ", ".join(res)

    # --- Level 2 & 3 (Filtering & Queries) ---

    def SCAN(self, prefix, timestamp=float('inf')):
        results = []
        for key in sorted(self.database.keys()):
            self._clean_expired(key, timestamp)
            if key in self.database and key.startswith(prefix):
                results.append(key)
        return ", ".join(results)

    def SCAN_BY_FIELD(self, field, value, timestamp=float('inf')):
        results = []
        for key in sorted(self.database.keys()):
            if field in self.database[key]:
                f_obj = self.database[key][field]
                if self._is_valid(f_obj, timestamp) and f_obj.value == value:
                    results.append(key)
        return ", ".join(results)

    def DELETE(self, key):
        if key in self.database:
            del self.database[key]
            return "true"
        return "false"

    def TOP_N_KEYS(self, n, timestamp=float('inf')):
        counts = []
        for key in self.database.keys():
            self._clean_expired(key, timestamp)
            if key in self.database:
                counts.append((key, len(self.database[key])))

        # Sort by count desc, then key asc
        counts.sort(key=lambda x: (-x[1], x[0]))
        res = [f"{k}({c})" for k, c in counts[:n]]
        return ", ".join(res)

    # --- Level 4 (Backup & Restore) ---

    def BACKUP(self, timestamp):
        self.backup_counter += 1
        b_id = f"backup_{self.backup_counter}"
        snapshot = {}

        for key, fields in self.database.items():
            valid_fields = {}
            for f, obj in fields.items():
                if self._is_valid(obj, timestamp):
                    # Store raw data to recreate FieldValue later
                    valid_fields[f] = (obj.value, obj.timestamp, obj.ttl)
            if valid_fields:
                snapshot[key] = valid_fields

        self.backups[b_id] = {"data": snapshot, "time": timestamp}
        return b_id

    def RESTORE(self, timestamp, backup_id):
        if backup_id not in self.backups:
            return ""

        backup = self.backups[backup_id]
        b_time = backup["time"]
        self.database = {}

        for key, fields in backup["data"].items():
            self.database[key] = {}
            for f, (val, orig_t, ttl) in fields.items():
                new_ttl = None
                if ttl is not None:
                    # Logic: expiry = R + (T + X - backup_timestamp)
                    new_ttl = (orig_t + ttl) - b_time

                # We essentially re-SET at the RESTORE timestamp
                self.database[key][f] = FieldValue(val, timestamp, new_ttl)

        return str(len(self.database))