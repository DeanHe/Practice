package Trie;

import java.util.*;

/*
Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

        Return list of lists of the suggested products after each character of searchWord is typed.

        Example 1:

        Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
        Output: [
        ["mobile","moneypot","monitor"],
        ["mobile","moneypot","monitor"],
        ["mouse","mousepad"],
        ["mouse","mousepad"],
        ["mouse","mousepad"]
        ]
        Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
        After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
        After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
        Example 2:

        Input: products = ["havana"], searchWord = "havana"
        Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
        Example 3:

        Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
        Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
        Example 4:

        Input: products = ["havana"], searchWord = "tatiana"
        Output: [[],[],[],[],[],[],[]]


        Constraints:

        1 <= products.length <= 1000
        There are no repeated elements in products.
        1 <= Σ products[i].length <= 2 * 10^4
        All characters of products[i] are lower-case English letters.
        1 <= searchWord.length <= 1000
        All characters of searchWord are lower-case English letters.
*/
public class SearchSuggestionsSystem {
    class SearchNode {
        SearchNode[] arr;
        ArrayList<String> suggestions;

        private SearchNode() {
            arr = new SearchNode[26];
            suggestions = new ArrayList<>();
        }
    }

    private SearchNode root;
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        root = new SearchNode();
        for(String p : products){
            insert(p);
        }
        return search(searchWord);

    }
    private void insert(String word){
        char[] arr = word.toCharArray();
        SearchNode cur = root;
        for(char c : arr){
            int idx = c - 'a';
            if(cur.arr[idx] == null){
                cur.arr[idx] = new SearchNode();
            }
            cur = cur.arr[idx];
            cur.suggestions.add(word);
            Collections.sort(cur.suggestions);
            if(cur.suggestions.size() > 3){
                cur.suggestions.remove(cur.suggestions.size() - 1);
            }
        }
    }

    private List<List<String>> search(String searchWord){
        List<List<String>> res = new ArrayList<>();
        char[] charArr = searchWord.toCharArray();
        SearchNode cur = root;
        for(char c : charArr){
            int idx = c - 'a';
            if(cur != null){
                cur = cur.arr[idx];
            }
            if(cur != null){
                res.add(new ArrayList<>(cur.suggestions));
            } else {
                res.add(new ArrayList<>());
            }
        }
        return res;
    }
}
