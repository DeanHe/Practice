package dp.bitmask;

import java.util.*;
/*
In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.
Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.
We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
Return any sufficient team of the smallest possible size, represented by the index of each person.
You may return the answer in any order.  It is guaranteed an answer exists.

Example 1:
Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
Output: [0,2]

Example 2:
Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
Output: [1,2]
 
Constraints:

1 <= req_skills.length <= 16
1 <= people.length <= 60
1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
Elements of req_skills and people[i] are (respectively) distinct.
req_skills[i][j], people[i][j][k] are lowercase English letters.
It is guaranteed a sufficient team exists.

hint:
1 Do a bitmask DP.
2 For each person, for each set of skills, we can update our understanding of a minimum set of people needed to perform this set of skills.

TC O(N^2)
*/

public class SmallestSufficientTeam {
	public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
		int req_skils_len = req_skills.length;
		int people_len = people.size();
		Map<String, Integer> map = new HashMap<>(); // skill : its bit location in bitmask
		for(int i = 0; i < req_skils_len; i++) {
			map.put(req_skills[i], i);
		}
		List<Integer>[] masks = new List[1 << req_skils_len]; // bitmask for all skills combination representation
		masks[0] = new ArrayList<>();
		for(int i = 0; i < people_len; i++) {
			int skillsPersonHave = 0;
			List<String> person = people.get(i);
			for(String skill : person) {
				skillsPersonHave |= (1 << map.get(skill));
			}
			for(int cmb = 0; cmb < masks.length; cmb++) {
				if(masks[cmb] != null) {
					int newCmb = cmb | skillsPersonHave;
					if(masks[newCmb] == null || masks[cmb].size() + 1 < masks[newCmb].size()) {
						masks[newCmb] = new ArrayList<>(masks[cmb]);
						masks[newCmb].add(i);
					}
				}
			}
		}
		List<Integer> team = masks[(1 << req_skils_len) - 1];
		int[] res = new int[team.size()];
		for(int i = 0; i < team.size(); i++) {
			res[i] = team.get(i);
		}
		return res;
	}
	public int[] smallestSufficientTeamBackTrack(String[] req_skills, List<List<String>> people) {
		int req_skils_len = req_skills.length;
		int people_len = people.size();
		int target = (1 << req_skils_len) - 1;
		Map<String, Integer> map = new HashMap<>(); // skill : its bit location in bitmask
		for(int i = 0; i < req_skils_len; i++) {
			map.put(req_skills[i], i);
		}
		int[] dp = new int[1 << req_skils_len]; // dp[i] means number of workers need to meat state i
		int[][] path = new int[1 << req_skils_len][2];
		Arrays.fill(dp, Integer.MAX_VALUE - 1);
		dp[0] = 0;
		for(int i = 0; i < people_len; i++) {
			int skillsPersonHave = 0;
			List<String> person = people.get(i);
			for(String skill : person) {
				skillsPersonHave |= (1 << map.get(skill));
			}
			if(skillsPersonHave == 0){
				continue;
			}
			for(int j = target; j >= 0; j--) {
				if(dp[j] + 1 < dp[j | skillsPersonHave]){
					dp[j | skillsPersonHave] = dp[j] + 1;
					path[j | skillsPersonHave] = new int[]{j, i};
				}
			}
		}
		List<Integer> res = new ArrayList<>();
		int state = target;
		while(state > 0){
			int preState = path[state][0];
			int personAdded = path[state][1];
			res.add(0, personAdded);
			state = preState;
		}
		int[] ret = new int[res.size()];
		for(int i = 0; i <res.size(); i++){
			ret[i] = res.get(i);
		}
		return ret;
	}
}
