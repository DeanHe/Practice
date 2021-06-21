package contest;

/*
A new online video game has been released, and in this video game, there are 15-minute rounds scheduled every quarter-hour period. This means that at HH:00, HH:15, HH:30 and HH:45, a new round starts, where HH represents an integer number from 00 to 23. A 24-hour clock is used, so the earliest time in the day is 00:00 and the latest is 23:59.

Given two strings startTime and finishTime in the format "HH:MM" representing the exact time you started and finished playing the game, respectively, calculate the number of full rounds that you played during your game session.

For example, if startTime = "05:20" and finishTime = "05:59" this means you played only one full round from 05:30 to 05:45. You did not play the full round from 05:15 to 05:30 because you started after the round began, and you did not play the full round from 05:45 to 06:00 because you stopped before the round ended.
If finishTime is earlier than startTime, this means you have played overnight (from startTime to the midnight and from midnight to finishTime).

Return the number of full rounds that you have played if you had started playing at startTime and finished at finishTime.



Example 1:

Input: startTime = "12:01", finishTime = "12:44"
Output: 1
Explanation: You played one full round from 12:15 to 12:30.
You did not play the full round from 12:00 to 12:15 because you started playing at 12:01 after it began.
You did not play the full round from 12:30 to 12:45 because you stopped playing at 12:44 before it ended.
Example 2:

Input: startTime = "20:00", finishTime = "06:00"
Output: 40
Explanation: You played 16 full rounds from 20:00 to 00:00 and 24 full rounds from 00:00 to 06:00.
16 + 24 = 40.
Example 3:

Input: startTime = "00:00", finishTime = "23:59"
Output: 95
Explanation: You played 4 full rounds each hour except for the last hour where you played 3 full rounds.


Constraints:

startTime and finishTime are in the format HH:MM.
00 <= HH <= 23
00 <= MM <= 59
startTime and finishTime are not equal.

hint:
Consider the day as 48 hours instead of 24.
For each round check if you were playing.

analysis:
1 Convert the startTime and finishTime into minutes.
2 If startTime > finishTime, add 24 hours to finishTime because we played overnight.
3 Divide startTime and finishTime by 15, and round them UP and DOWN respectively. In this way we round the startTime and endTime to their next/previous closest 15-minute rounds, respectively. So floor(finish / 15) - ceil(start / 15) is number of rounds inbetween.

 */
public class TheNumberOfFullRoundsYouHavePlayed {
    public int numberOfRounds(String startTime, String finishTime) {
        int startHour = Integer.parseInt(startTime.substring(0, 2));
        int startMin = Integer.parseInt(startTime.substring(3, 5));
        int finishHour = Integer.parseInt(finishTime.substring(0, 2));
        int finishMin = Integer.parseInt(finishTime.substring(3, 5));
        int start = 60 * startHour + startMin;
        int end = 60 * finishHour + finishMin;
        if(end < start){
            end += 60 * 24;
        }
        // max(0, floor(finish / 15) - ceil(start / 15))
        return Math.max(0, end / 15 - (start + 14) / 15);
    }

    public int numberOfRounds2(String startTime, String finishTime) {
        int startHour = Integer.parseInt(startTime.substring(0, 2));
        int startMin = Integer.parseInt(startTime.substring(3, 5));
        int finishHour = Integer.parseInt(finishTime.substring(0, 2));
        int finishMin = Integer.parseInt(finishTime.substring(3, 5));
        int round = 0;
        if (startHour < finishHour) {
            round = 4 * (finishHour - startHour - 1) + (60 - startMin) / 15 + finishMin / 15;
        }
        if (startHour > finishHour || (startHour == finishHour && startMin > finishMin)) {
            round = 4 * (finishHour + 23 - startHour) + (60 - startMin) / 15 + finishMin / 15;
        }
        if (startHour == finishHour && startMin < finishMin) {
            round = finishMin / 15 - startMin / 15 - 1;
        }
        return round;
    }
}

