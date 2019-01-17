package BFS;

import java.util.*;

/*There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example
Given n = 2, prerequisites = [[1,0]]
Return [0,1]

Given n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
Return [0,1,2,3] or [0,2,1,3]*/
		
public class CourseScheduleII {
	/**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<List<Integer>>(numCourses);
        for(int i = 0; i < numCourses; i++){
            edges.add(new ArrayList<Integer>());
        }
        int[] indegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++){
            int pre = prerequisites[i][1];
            int post = prerequisites[i][0];
            edges.get(pre).add(post);
            indegree[post]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0;
        int[] res = new int[numCourses];
        while(!queue.isEmpty()){
            int course = queue.poll();
            res[count] = course;
            count++;
            for(Integer i : edges.get(course)){
                if(--indegree[i] == 0){
                    queue.offer(i);
                }
            }
        }
        if(count == numCourses){
            return res;
        } else {
            return new int[0];
        }
    }
}
