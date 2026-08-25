package MultiThread;

/*
implement 2 API:
setLatency(timestamp, latency)
→ record a latency datapoint

getP99Latency(windowSize)
→ return the 99th percentile latency within the last windowSize (e.g., last 60 seconds)

analysis:
metrics + concurrency + sliding window
 */

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LatencyTracker {

    private final ConcurrentHashMap<Long, Bucket> buckets = new ConcurrentHashMap<>();
    private final int MAX_LATENCY = 1000;

    static class Bucket {
        // histogram: latency -> count
        private final ConcurrentHashMap<Integer, AtomicInteger> histogram = new ConcurrentHashMap<>();

        void add(double latency) {
            int lat = (int) latency;
            histogram.computeIfAbsent(lat, key -> new AtomicInteger(0)).incrementAndGet();
        }

        Map<Integer, AtomicInteger> getHistogram() {
            return histogram;
        }
    }

    public void setLatency(long timestamp, double latency) {
        Bucket bucket = buckets.computeIfAbsent(timestamp, k -> new Bucket());
        bucket.add(latency);
    }

    public double getP99Latency(int windowSize) {
        long now = System.currentTimeMillis() / 1000;
        long start = now - windowSize;

        //merge histograms
        TreeMap<Integer, Integer> merged = new TreeMap<>();
        int totalCount = 0;
        for (long t = start; t <= now; t++) {
            Bucket bucket = buckets.get(t);
            if(bucket != null){
                for(Map.Entry<Integer, AtomicInteger> entry : bucket.getHistogram().entrySet()){
                    int latency = entry.getKey();
                    int count = entry.getValue().get();
                    merged.put(latency, merged.getOrDefault(latency, 0) + count);
                    totalCount += count;
                }
            }
        }
        if(totalCount > 0){
            int percentile99pos = (int)Math.ceil(totalCount * 0.99);
            int count = 0;
            for(Map.Entry<Integer, Integer> entry : merged.entrySet()){
                count += entry.getValue();
                if(count >= percentile99pos){
                    return entry.getKey();
                }
            }
        }
        return 0;
    }
}
