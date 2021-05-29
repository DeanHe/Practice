package dijkstra;

/*
The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem.
The problem is to find shortest distances between every pair of vertices in a given edge weighted directed graph.
*/
public class AllPairShortestPath {
    int INF = 99999;
    int V = 4;

    public void floydWarshall(int graph[][]) {
        int[][] dist = new int[V][V];
        int[][] path = new int[V][V];
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
                        path[i][j] = k;
                    }
                }
            }
        }
    }

    private void printPath(int start, int end, StringBuilder sb, int[][] path) {
        if (start == end) {
            return;
        }
        if (path[start][end] == 0) {
            sb.append("-" + end);
        } else {
            int mid = path[start][end];
            printPath(start, mid, sb, path);
            printPath(mid, end, sb, path);
        }
    }
}
