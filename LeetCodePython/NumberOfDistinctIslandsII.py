"""
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example 1:
11000
10000
00001
00011
Given the above grid map, return 1.

Notice that:

11
1
and
 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.

Example 2:
11100
10001
01001
01110
Given the above grid map, return 2.

Here are the two distinct islands:

111
1
and

1
1

Notice that:
111
1
and
1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.

Notice
The length of each dimension in the given grid does not exceed 50.
"""

class NumberOfDistinctIslandsII:
    def numDistinctIslands2(self, grid):
        rows = len(grid)
        cols = len(grid[0])
        distinct = set()

        def dfs(r, c, cells):
            if r < 0 or c < 0 or r >= rows or c >= cols or grid[r][c] != 0:
                return
            grid[r][c] = 0
            cells.apppend((r, c))
            dfs(r + 1, c, cells)
            dfs(r - 1, c, cells)
            dfs(r, c + 1, cells)
            dfs(r, c - 1, cells)

        def canonical(cells):
            shapes = [[] for _ in range(8)]
            for r, c in cells:
                shapes[0].append((r, c))
                shapes[1].append((r, -c))
                shapes[2].append((-r, c))
                shapes[3].append((-r, c))
                shapes[4].append((c, r))
                shapes[5].append((c, -r))
                shapes[6].append((-c, r))
                shapes[7].append((-c, r))
            normalized = []
            for shape in shapes:
                normalized_shape = []
                shape.sort()
                start_r, start_c = shape[0]
                for r, c in shape:
                    normalized_shape.append((r - start_r, c - start_c))
                normalized.append(tuple(normalized_shape))
            return min(normalized)

        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1:
                    cells = []
                    dfs(r, c, cells)
                    distinct.add(canonical(cells))
        return len(distinct)