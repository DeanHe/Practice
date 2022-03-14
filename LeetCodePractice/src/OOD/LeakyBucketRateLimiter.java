package OOD;

/*
Token Bucket
usage:

if(rateLimiter.tryAcquire(1)){
  doSomeLimitedOperation();
} else {
   sleep(1000);
}
*/

public class LeakyBucketRateLimiter {
    private long nextAllowedTime;

    private final long REQUEST_INTERVAL_MILLIS;

    public LeakyBucketRateLimiter(long permitsPerSecond){
        nextAllowedTime = System.nanoTime();
        REQUEST_INTERVAL_MILLIS = 1000 / permitsPerSecond;
    }

    public synchronized boolean tryAcquire(){
        long now = System.nanoTime();
        if(now >= nextAllowedTime){
            nextAllowedTime = now + REQUEST_INTERVAL_MILLIS;
            return true;
        }
        return false;
    }

}
