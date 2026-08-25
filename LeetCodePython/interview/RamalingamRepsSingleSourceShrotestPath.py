import heapq
import math


class RamalingamRepsSSSP:
    def __init__(self, graph, src):
        # graph format: {u: {v: weight, ...}, ...}
        self.graph = graph
        self.src = src
        self.dist = {}
        self.parent = {}
        self.dist = {node: math.inf for node in self.graph}
        self.dist[self.src] = 0
        self._initial_dijkstra(self.src)

    def _initial_dijkstra(self, start):
        pq = [(self.dist[start], start)]
        while pq:
            d, cur = heapq.heappop(pq)
            if d == self.dist[cur]:
                for nb, nb_weight in self.graph[cur].items():
                    if self.dist[cur] + nb_weight < self.dist[nb]:
                        self.dist[nb] = self.dist[cur] + nb_weight
                        self.parent[nb] = cur
                        heapq.heappush(pq, (self.dist[nb], nb))

    def update_edge_weight(self, a, b, weight):
        origin_weight = self.graph[a][b]
        self.graph[a][b] = weight
        # Case 1: edge weight decreased
        if weight < origin_weight:
            if self.dist[a] + weight < self.dist[b]:
                self.dist[b] = self.dist[a] + weight
                self.parent[b] = a
                self._initial_dijkstra(b)
        # Case 2: edge weight increased
        elif weight > origin_weight:
            # If the edge was part of the shortest path tree, potential increase propagation is needed
            if self.parent[b] == a:
                self.dist[b] = self.dist[a] + weight
                self._propagate_increase(b)

    def _propagate_increase(self, start):
        # Priority queue for inconsistent/affected nodes (simplified Dijkstra-style repair)
        pq = [(self.dist[start], start)]
        while pq:
            d, cur = heapq.heappop(pq)
            if d == self.dist[cur]:
                # Check alternative shortest paths to cur via neighbors
                for nb, nb_weight in self.graph[cur].items():
                    if cur in self.graph[nb]:
                        if self.dist[nb] + self.graph[nb][cur] < self.dist[cur]:
                            self.dist[cur] = self.dist[nb] + self.graph[nb][cur]
                            self.parent[cur] = nb
                # update previous down path children of cur
                for nb, nb_weight in self.graph[cur].items():
                    if self.parent[nb] == cur:
                        self.dist[nb] = self.dist[cur] + nb_weight
                        heapq.heappush(pq, (self.dist[nb], nb))



