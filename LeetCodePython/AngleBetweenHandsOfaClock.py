"""
Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.

Example 1:
Input: hour = 12, minutes = 30
Output: 165

Example 2:
Input: hour = 3, minutes = 30
Output: 75

Example 3:
Input: hour = 3, minutes = 15
Output: 7.5

Constraints:
1 <= hour <= 12
0 <= minutes <= 59
"""

class AngleBetweenHandsOfaClock:
    def angleClock(self, hour: int, minutes: int) -> float:
        minute_hand = 360.0 * minutes / 60
        hour_hand = (hour % 12 + minutes / 60.0) * (360.0 / 12)
        diff = abs(minute_hand - hour_hand)
        if diff < 180.0:
            return diff
        return 360.0 - diff