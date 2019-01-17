package BFS;

import java.util.*;

/*Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example
Given pid = [1, 3, 10, 5], ppid = [3, 0, 5, 3], kill = 5, return [5,10].

Explanation: 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.
Notice
The given kill id is guaranteed to be one of the given PIDs.
n >= 1.*/
public class KillProcess {
	/**
     * @param pid: the process id
     * @param ppid: the parent process id
     * @param kill: a PID you want to kill
     * @return: a list of PIDs of processes that will be killed in the end
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // Write your code here
    	List<Integer> res = new ArrayList<>();
    	Map<Integer, List<Integer>> processTree = buildTree(pid, ppid);
    	Queue<Integer> queue = new LinkedList<>();
    	queue.offer(kill);
    	while(!queue.isEmpty()){
    		int cur = queue.poll();
    		res.add(cur);
    		if(processTree.containsKey(cur)){
    			for(int nb : processTree.get(cur)){
    				queue.offer(nb);
    			}
    		}
    	}
    	return res;    	
    }
    
    private Map<Integer, List<Integer>> buildTree(List<Integer> pid, List<Integer> ppid){
    	Map<Integer, List<Integer>> processTree = new HashMap<>();
    	for(int i = 0; i < ppid.size(); i++){
    		int parent = ppid.get(i);
    		int child = pid.get(i);
    		if(!processTree.containsKey(parent)){
    			processTree.put(parent, new ArrayList<Integer>());
    		}
    		processTree.get(parent).add(child);
    	}
    	return processTree;
    }
}
