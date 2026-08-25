import threading
import numpy as np
from queue import Queueclass

#Communicator:
"""Simulates inter-device communication using Queues."""
def init(self, num_devices: int):

        self.num_devices = num_devices
        self.inboxes = [Queue() for _ in range(num_devices)]

    def send(self, src: int, dst: int, data: np.ndarray) -> None:
            self.inboxes[dst].put((src, data))

    def recv(self, dst: int) -> tuple:
            return self.inboxes[dst].get()

def computefn(comm, rank, a_chunk, b, result):
"""
    TODO: Per-device compute function for Data Parallel strategy.
    Each device computes a_chunk @ b, then sends result to rank 0.
    """
    # YOUR CODE HERE
    pass

def dp_mat_mul(a: np.ndarray, b: np.ndarray, num_devices: int) -> np.ndarray:
"""
    Data Parallel Matrix Multiplication.
    Split A row-wise; each device computes partial result; gather to rank 0.
    """
    comm = Communicator(num_devices)
    result = [None]
    a_chunks = np.array_split(a, num_devices, axis=0)

    threads = []
    for rank in range(num_devices):
            t = threading.Thread(
            target=computefn,
            args=(comm, rank, a_chunks[rank], b, result)
        )
        threads.append(t)
        t.start()

    for t in threads:
            t.join()

    return result[0]

def fsdp_mat_mul(a: np.ndarray, b: np.ndarray, num_devices: int) -> np.ndarray:
        """
    Fully Sharded Data Parallel Matrix Multiplication.
    TODO: Implement from scratch.
    Both A (row-wise) and B (column-wise) are sharded across devices.
    Use all-gather: rotate B shards, each device accumulates partial results.
    """
    # YOUR CODE HERE
    pass

if name == "main":
        np.random.seed(42)
    M, K, N, num_devices = 8, 6, 4, 2
    a = np.random.randn(M, K)
    b = np.random.randn(K, N)
    expected = a @ b

    result_dp = dp_mat_mul(a, b, num_devices)
    assert np.allclose(result_dp, expected), "DP result mismatch!"
    print("DP passed!")

    result_fsdp = fsdp_mat_mul(a, b, num_devices)
    assert np.allclose(result_fsdp, expected), "FSDP result mismatch!"
    print("FSDP passed!")