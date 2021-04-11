package designDataStructure;

import java.util.*;

/*Implement a load balancer for web serversList. It provide the following functionality:

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
	List<Integer> serversList;
	Map<Integer, Integer> map; // server ：index in list
	Random random;
	
	public LoadBalancer() {
        // do intialization if necessary
		serversList = new ArrayList<>();
		map = new HashMap<>();
		random = new Random();
    }

    /*
     * @param server: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server) {
        if(!map.containsKey(server)){
        	map.put(server, serversList.size());
            serversList.add(server);
        }
    }

    /*
     * @param server: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server) {
        if(map.containsKey(server)){
        	int server_idx = map.get(server);
        	int last = serversList.size() - 1;
        	if(server_idx != last){
        		int lastServer_id = serversList.get(last);
            	serversList.set(server_idx, lastServer_id);
            	map.put(lastServer_id, server_idx);
        	}
        	serversList.remove(last);
        	map.remove(server);
        }
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        int idx = random.nextInt(serversList.size());
        return serversList.get(idx);
    }
}
