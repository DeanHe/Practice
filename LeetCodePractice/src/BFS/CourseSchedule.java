package BFS;

import java.util.*;

public class CourseSchedule {
	/*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
	//BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
    	ArrayList[] graph = new ArrayList[numCourses];
    	   for(int i = 0; i < numCourses; i++){
    	       graph[i] = new ArrayList();
    	   }
    	   int[] indegree = new int[numCourses];
    	    
    	   for(int i =0; i < prerequisites.length; i++){
    	       int post = prerequisites[i][0];
    	       int pre = prerequisites[i][1];
    	      graph[pre].add(post);
    	      indegree[post]++;
    	   }
    	    
    	   int count = 0;
    	   Queue<Integer> queue = new LinkedList<Integer>();
    	   for(int i = 0; i < indegree.length; i++){
    	       if(indegree[i]==0){
    	           //vertex without pre
    	           queue.offer(i);
    	       }
    	   }
    	   while(!queue.isEmpty()){
    	       int course = queue.poll();
    	       count++;
    	       int size = graph[course].size();
    	       for(int i = 0; i < size; i++){
    	               if(--indegree[i] == 0){
    	                   queue.offer(i);
    	               }
    	       }
    	   }
    	   return count == numCourses;
    }
    
    //DFS
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
       ArrayList[] graph = new ArrayList[numCourses];
       boolean[] visited = new boolean[numCourses];
       for(int i = 0; i < numCourses; i++){
           graph[i] = new ArrayList<Integer>();
       }
       for(int i = 0; i < prerequisites.length; i++){
            int pre = prerequisites[i][1];
            int post = prerequisites[i][0];
           graph[pre].add(post);
       }
       for(int i = 0; i < numCourses; i++){
           if(!dfs(graph,visited,i)){
               return false;
           }
       }
       return true;
    }
     
    private boolean dfs(ArrayList[] graph, boolean[] visited, int cur){
        //true- no cycle // fasle - with cycle
        if(visited[cur]){
            return false;
        }
        visited[cur] = true; 
        for(int i = 0; i < graph[cur].size(); i++){
            if(!dfs(graph, visited, (int)graph[cur].get(i))){
                return false;
            }
        }
        visited[cur] = false;
        return true;
    }
}
