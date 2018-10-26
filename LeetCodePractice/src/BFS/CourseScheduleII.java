package BFS;

import java.util.*;

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
