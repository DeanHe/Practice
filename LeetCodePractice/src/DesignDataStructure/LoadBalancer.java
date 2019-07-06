package DesignDataStructure;

import java.util.*;

/*Implement a load balancer for web servers. It provide the following functionality:

Add a new server to the cluster => add(server_id).
Remove a bad server from the cluster => remove(server_id).
Pick a server in the cluster randomly with equal probability => pick().
At beginning, the cluster is empty. When pick() is called you need to randomly return a server_id in the cluster.

Example
Example 1:

Input:
  add(1)
  add(2)
  add(3)
  pick()
  pick()
  pick()
  pick()
  remove(1)
  pick()
  pick()
  pick()
Output:
  1
  2
  1
  3
  2
  3
  3
Explanation: The return value of pick() is random, it can be either 2 3 3 1 3 2 2 or other.

*/
public class LoadBalancer {
	List<Integer> servers;
	Map<Integer, Integer> map; // server_id ：index in list
	Random random;
	
	public LoadBalancer() {
        // do intialization if necessary
		servers = new ArrayList<>();
		map = new HashMap<>();
		random = new Random();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        if(!map.containsKey(server_id)){
        	map.put(server_id, servers.size());
            servers.add(server_id);
        }
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        if(map.containsKey(server_id)){
        	int server_idx = map.get(server_id);
        	int last = servers.size() - 1;
        	if(server_idx != last){
        		int lastServer_id = servers.get(last);
            	servers.set(server_idx, lastServer_id);
            	map.put(lastServer_id, server_idx);
        	}
        	servers.remove(last);
        	map.remove(server_id);
        }
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        int idx = random.nextInt(servers.size());
        return servers.get(idx);
    }
}
