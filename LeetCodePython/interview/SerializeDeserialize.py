import struct

class BinaryCodec:

    def serialize(self, data: dict) -> bytes:
        buffer = bytearray()
        for key, value in data.items():
            self._pack_element(buffer, key)
            self._pack_element(buffer, value)
        return bytes(buffer)

    def _pack_element(self, buffer, item):
        encoded = item.encode('utf-8')
        buffer.extend(struct.pack('>I', len(encoded)))
        buffer.extend(encoded)

    def deserialize(self, binary_data: bytes) -> dict:
        offset = 0
        res = {}
        while offset < len(binary_data):
            key, offset = self._unpack_element(binary_data, offset)
            value, offset = self._unpack_element(binary_data, offset)
            res[key] = value
        return res

    def _unpack_element(self, data, offset):
        length = struct.unpack_from('>I', data, offset)[0]
        offset += 4
        value = data[offset: offset + length].decode('utf-8')
        return value, offset + length

codecs = BinaryCodec()
example = {"username": "Alice", "score": "1500", "ratio": "0.85"}

# To Binary
binary_data = codecs.serialize(example)
print(f"Binary size:{len(binary_data)} bytes")

# Back to dict
res = codecs.deserialize(binary_data)
print(f"Restored: {res}")
