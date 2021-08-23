package heap;
/*
iven an array of strings words and an integer k, return the k most frequent strings.

Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.



Example 1:

Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:

Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.


Constraints:

1 <= words.length <= 500
1 <= words[i] <= 10
words[i] consists of lowercase English letters.
k is in the range [1, The number of unique words[i]]


Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?

analysis:
maintain a minheap
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
    private class Pair {
        String word;
        int freq;

        Pair(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }

    /**
     * @param words an array of string
     * @param k     an integer
     * @return an array of string
     */
    public List<String> topKFrequentWords(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (k == 0) {
            return res;
        }
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Pair> Q = new PriorityQueue<>(k, (a, b) -> {
            if (a.freq != b.freq) {
                return a.freq - b.freq;
            } else {
                return b.word.compareTo(a.word);
            }
        });
        for (String word : freq.keySet()) {
            Pair newPair = new Pair(word, freq.get(word));
            Q.add(newPair);
            if (Q.size() > k) {
                Q.poll();
            }
        }
        for (int i = 0; i < k; i++) {
            res.add(Q.poll().word);
        }
        Collections.reverse(res);
        return res;
    }
}
