"""
DataBrick interview

Encode a list of non-negative integers using:
    - Run Length Encoding (RLE) if run >= threshold
    - Bit Packing otherwise

"""


class CompressionEncoder:
    def __init__(self):
        self.cont_threshold = 8

    def encode(self, values):
        i = 0
        encoded_blocks = []
        bit_packing_vals = []
        while i < len(values):
            j, run_len = self.find_run(values, i)
            # ----- Decide whether to use RLE -----
            if run_len >= self.cont_threshold:
                if bit_packing_vals:
                    # Use bit packing for already accumulated segment
                    bit_width = max(bit_packing_vals).bit_length()
                    payload = self.pack_bits(bit_packing_vals, bit_width)
                    encoded_blocks.append({
                        "type": "BitPack",
                        "bitwidth": bit_width,
                        "count": len(bit_packing_vals),
                        "payload": payload
                    })
                    bit_packing_vals.clear()

                # Use RLE block for current segment
                encoded_blocks.append({
                    "type": "RLE",
                    "value": values[i],
                    "count": run_len,
                })
            else:
                # Collect until next long run or end of values
                bit_packing_vals.extend(values[i:j])
            i = j

        if bit_packing_vals:
            bit_width = max(bit_packing_vals).bit_length()
            payload = self.pack_bits(bit_packing_vals, bit_width)
            encoded_blocks.append({
                "type": "BitPack",
                "width": bit_width,
                "count": len(bit_packing_vals),
                "payload": payload
            })

    def find_run(self, values, start):
        end = start
        while end < len(values) and values[end] == values[start]:
            end += 1
        return end, end - start

    def pack_bits(self, vals, bit_width):
        print(vals)
        out = 0
        bits_used = 0
        result = bytearray()

        for v in vals:
            out = (out << bit_width) | v
            bits_used += bit_width

            while bits_used >= 8:
                byte = (out >> (bits_used - 8)) & 0xFF
                result.append(byte)
                bits_used -= 8

        if bits_used > 0:
            # pad the remaining bits
            byte = (out << (8 - bits_used)) & 0xFF
            result.append(byte)

        return result


if __name__ == "__main__":
    # test input
    data = [7, 7, 7, 7, 7, 7, 7, 3, 5, 6, 7, 10, 10, 10, 10, 10, 10, 10, 10, 10, 7, 7, 7, 7, 3, 5, 6]
    x = CompressionEncoder()
    x.encode(data)
