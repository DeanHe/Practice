class Solution:
    def greatestLetter(self, s: str) -> str:
        res = ""
        for c in list(range(ord('a'), ord('z') + 1)):
            letter = chr(c)
            if letter.upper() in s and letter.lower() in s:
                res = letter.upper()
        return res
