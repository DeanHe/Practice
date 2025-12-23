import asyncio
import time


def print_with_time(msg):
    current_time = time.strftime('%x')
    print(f'[{current_time}] {msg}')


async def async_io_task(task_id, delay):
    print_with_time(f'coroutine task {task_id} starts, wait about {delay} seconds')
    await asyncio.sleep(delay)
    print_with_time(f'coroutine task {task_id} finishes')
    return f'Task-{task_id} Completed (with delay {delay} seconds)'


async def coroutine_main():
    print("=== Python multi coroutine example（I/O intensive task）===")
    start_time = time.time()
    task_list = [
        async_io_task(1, 2),
        async_io_task(2, 1),
        async_io_task(3, 4),
        async_io_task(4, 2),
        async_io_task(5, 4),
        async_io_task(6, 2),
        async_io_task(7, 3),
        async_io_task(8, 1),
    ]
    task_results = await asyncio.gather(task_list)
    # use API for single task handle: asyncio.create_task() + asyncio.as_completed()
    total_duration = time.time() - start_time
    print(f"\n=== coroutine task finish ===")
    print(f"duration：{total_duration:.2f} second")
    for i, res in enumerate(task_results, 1):
        print(f" task {i}：{res}")


if __name__ == "main":
    asyncio.run(coroutine_main())

async def block_task_example():
    loop = asyncio.get_running_loop()
    res = await loop.run_in_executor(None, time.sleep, 2)
