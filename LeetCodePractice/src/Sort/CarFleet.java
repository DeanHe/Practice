package Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

/*N cars are going to the same destination along a one lane road.  The destination is target miles away.
Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
The distance between these two cars is ignored - they are assumed to have the same position.
A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.

How many car fleets will arrive at the destination?

Example 1:

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation:
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.

Note:

0 <= N <= 10 ^ 4
0 < target <= 10 ^ 6
0 < speed[i] <= 10 ^ 6
0 <= position[i] < target
All initial positions are different.*/
public class CarFleet {
	class Car {
		int pos;
		double timeToArrive;
		public Car(int pos, double timeToArrive){
			this.pos = pos;
			this.timeToArrive = timeToArrive;
		}
	}
	public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        Car[] car = new Car[len];
        for(int i = 0; i < len; i++){
        	car[i] =  new Car(position[i], 1.0 * (target - position[i]) / speed[i]);
        }
        Arrays.sort(car, (a, b) -> a.pos - b.pos);
        double longestTimeToArrive = 0;
        int res = 0;
        for(int i = len - 1; i >= 0; i--){
        	if(car[i].timeToArrive > longestTimeToArrive){
        		longestTimeToArrive = car[i].timeToArrive;
        		res++;
        	}
        }
        return res;
    }
	
	public int carFleetWithTreeMap(int target, int[] position, int[] speed) {
        int len = position.length;
        TreeMap<Integer, Double> map = new TreeMap<>(Collections.reverseOrder());
        for(int i = 0; i < len; i++){
        	map.put(position[i], 1.0 * (target - position[i]) / speed[i]);
        }
        double longestTimeToArrive = 0;
        int res = 0;
        for(Double timeToArrive : map.values()){
        	if(timeToArrive > longestTimeToArrive){
        		longestTimeToArrive = timeToArrive;
        		res++;
        	}
        }
        return res;
    }
}
