package MinMax;
/*This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.

analysis:
calculate unique rank of each word
guess the most common word m (word with lowest unique rank), and get its score
from the pool, find all other words compare with m having the same match core as the guess score
repeat the above process

Time Complexity: O(N^2 log N), where N is the number of words, and assuming their length is O(1). Each call to solve is O(N^2)
and the number of calls is bounded by O(logN).
Space Complexity: O(N^2).
*/

import java.util.*;

public class GuessTheWord {
	public void findSecretWord(String[] wordlist, Master master) {
		HashMap<String, Integer> uniqueLevel = new HashMap<>();
		for (String w1 : wordlist) {
			for (String w2 : wordlist) {
				if (match(w1, w2) == 0) {
					uniqueLevel.put(w1, uniqueLevel.getOrDefault(w1, 0) + 1);
				}
			}
		}
		for (int i = 0, score = 0; i < 10 && score < 6; i++) {
			// the least unique word
			String minimax_str = "";
			int minimax_count = 1000;
			for (String w : wordlist) {
				// System.out.println(w + ":" + uniqueLevel.getOrDefault(w, 0));
				if (uniqueLevel.getOrDefault(w, 0) < minimax_count) {
					minimax_str = w;
					minimax_count = uniqueLevel.getOrDefault(w, 0);
				}
			}
			score = master.guess(minimax_str);
			List<String> ls = new ArrayList<>();
			for (String w : wordlist) {
				if (match(w, minimax_str) == score) {
					ls.add(w);
				}
			}
			wordlist = ls.toArray(new String[ls.size()]);
		}
	}

	private int match(String a, String b) {
		int matches = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(i)) {
				matches++;
			}
		}
		return matches;
	}

	//Count the Occurrence of Characters
	//time complexity: O(N), space complexity: O(N)
	public void findSecretWord2(String[] wordlist, Master master) {
		int[][] cnt = new int[6][26];
		for(String s : wordlist){
			for(int i = 0; i < 6; i++){
				cnt[i][s.charAt(i) - 'a']++;
			}
		}
		for(int i = 0, score = 0; i < 10 && score < 6; i++){
			int best = 0;
			String cand = "";
			for(String s : wordlist){
				int points = 0;
				for(int j = 0; j < 6; j++){
					points += cnt[j][s.charAt(j) - 'a'];
				}
				if(points > best){
					cand = s;
					best = points;
				}
			}
			score = master.guess(cand);
			List<String> ls = new ArrayList<>();
			for(String s : wordlist){
				if(match(s, cand) == score) {
					ls.add(s);
				}
			}
			wordlist = ls.toArray(new String[ls.size()]);
		}
	}
}
