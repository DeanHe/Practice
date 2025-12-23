import multiprocessing
import time

"""
simulate CPU intensive task：calculate from 1 to number square sum
Parameters：
number：max value
process_name：current process name
返回：square sum result
"""


def cpu_intensive_task(number, process_name):
    print(f"[{time.strftime('%X')}] process {process_name} start compute：1-{number} square sum")
    result = sum(i * i for i in range(1, number + 1))
    print(f"[{time.strftime('%X')}] process {process_name} finish compute，result：{result}")
    return result


if __name__ == "main":
    task_params = [
        (10000000, "Worker-1"),
        (20000000, "Worker-2"),
        (30000000, "Worker-3"),
        (40000000, "Worker-4")
    ]
    print("=== Python multi process example（CPU intensive task）===")
    start_time = time.time()
    with multiprocessing.Pool(processes=multiprocessing.cpu_count()) as pool:
        task_results = pool.starmap(cpu_intensive_task, task_params)
    end_time = time.time()
    total_duration = end_time - start_time
    print(f"\n=== multi process task finish ===")
    print(f"duration：{total_duration:.2f} second")
    print(f"task results：{task_results}")
    serial_duration = total_duration * 4
    print(
        f"if sequential execute，in theory duration≈{serial_duration:.2f} second（parallel processing improvement≈{serial_duration / total_duration:.1f} fold）")
