package OOD;

import java.util.HashMap;
import java.util.Map;

public class SlidingWIndowRateLimiter {
    private Map<String, Config> configStore = new HashMap<>();
    private Map<String, Map<Long, Integer>> requestStore = new HashMap<>();

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        Config config = getRateLimitConfig(userId);
        long startTime = currentTime - 1000 * config.windowInSec;
        int totalRequests = getCurrentWindowRequestsCount(userId, startTime);
        if (totalRequests > config.capacity) {
            return false;
        }
        registerRequest(userId, currentTime);
        return true;
    }

    private Config getRateLimitConfig(String userId) {
        return configStore.getOrDefault(userId, null);
    }

    private int getCurrentWindowRequestsCount(String userId, long startTime) {
        int total = 0;
        if (!requestStore.containsKey(userId)) {
            return total;
        }
        Map<Long, Integer> requestCountMap = requestStore.get(userId);
        for (long requestTime : requestCountMap.keySet()) {
            if (requestTime > startTime) {
                total += requestCountMap.get(requestTime);
            } else {
                requestCountMap.remove(requestTime);
            }
        }
        return total;
    }

    private void registerRequest(String userId, long currentTime) {
        Map<Long, Integer> requestCountMap = requestStore.computeIfAbsent(userId, x -> new HashMap<>());
        requestCountMap.put(currentTime, requestCountMap.getOrDefault(currentTime, 0) + 1);
    }

    private class Config {
        long windowInSec;
        int capacity;

        Config(long windowInSec, int capacity) {
            this.windowInSec = windowInSec;
            this.capacity = capacity;
        }
    }
}
