import threading
import time
from concurrent.futures import ThreadPoolExecutor

import requests

"""
simulate I/O intensive tasks：download webpages (simulate network latency)
Parameters：
url：webpage url
mock_delay：simulate I/O wait time
Return：download result description
"""


def background_thread_demo(background_task):
    background_thread = threading.Thread(target=background_task, daemon=True)
    background_thread.start()


def download_url(self, url, mock_delay=2):
    thread_name = threading.currentThread().name
    print(f"[{time.strftime('%x')}] {thread_name} start download {url}")
    try:
        time.sleep(mock_delay)
        response = requests.get(url, timeout=5)
        response.raise_for_status()
        return f"[{time.strftime('%x')}] {url} finish download (simulate I/O wait {mock_delay} sec)"
    except Exception as ex:
        return f"[{time.strftime('%x')}] {url} download failed {str(ex)}"


if __name__ == "main":
    task_params = [
        ("url1", 1),
        ("url2", 2),
        ("url3", 4),
        ("url4", 2)
    ]
    print("=== Python multi threads example（I/O intensive task）===")
    start_time = time.time()
    # 4 core CPU set thread pool size to 10
    with ThreadPoolExecutor(max_workers=10) as executor:
        task_results = list(executor.map(download_url, task_params))
    end_time = time.time()
    total_duration = end_time - start_time
    print(f"\n=== multi threads task finish ===")
    print(f"duration：{total_duration:.2f} second")
    for res in task_results:
        print(f"task result：{res}")
    serial_duration = sum([delay for _, delay in task_params])
    print(
        f"if sequential execute，in theory duration≈{serial_duration:.2f} second（parallel processing improvement≈{serial_duration / total_duration:.1f} fold）")

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