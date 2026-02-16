"""
You are asked to design an auction system that manages bids from multiple users in real time.

Each bid is associated with a userId, an itemId, and a bidAmount.

Implement the AuctionSystem class:

AuctionSystem(): Initializes the AuctionSystem object.
void addBid(int userId, int itemId, int bidAmount): Adds a new bid for itemId by userId with bidAmount. If the same userId already has a bid on itemId, replace it with the new bidAmount.
void updateBid(int userId, int itemId, int newAmount): Updates the existing bid of userId for itemId to newAmount. It is guaranteed that this bid exists.
void removeBid(int userId, int itemId): Removes the bid of userId for itemId. It is guaranteed that this bid exists.
int getHighestBidder(int itemId): Returns the userId of the highest bidder for itemId. If multiple users have the same highest bidAmount, return the user with the highest userId. If no bids exist for the item, return -1.

Example 1:
Input:
["AuctionSystem", "addBid", "addBid", "getHighestBidder", "updateBid", "getHighestBidder", "removeBid", "getHighestBidder", "getHighestBidder"]
[[], [1, 7, 5], [2, 7, 6], [7], [1, 7, 8], [7], [2, 7], [7], [3]]

Output:
[null, null, null, 2, null, 1, null, 1, -1]

Explanation
AuctionSystem auctionSystem = new AuctionSystem(); // Initialize the Auction system
auctionSystem.addBid(1, 7, 5); // User 1 bids 5 on item 7
auctionSystem.addBid(2, 7, 6); // User 2 bids 6 on item 7
auctionSystem.getHighestBidder(7); // return 2 as User 2 has the highest bid
auctionSystem.updateBid(1, 7, 8); // User 1 updates bid to 8 on item 7
auctionSystem.getHighestBidder(7); // return 1 as User 1 now has the highest bid
auctionSystem.removeBid(2, 7); // Remove User 2's bid on item 7
auctionSystem.getHighestBidder(7); // return 1 as User 1 is the current highest bidder
auctionSystem.getHighestBidder(3); // return -1 as no bids exist for item 3

Constraints:
1 <= userId, itemId <= 5 * 10^4
1 <= bidAmount, newAmount <= 10^9
At most 5 * 10^4 total calls to addBid, updateBid, removeBid, and getHighestBidder.
The input is generated such that for updateBid and removeBid, the bid from the given userId for the given itemId will be valid.

hints:
1 Maintain a map from itemId to its active bids.
2 For each item, use a data structure ordered by (bidAmount, userId) to get the highest bidder efficiently.
3 On addBid or updateBid, remove the old entry (if any) and insert the new one.
4 On removeBid, delete the corresponding entry; return the top element for getHighestBidder.

analysis:
heap, lazy deletion
"""
import heapq
from collections import defaultdict


class AuctionSystem:

    def __init__(self):
        self.bid_map = defaultdict(dict)
        self.bid_heap = defaultdict(list)

    def addBid(self, userId: int, itemId: int, bidAmount: int) -> None:
        self.bid_map[itemId][userId] = bidAmount
        heapq.heappush(self.bid_heap[itemId], (-bidAmount, -userId))

    def updateBid(self, userId: int, itemId: int, newAmount: int) -> None:
        self.addBid(userId, itemId, newAmount)

    def removeBid(self, userId: int, itemId: int) -> None:
        self.bid_map[itemId].pop(userId, None)

    def getHighestBidder(self, itemId: int) -> int:
        if itemId not in self.bid_heap:
            return -1
        pq = self.bid_heap[itemId]
        while pq:
            amount, user_id = pq[0]
            amount = -amount
            user_id = -user_id
            if user_id in self.bid_map[itemId] and self.bid_map[itemId][user_id] == amount:
                return user_id
            # lazy delete
            heapq.heappop(pq)
        return -1
