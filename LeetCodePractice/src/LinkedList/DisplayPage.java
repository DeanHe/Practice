package LinkedList;
/*You are given an array of CSV strings representing search results. Results are sorted by a score initially. A
given host may have several listings that show up in these results. Suppose we want to show 12 results
per page, but we don't want the same host to dominate the results. Write a function that will reorder
the list so that a host shows up at most once on a page if possible, but otherwise preserves the ordering.
Your program should return the new array and print out the results in blocks representing the pages.
Input: An array of csv strings, with sort score number of results per page. example:
"host_id,listing_id,score,city"
"1,28,300.1,San Francisco"*/

import java.util.*;

public class DisplayPage {
	public List<String> displayPages(List<String> input, int pageSize){
		List<String> res = new ArrayList<>();
		Iterator<String> iter = input.iterator();
		boolean reachEnd = false;
		int count = 0;
		Set<String> visited = new HashSet<>();
		while(iter.hasNext()){
			String cur = iter.next();
			String id = cur.split(",")[0];
			if(!visited.contains(id) || reachEnd){
				res.add(cur);
				iter.remove();
				visited.add(id);
				count++;
			}
			if(count == pageSize){
				if(!input.isEmpty()){
					res.add(" ");
				}
				visited.clear();
				count = 0;
				reachEnd = false;
				iter = input.iterator();
			}
			if(!iter.hasNext()){
				reachEnd = true;
				iter = input.iterator();
			}
		}
		return res;
	}
}
