package BFS;

import java.util.*;

//Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
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
