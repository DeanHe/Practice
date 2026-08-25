import hashlib
import os
import time
import tempfile
import requests
from concurrent.futures import ThreadPoolExecutor, as_completed


def download_file(
        url: str,
        dst_path: str,
        chunk_size: int,
        max_workers: int,
        file_size: int,
        expected_sha256: str,
        max_retries: int = 3,
):
    # Never write directly to the destination.
    tmp_path = dst_path + ".tmp"

    # Preallocate the complete output file.
    with open(tmp_path, "wb") as f:
        f.truncate(file_size)

    def download_chunk(chunk_index: int):
        start = chunk_index * chunk_size
        end = min(start + chunk_size, file_size)
        expected_size = end - start

        for attempt in range(max_retries):
            try:
                headers = {
                    "Range": f"bytes={start}-{end - 1}"
                }

                response = requests.get(
                    url,
                    headers=headers,
                    timeout=30,
                )

                response.raise_for_status()

                # Verify that the server returned the requested range.
                content_range = response.headers.get("Content-Range")

                if content_range:
                    expected_range = f"bytes {start}-{end - 1}/{file_size}"

                    # In a real implementation, parse Content-Range
                    # instead of doing a literal comparison because the
                    # total size may be unknown.
                    if not content_range.startswith(
                            f"bytes {start}-{end - 1}/"
                    ):
                        raise RuntimeError(
                            f"wrong Content-Range: "
                            f"{content_range}, expected {start}-{end - 1}"
                        )

                data = response.content

                # Verify exact chunk size.
                if len(data) != expected_size:
                    raise RuntimeError(
                        f"chunk {chunk_index}: "
                        f"expected {expected_size} bytes, "
                        f"got {len(data)}"
                    )

                # Write only to this chunk's region.
                #
                # Each worker owns a disjoint byte range, so there is
                # no overlap between workers.
                with open(tmp_path, "r+b") as f:
                    f.seek(start)
                    f.write(data)

                return chunk_index

            except Exception as e:
                if attempt == max_retries - 1:
                    raise RuntimeError(
                        f"chunk {chunk_index} failed after "
                        f"{max_retries} attempts"
                    ) from e

                # Simple exponential backoff.
                time.sleep(2 ** attempt)

    num_chunks = (file_size + chunk_size - 1) // chunk_size

    try:
        with ThreadPoolExecutor(max_workers=max_workers) as executor:
            futures = [
                executor.submit(download_chunk, i)
                for i in range(num_chunks)
            ]

            # Important: wait for every task and propagate failures.
            for future in as_completed(futures):
                future.result()

        # Final size verification.
        actual_size = os.path.getsize(tmp_path)

        if actual_size != file_size:
            raise RuntimeError(
                f"wrong final size: expected {file_size}, "
                f"got {actual_size}"
            )

        actual_hash = sha256_file(tmp_path)
        if actual_hash != expected_sha256:
            raise RuntimeError("final checksum mismatch")

        # Atomic finalization.
        os.replace(tmp_path, dst_path)

    except Exception:
        # Never leave a potentially corrupt final file.
        try:
            os.remove(tmp_path)
        except FileNotFoundError:
            pass

        raise

def sha256_file(path):
    h = hashlib.sha256()

    with open(path, "rb") as f:
        while True:
            data = f.read(1024 * 1024)

            if not data:
                break

            h.update(data)

    return h.hexdigest()