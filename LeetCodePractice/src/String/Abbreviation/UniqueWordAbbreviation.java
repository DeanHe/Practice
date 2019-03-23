package String.Abbreviation;

import java.util.*;

/*An abbreviation of a word follows the form . Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example
Example1

Input:
[ "deer", "door", "cake", "card" ]
isUnique("dear")
isUnique("cart")
Output:
false
true
Explanation:
Dictionary's abbreviation is ["d2r", "d2r", "c2e", "c2d"].
"dear" 's abbreviation is "d2r" , in dictionary.
"cart" 's abbreviation is "c2t" , not in dictionary.
Example2

Input:
[ "deer", "door", "cake", "card" ]
isUnique("cane")
isUnique("make")
Output:
false
true
Explanation:
Dictionary's abbreviation is ["d2r", "d2r", "c2e", "c2d"].
"cane" 's abbreviation is "c2e" , in dictionary.
"make" 's abbreviation is "m2e" , not in dictionary.
*/
public class UniqueWordAbbreviation {
	// word : count
	Map<String, Integer> wordCount;
	// word.abbr : count
	Map<String, Integer> abbrCount;

	/*
	 * @param dictionary: a list of words
	 */
	public UniqueWordAbbreviation(String[] dictionary) {
		// do intialization if necessary
		wordCount = new HashMap<>();
		abbrCount = new HashMap<>();
		for (String s : dictionary) {
			wordCount.put(s, wordCount.getOrDefault(s, 0) + 1);
			String abbr = getAbbr(s);
			abbrCount.put(abbr, abbrCount.getOrDefault(abbr, 0) + 1);
		}
	}

	/*
	 * @param word: a string
	 * 
	 * @return: true if its abbreviation is unique or false
	 */
	public boolean isUnique(String word) {
		// write your code here
		String abbr = getAbbr(word);
		return wordCount.get(word) == abbrCount.get(abbr);
	}

	private String getAbbr(String word) {
		if (word.length() <= 2) {
			return word;
		} else {
			return "" + word.charAt(0) + (word.length() - 2) + word.charAt(word.length() - 1);
		}
	}
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary); boolean param =
 * obj.isUnique(word);
 */
