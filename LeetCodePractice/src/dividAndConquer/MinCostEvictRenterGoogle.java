package dividAndConquer;

import java.util.Arrays;

public class MinCostEvictRenterGoogle {
    public int minCost(int[] houses, int[] renters){
        Arrays.sort(renters);
        return dfs(houses, 0, houses.length - 1, renters, 0, renters.length - 1);
    }

    private int dfs(int[] houses, int hs, int he, int[] renters, int rs, int re) {
        if(rs > re){
            return 0;
        }
        int cost = he - hs;
        if(rs == re){
            return cost;
        } else {
            int mid = (rs + re) / 2;
            int renter = renters[mid];
            return cost + dfs(houses, hs, renter - 1, renters, rs, mid - 1) + dfs(houses,renter - 1, he, renters, mid + 1, re);
        }
    }
}
