import struct

class BinaryCodec:

    TYPE_STR = 1
    TYPE_INT = 2
    TYPE_FLOAT = 3
    TYPE_BOOL = 4

    def serialize(self, data: dict) -> bytes:
        buffer = bytearray()
        for key, value in data.items():
            self._pack_element(buffer, key)
            self._pack_element(buffer, value)
        return bytes(buffer)

    def _pack_element(self, buffer, item):
        typed, encoded = self._encode_item(item)
        buffer.extend(struct.pack('>B', typed))  # type (1 byte)
        buffer.extend(struct.pack('>I', len(encoded)))
        buffer.extend(encoded)

    def _encode_item(self, item):
        if isinstance(item, bool):
            return self.TYPE_BOOL, struct.pack('>?', item)
        if isinstance(item, int):
            return self.TYPE_INT, struct.pack('>q', item)  # 8-byte int
        if isinstance(item, float):
            return self.TYPE_FLOAT, struct.pack('>d', item)
        if isinstance(item, str):
            return self.TYPE_STR, item.encode('utf-8')
        raise TypeError(f"Unsupported type: {type(item)}")

    def deserialize(self, binary_data: bytes) -> dict:
        offset = 0
        res = {}
        while offset < len(binary_data):
            key, offset = self._unpack_element(binary_data, offset)
            value, offset = self._unpack_element(binary_data, offset)
            res[key] = value
        return res

    def _unpack_element(self, data, offset):
        typed = struct.unpack_from('>B', data, offset)[0]
        offset += 1
        length = struct.unpack_from('>I', data, offset)[0]
        offset += 4
        payload = data[offset: offset + length]
        value = self._decode_item(typed, payload)
        return value, offset + length

    def _decode_item(self, t, payload):
        if t == self.TYPE_BOOL:
            return struct.unpack('>?', payload)[0]
        if t == self.TYPE_INT:
            return struct.unpack('>q', payload)[0]
        if t == self.TYPE_FLOAT:
            return struct.unpack('>d', payload)[0]
        if t == self.TYPE_STR:
            return payload.decode('utf-8')

        raise ValueError(f"Unknown type tag: {t}")

codecs = BinaryCodec()
example = {
    "username": "Alice",
    "score": 1500,
    "ratio": 0.85,
    "active": True
}

# To Binary
binary_data = codecs.serialize(example)
print(f"Binary size:{len(binary_data)} bytes")

# Back to dict
res = codecs.deserialize(binary_data)
print(f"Restored: {res}")
