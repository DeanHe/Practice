package Dijkstra;
/*The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem. 
The problem is to find shortest distances between every pair of vertices in a given edge weighted directed Graph.*/
public class AllPairShortestPath {
	int INF = 99999;
	int V = 4;

	void floydWarshall(int graph[][]) {
		int[][] dist = new int[V][V];
		int i, j, k;
		for (i = 0; i < V; i++) {
			for (j = 0; j < V; j++) {
				dist[i][j] = graph[i][j];
			}
		}
		for (k = 0; k < V; k++) {
			for (i = 0; i < V; i++) {
				for (j = 0; j < V; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
	}
}
