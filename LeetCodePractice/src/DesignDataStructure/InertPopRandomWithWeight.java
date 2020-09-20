package DesignDataStructure;

import java.util.ArrayList;
import java.util.Random;

public class InertPopRandomWithWeight {
    ArrayList<Integer> ls;
    ArrayList<Integer> weights;
    Random random;
    int weightSum;

    /**
     * Initialize your data structure here.
     */
    public InertPopRandomWithWeight() {
        ls = new ArrayList<>();
        random = new Random();
    }

    /**
     * Inserts a value to the set
     */
    public void insert(int val, int weight) {
        weights.add(weight);
        weightSum += weight;
        ls.add(val);
    }

    /**
     * pop a random value from the set
     */
    public int popAndRemove() {
        int prob = random.nextInt(weightSum);
        int last = ls.size() - 1;
        int i;
        for (i = 0; i < weights.size(); i++) {
            prob -= weights.get(i);
            if (prob <= 0) {
                break;
            }
        }
        int targetVal = ls.get(i);
        int targetWeight = weights.get(i);
        int lastVal = ls.get(last);
        int lastWeight = weights.get(last);
        ls.set(i, lastVal);
        ls.remove(last);
        weights.set(i, lastWeight);
        weights.remove(last);
        weightSum -= targetWeight;
        return targetVal;
    }
}
