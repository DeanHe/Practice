package MultiThread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/*
Design a log writer where multiple threads append log entries to a file, ensuring:
1 Log entries are not interleaved (each entry is atomic)
2 Entries are written in the order threads call write()
3 High throughput
4 Safe under concurrency

This is a classic question about:
Concurrent queues
Single-writer design
Backpressure
No locks on the hot path (or minimal locking)

solution:
Use a single writer thread and a multi-producer queue.

Why?
Many threads generate logs
Exactly one thread writes to disk
No interleaving between threads
Writes are serialized in order of enqueue
High throughput due to batching
 */
public class LogWriter {
    private final BlockingDeque<String> queue = new LinkedBlockingDeque<>();
    private final Thread writerThread;
    private volatile boolean running = true;

    public LogWriter(File file) throws IOException {
        try (FileWriter fw = new FileWriter(file, true)) {
            writerThread = new Thread(() -> {
                try {
                    while (running || !queue.isEmpty()) {
                        String msg = queue.poll(100, TimeUnit.MILLISECONDS);
                        if (msg != null) {
                            fw.write(msg);
                            fw.write("\n");
                        }
                    }
                    fw.flush();
                    fw.close();
                } catch (Exception e) {}
            });

            writerThread.start();
        }
    }

    public void write(String message) {
        queue.offer(message);
    }

    public void shutdown() throws InterruptedException {
        running = false;
        writerThread.join();
    }
}
