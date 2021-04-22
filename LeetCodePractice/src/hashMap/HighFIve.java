package hashMap;
/*Description
There are two properties in the node student id and scores, to ensure that each student will have at least 5 points, find the average of 5 highest scores for each person.


Example
Given results = [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]*/

import java.util.*;

public class HighFIve {
	
	class Record {
		public int id, score;

		public Record(int id, int score) {
			this.id = id;
			this.score = score;
		}
	}
	
	/**
	 * @param results
	 *            a list of <student_id, score>
	 * @return find the average of 5 highest scores for each person Map<Integer,
	 *         Double> (student_id, average_score)
	 */
	public Map<Integer, Double> highFive(Record[] results) {
		// student id : average score
		Map<Integer, Double> answer = new HashMap<>();
		// student id : scores of different course
		Map<Integer, PriorityQueue<Integer>> hash = new HashMap<Integer, PriorityQueue<Integer>>();
		int K = 5;
		for (Record r : results) {
			if (!hash.containsKey(r.id)) {
				PriorityQueue<Integer> pq = new PriorityQueue<Integer>(K, new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						return o1 - o2; // min heap
					}
					
				});
				hash.put(r.id, pq);
			}
			hash.get(r.id).offer(r.score);
			if (hash.get(r.id).size() > K) {
				hash.get(r.id).poll();
			}
		}
		for (Map.Entry<Integer, PriorityQueue<Integer>> entry : hash.entrySet()) {
			int id = entry.getKey();
			PriorityQueue<Integer> scores = entry.getValue();
			double average = 0.0;
			while(!scores.isEmpty()){
				average += scores.poll();
			}
			average /= scores.size();
			answer.put(id, average);
		}
		return answer;
	}
}
