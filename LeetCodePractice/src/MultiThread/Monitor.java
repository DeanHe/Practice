package MultiThread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Monitor {
    public static class  MonitorKey {
        String key, desc;
        public MonitorKey(String key, String desc){
            this.key = key;
            this.desc = desc;
        }
    }

    public static class MonitorValue {
        AtomicInteger count = new AtomicInteger();
        float avgTime;
        AtomicLong totalTime = new AtomicLong();
    }

    private Map<MonitorKey, MonitorValue> monitors = new ConcurrentHashMap<>();

    public synchronized void visit(String url, String desc, long timeCost) {
        MonitorKey key = new MonitorKey(url, desc);
        MonitorValue value = monitors.computeIfAbsent(key, k -> new MonitorValue());
        value.count.getAndIncrement();
        value.totalTime.getAndAdd(timeCost);
        value.avgTime = value.totalTime.get() / value.count.get();
    }

}
