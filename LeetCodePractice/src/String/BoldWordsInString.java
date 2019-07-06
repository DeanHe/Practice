package String;
/*Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Note:

words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.*/
public class BoldWordsInString {
	public String boldWords(String[] words, String s) {
		int len = s.length();
		boolean[] mark = new boolean[len];
		for(String w : words){
			int start = 0;
			int idx = s.indexOf(w, start);
			while(idx >= 0){
				for(int i = idx; i < idx + w.length(); i++){
					mark[i] =  true;
				}
				start++;
				idx = s.indexOf(w, start);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < len; i++){
			if(mark[i] && (i == 0 || !mark[i - 1])){
				sb.append("<b>");
			}
			sb.append(s.charAt(i));
			if(mark[i] && (i == len - 1 || !mark[i + 1])){
				sb.append("</b>");
			}
		}
		return sb.toString();
	}
}
