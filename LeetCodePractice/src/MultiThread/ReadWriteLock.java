package MultiThread;
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
                readLock.unlock();
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
