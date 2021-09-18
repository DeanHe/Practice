package binarySearch;

/*
On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000

 */
public class MinimizeMaxDistanceToGasStation {
    public double minmaxGasDist(int[] stations, int K) {
        double delta = 1e-6;
        double s = 0, e = 1e8;
        while(e - s > delta){
            double mid = s + (e - s) / 2;
            if(canReach(stations, K, mid)){
                e = mid;
            } else {
                s = mid;
            }
        }
        return s;
    }

    private boolean canReach(int[] stations, int k, double dist) {
        int cnt = 0;
        for(int i = 1; i < stations.length; i++){
            cnt += (int)((stations[i] - stations[i - 1]) / dist);
        }
        return cnt <= k;
    }
}

