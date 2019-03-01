package HashMap;

import java.util.*;

/*Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz".
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence, return:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]*/
public class GroupShiftedStrings {
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> res = new ArrayList<>();
		HashMap<String, List<String>> map = new HashMap<>();
		for(String s : strings){
			String key = shiftBacktoStart(s);
			if(!map.containsKey(key)){
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(s);
		}
		for(List<String> list : map.values()){
			Collections.sort(list);
			res.add(list);
		}
		return res;
	}
	
	private String shiftBacktoStart(String s){
		char[] arr = s.toCharArray();
		if(arr.length > 0){
			int offset = arr[0] - 'a';
			for(int i = 0; i < arr.length; i++){
				if(arr[i] - offset < 'a'){
					arr[i] = (char)(arr[i] - offset + 26);
				} else {
					arr[i] = (char)(arr[i] - offset);
				}
			}
		}
		return new String(arr);
	}
}
