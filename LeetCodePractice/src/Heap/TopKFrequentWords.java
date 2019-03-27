package Heap;

import java.util.*;

public class TopKFrequentWords {
	/**
     * @param words an array of string
     * @param k an integer
     * @return an array of string
     */
	class Pair {
	    String word;
	    int freq;
	    
	    Pair(String word, int freq) {
	        this.word = word;
	        this.freq = freq;
	    }
	} 
    private Comparator<Pair> pairComparator = new Comparator<Pair>() {
        public int compare(Pair a, Pair b) {
            if (a.freq != b.freq) {
                return a.freq - b.freq;
            }
            return a.word.compareTo(b.word);
        }
    };
    
    public String[] topKFrequentWords(String[] words, int k) {
        if (k == 0) {
            return new String[0];
        }
        
        HashMap<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            if (counter.containsKey(word)) {
                counter.put(word, counter.get(word) + 1);
            } else {
                counter.put(word, 1);
            }
        }
        
        PriorityQueue<Pair> Q = new PriorityQueue<Pair>(k, pairComparator);
        for (String word : counter.keySet()) {
            Pair newPair = new Pair(word, counter.get(word));
            Q.add(newPair);
            if (Q.size() > k) {
            	Q.poll();
            } 
        }
        
        String[] result = new String[k];
        int index = 0;
        while (!Q.isEmpty()) {
            result[index++] = Q.poll().word;
        }
        
        // reverse
        for (int i = 0; i < index / 2; i++) {
            String temp = result[i];
            result[i] = result[index - i - 1];
            result[index - i - 1] = temp;
        }
        
        return result;
     }
}
