package Sort;

import java.util.*;

/*
Give a List of data representing the coordinates[x,y] of each restaurant.
Customer's coordinates are at the origin[0,0].Find out the n restaurants closest to the customer ,
you have to pick the restaurant that first appeared, although it may not be the best option. 
return their coordinates in the original order.
*/
// https://www.lintcode.com/problem/number-of-restaurants/description
public class NumberOfRestaurants {
	/**
     * @param restaurant: 
     * @param n: 
     * @return: nothing
     */
    private int X = 0;
    private int Y = 1;
    public List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
        // Write your code here
        if(restaurant == null || restaurant.size() == 0 || n > restaurant.size()){
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        int furthestDistance = 0;
        int[] distances = new int[restaurant.size()];
        for(int i = 0; i < distances.length; i++){
            distances[i] = calculateDistance(restaurant.get(i));
        }
        Arrays.sort(distances);
        furthestDistance = distances[n - 1];
        
        for(int i = 0; i < distances.length; i++){
            if(calculateDistance(restaurant.get(i)) <= furthestDistance){
                res.add(restaurant.get(i));
            }
        }
        return res;
    }
    
    private int calculateDistance(List<Integer> point){
        return point.get(X) * point.get(X) + point.get(Y) * point.get(Y);
    }
}
