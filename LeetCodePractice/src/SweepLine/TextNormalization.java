package SweepLine;

import java.util.*;

// given a text input string, and a list of interval to character mapping, for example:
// string "Isomorphism" has following mapping:
// [0 - 3) -> a
// [2 - 7) -> b
// [4 - 8) -> c
// [6 - 10) -> d
//please output the intervals without overlap and there correspondent characters, for above case:
// [0 - 2) -> a
// [2 - 3) -> a,b
// [3 - 4) -> b
// [4 - 6) -> b,c
// [6 - 7) -> b,c,d
// [7 - 8) -> c,d
// [8 - 10) -> d
class Span {
	Set<Character> charSet;
	Interval interval;
	Span(Interval interval){
		this.interval = interval;
		charSet = new HashSet<>();
	}
}
class Tag {
	int x;
	Map<Character, Integer> charFreqMap;
	public Tag(int x){
		this.x = x;
		charFreqMap = new HashMap<>();
	}
}
public class TextNormalization {
	ArrayList<Span> normalize(ArrayList<Span> input, String str){
		ArrayList<Span> res = new ArrayList<>();
		ArrayList<Tag> axis = new ArrayList<>();
		for(Span span : input){
			Tag tag_start = new Tag(span.interval.start);
			Tag tag_end = new Tag(span.interval.end);
			for(char c : span.charSet){
				int start_c_freq = 0;
				if(tag_start.charFreqMap.containsKey(c)){
					start_c_freq = tag_start.charFreqMap.get(c);
				}
				tag_start.charFreqMap.put(c, start_c_freq + 1);
				int end_c_freq = 0;
				if(tag_end.charFreqMap.containsKey(c)){
					end_c_freq = tag_end.charFreqMap.get(c);
				} 
				tag_end.charFreqMap.put(c, end_c_freq - 1);
			}
			axis.add(tag_start);
			axis.add(tag_end);
		}
		Collections.sort(axis, (a, b) -> a.x - b.x);
		if(axis.get(0).x < 0 || axis.get(axis.size() - 1).x > str.length() - 1){
			return res;
		}
		for(int i = 1; i < axis.size(); i++){
			Tag pre = axis.get(i - 1);
			Span span =  new Span(new Interval(pre.x, axis.get(i).x));
			for(char c : pre.charFreqMap.keySet()){
				int pre_c_freq = pre.charFreqMap.get(c);
				if(pre_c_freq > 0){
					span.charSet.add(c);
				}
				int cur_c_freq = 0;
				if(axis.get(i).charFreqMap.containsKey(c)){
					cur_c_freq = axis.get(i).charFreqMap.get(c);
				}
				axis.get(i).charFreqMap.put(c, pre_c_freq + cur_c_freq);
			}
			res.add(span);
		}
		return res;
	}
}
