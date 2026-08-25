import concurrent.futures
import threading
import time
import warnings
from concurrent.futures import ThreadPoolExecutor

warnings.filterwarnings("ignore")

import requests

"""
simulate I/O intensive tasks:download webpages (simulate network latency)
Parameters:
url:webpage url
mock_delay:simulate I/O wait time
Return:download result description
"""

def format_elapsed_time(start_time):
    elapsed = time.time() - start_time
    minutes = int(elapsed // 60)
    seconds = int(elapsed % 60)
    return f"{minutes}m {seconds}s"
    
def background_thread_demo(background_task):
    background_thread = threading.Thread(target=background_task, daemon=True)
    background_thread.start()


def download_url(url, mock_delay=2):
    thread_name = threading.current_thread().name
    start_time = time.time()
    print(f"[{format_elapsed_time(start_time)}] {thread_name} start download {url}")
    try:
        time.sleep(mock_delay)
        response = requests.get(url, timeout=5)
        response.raise_for_status()
        return f"[{format_elapsed_time(start_time)}] {url} finish download (simulate I/O wait {mock_delay} sec)"
    except Exception as ex:
        return f"[{format_elapsed_time(start_time)}] {url} download failed {str(ex)}"


if __name__ == "__main__":
    task_params = [
        ("url1", 1),
        ("url2", 2),
        ("url3", 4),
        ("url4", 2)
    ]
    print("=== Python multi threads example (I/O intensive task) ===")
    start_time = time.time()
    # 4 core CPU set thread pool size to 10
    with ThreadPoolExecutor(max_workers=10) as executor:
        futures = []
        for url, delay in task_params:
            futures.append(executor.submit(download_url, url, delay))
    end_time = time.time()
    total_duration = end_time - start_time
    print(f"\n=== multi threads task finish ===")
    print(f"duration:{total_duration:.2f} second")
    for future in concurrent.futures.as_completed(futures):
    # for future in futures:
        print(f"task result:{future.result()}")
    serial_duration = sum([delay for _, delay in task_params])
    print(
        f"if sequential execute, in theory duration≈{serial_duration:.2f} second (parallel processing improvement≈{serial_duration / total_duration:.1f} fold）")

def thread_lock_example():
    cnt = 0
    lock = threading.Lock()

    def increment():
        global cnt
        with lock:
            cnt += 1

    with ThreadPoolExecutor(max_workers=5) as executor:
        for _ in range(1000):
            executor.submit(increment)
    print(cnt)