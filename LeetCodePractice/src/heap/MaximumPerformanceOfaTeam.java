package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
        There are n engineers numbered from 1 to n and two arrays: speed and efficiency, where speed[i] and efficiency[i] represent the speed and efficiency for the i-th engineer respectively. Return the maximum performance of a team composed of at most k engineers, since the answer can be a huge number, return this modulo 10^9 + 7.
        The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.

        Example 1:
        Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
        Output: 60
        Explanation:
        We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.

        Example 2:
        Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
        Output: 68
        Explanation:
        This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.

        Example 3:
        Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
        Output: 72

        Constraints:
        1 <= n <= 10^5
        speed.length == n
        efficiency.length == n
        1 <= speed[i] <= 10^5
        1 <= efficiency[i] <= 10^8
        1 <= k <= n

        similar to MinimumCostToHireKworkers, always sort by common multiply attribute first, but not the sum attribute.
        Then iterate each element common attribute, calculate its best result.

        tag: sort + heap
*/
public class MaximumPerformanceOfaTeam {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        final int MOD = (int) (1e9 + 7);
        long res = 0, speedSum = 0;
        Engineer[] engineers = new Engineer[n];
        for(int i = 0; i < n; i++){
            engineers[i] = new Engineer(speed[i], efficiency[i]);
        }
        Arrays.sort(engineers, (a, b) -> b.efficiency - a.efficiency);
        PriorityQueue<Engineer> pq = new PriorityQueue<>((a, b) -> a.speed - b.speed);
        for(Engineer eng : engineers){
            if(pq.size() >= k){
                Engineer slowest = pq.poll();
                speedSum -= slowest.speed;
            }
            speedSum += eng.speed;
            pq.offer(eng);
            res = Math.max(res, speedSum * eng.efficiency);
        }
        return (int)(res % MOD);
    }

    class Engineer {
        int speed, efficiency;
        public Engineer(int speed, int efficiency){
            this.speed = speed;
            this.efficiency = efficiency;
        }
    }
}
