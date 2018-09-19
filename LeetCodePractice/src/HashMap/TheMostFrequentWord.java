import java.util.*;

/*Give a string s witch represents the content of the novel, and then give a list indicating that the words do not participate in statistics, find the most frequent word(return the one with the smallest lexicographic order if there are more than one word)*/
// https://www.lintcode.com/problem/the-most-frequent-word/
public class TheMostFrequentWord {
	/**
     * @param s: a string
     * @param excludewords: a dict
     * @return: the most frequent word
     */
    public String frequentWord(String s, Set<String> excludewords) {
        // Write your code here
        Map<String, Integer> map = new HashMap<>();
        String[] strs = s.split(" ");
        String result = null;
        int maxFrequent = 0;
        for(String str : strs){
            str = str.replace(",","").replace(".","");
            if(!excludewords.contains(str)){
                map.putIfAbsent(str, 0);
                map.put(str, map.get(str) + 1);
                if(map.get(str) > maxFrequent){
                    result = str;
                    maxFrequent = map.get(str);
                }
                if(map.get(str) == maxFrequent && str.compareTo(result) < 0){
                    result = str;
                }
            }
        }
        return result;
    }
}
