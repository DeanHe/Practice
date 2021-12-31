package MultiThread;

/*
The idea is, that multiple threads can read from a shared resource without causing concurrency errors. The concurrency errors first occur when reads and writes to a shared resource occur concurrently,
or if multiple writes take place concurrently.

In this text I only cover Java's built-in ReadWriteLock. If you want to read more about the theory behind the implementation of a ReadWriteLock,
you can read it in my text on Read Write Locks in my Java Concurrency tutorial.

ReadWriteLock Locking Rules
The rules by which a thread is allowed to lock the ReadWriteLock either for reading or writing the guarded resource, are as follows:

Read Lock   	If no threads have locked the ReadWriteLock for writing,
and no thread have requested a write lock (but not yet obtained it).
Thus, multiple threads can lock the lock for reading.
Write Lock   	If no threads are reading or writing.
Thus, only one thread at a time can lock the lock for writing.

usage sample:
ReadWriteLock readWriteLock = new ReadWriteLock();


readWriteLock.readLock();

// multiple readers can enter this section
// if not locked for writing, and not writers waiting
// to lock for writing.

readWriteLock.readUnlock();


readWriteLock.writeLock();

// only one writer can enter this section,
// and only if no threads are currently reading.

readWriteLock.writeUnlock();
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLock {
    int readCnt = 0;
    Lock writeLock = new ReentrantLock();
    Lock readLock = new ReentrantLock();

    void readLock() throws InterruptedException {
        do {
            boolean getReadLock = readLock.tryLock();
            if(getReadLock){
                readCnt++;
                if (readCnt == 1) {
                    writeLock();
                }
                readUnlock();
                return;
            }
            Thread.sleep(1);
        } while (true);

    }

    void readUnlock() throws InterruptedException {
        do {
            boolean getReadLock = readLock.tryLock();
            if(getReadLock){
                readCnt--;
                if (readCnt == 0) {
                    writeUnlock();
                }
                readLock.unlock();
                return;
            }
            Thread.sleep(1);
        } while (true);
    }

    void writeLock() throws InterruptedException {
        do {
            boolean getWriteLock = writeLock.tryLock();
            if(getWriteLock){
                return;
            }
            Thread.sleep(1);
        } while (true);
    }

    void writeUnlock() {
        writeLock.unlock();
    }
}
