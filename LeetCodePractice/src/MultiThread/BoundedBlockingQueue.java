package MultiThread;

import java.util.*;

/*
Implement a thread-safe bounded blocking queue that has the following methods:

BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
int size() Returns the number of elements currently in the queue.
Your implementation will be tested using multiple threads at the same time. Each thread will either be a producer thread that only makes calls to the enqueue method or a consumer thread that only makes calls to the dequeue method. The size method will be called after every test case.

Please do not use built-in implementations of bounded blocking queue as this will not be accepted in an interview.
 */
public class BoundedBlockingQueue {
    private Queue<Integer> q;
    private int capacity;
    private Object lock;

    public BoundedBlockingQueue(int cap) {
        q = new LinkedList<>();
        capacity = cap;
        lock = new Object();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (lock) {
            while (q.size() == capacity) {
                lock.wait();
            }
            q.offer(element);
            lock.notifyAll();
        }
    }

    public int dequeue(int element) throws InterruptedException {
        int res = 0;
        synchronized (lock) {
            while (q.isEmpty()) {
                lock.wait();
            }
            res = q.poll();
            lock.notifyAll();
        }
        return res;
    }

    public int size() {
        return q.size();
    }
}
