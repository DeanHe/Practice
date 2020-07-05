package OOD;

/*
usage:

if(rateLimiter.tryAcquire(1)){
  doSomeLimitedOperation();
} else {
   sleep(1000);
}
*/

public class RateLimiter {
    private final long maxBucketSize;
    private final long permitsPerSecond;

    private double currentBucketSize;
    private long lastRefillTimestamp;

    public RateLimiter(long maxBucketSize, long permitsPerSecond){
        this.maxBucketSize = maxBucketSize;
        this.permitsPerSecond = permitsPerSecond;

        currentBucketSize = maxBucketSize;
        lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean tryAcquire(int permits){
        refill();
        if(currentBucketSize > permits){
            currentBucketSize -= permits;
            return true;
        }
        return false;
    }

    private void refill(){
        long now = System.nanoTime();
        double permitsToAdd = (now - lastRefillTimestamp) * permitsPerSecond / 1e9;
        currentBucketSize = Math.min(maxBucketSize, currentBucketSize + permitsToAdd);
        lastRefillTimestamp = now;
    }

}
