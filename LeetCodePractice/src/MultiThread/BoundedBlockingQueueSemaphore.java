package MultiThread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/*
Implement a thread-safe bounded blocking queue that has the following methods:

BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
int size() Returns the number of elements currently in the queue.
Your implementation will be tested using multiple threads at the same time. Each thread will either be a producer thread that only makes calls to the enqueue method or a consumer thread that only makes calls to the dequeue method. The size method will be called after every test case.

Please do not use built-in implementations of bounded blocking queue as this will not be accepted in an interview.
 */
public class BoundedBlockingQueueSemaphore {
    private Queue<Integer> q;
    private int capacity;

    private Semaphore enqueue, dequeue;

    public BoundedBlockingQueueSemaphore(int cap) {
        q = new LinkedList<>();
        capacity = cap;
        enqueue = new Semaphore(cap);
        dequeue = new Semaphore(0);
    }

    public void enqueue(int element) throws InterruptedException {
        enqueue.acquire();
        q.offer(element);
        dequeue.release();
    }

    public int dequeue(int element) throws InterruptedException {
        dequeue.acquire();
        int res = q.poll();
        enqueue.release();
        return res;
    }

    public int size() {
        return q.size();
    }
}
