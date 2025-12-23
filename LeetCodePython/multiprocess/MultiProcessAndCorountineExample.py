import asyncio
import multiprocessing
import time


# inner layer: coroutine logic (for I/O extensive task)
async def async_worker(task_id, data_chunk, process_name):
    print(
        f"[{time.strftime('%x')}] {process_name}-coroutine {task_id}: start to process data chunk (length:{len(data_chunk)})")
    await asyncio.sleep(1)  # simulate I/O
    # simulate CPU work: compute square sum
    res = sum(x * x for x in data_chunk)
    print(f"[{time.strftime('%x')}] {process_name}-coroutine {task_id}: finish process data chunk (result:{res})")
    return res

async def process_inner_logic(data_chunk, process_name):
    # create 3 coroutines for each process
    coroutines = [async_worker(i, data_chunk, process_name) for i in range(3)]
    # parallel process coroutines
    coros_result = await asyncio.gather(*coroutines)
    return sum(coros_result)

def process_worker(data_chunk):
    process_name = multiprocessing.current_process().name
    # set independent asyncio event loop (process memory segregation, prevent parent process cycle)
    return asyncio.run(process_inner_logic(data_chunk, process_name))

def main():
    print("=== Python multi process and coroutines example ===")
    start_time = time.time()
    data_chunk = [
        list(range(1, 1001)),
        list(range(1001, 2001)),
        list(range(2001, 3001)),
        list(range(3001, 4001)),
    ]
    with multiprocessing.Pool(processes=2) as pool:
        process_results = pool.starmap(process_worker(data_chunk))
        aggregated_result = sum(process_results)
        end_time = time.time()
        total_duration = end_time - start_time
        print(f"\n=== multi process and coroutines task finish ===")
        print(f"duration：{total_duration:.2f} second")
        print(f"final results：{aggregated_result}")
        # assume each data chunk wait 1 sec for I/O, and process the data_chunks in sequence
        pure_multi_process_time = 4 * 1 + 0.5
        print(
        f"if sequential execute，in theory duration≈{pure_multi_process_time:.2f} second（hybrid processing improvement≈{pure_multi_process_time / total_duration:.1f} fold）")


if __name__ == "main":
    main()
