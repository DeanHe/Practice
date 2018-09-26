package Greedy;

public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
	    int totoalDiff = 0; 
	    int[] diff = new int[gas.length];
        for(int i = 0; i < gas.length; i++){
        	diff[i] = gas[i] - cost[i];
        	totoalDiff += diff[i];
        }
        if(totoalDiff < 0){
        	return -1;
        } else {
        	// definitely has a result station
        	int sum = 0, candidate = 0;
        	for(int i = 0; i < gas.length ; i++){
        		sum += diff[i];
        		// the totalDiff > 0, so the sum of[candidate, 0] must > 0
        		if(sum < 0){
        			candidate = i + 1;
        			sum = 0;
        		}
        	}
        	return candidate;
        }     
    }
}
