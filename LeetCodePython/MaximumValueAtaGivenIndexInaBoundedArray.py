class MaximumValueAtaGivenIndexInaBoundedArray:
    def maxValue(self, n: int, index: int, maxSum: int) -> int:
        s, e = 1, maxSum

        def get_sum(target):
            res = 0
            if target > index:
                res += (target + target - index) * (index + 1) // 2
            else:
                res += (target + 1) * target // 2 + index - target + 1

            if target >= n - index:
                res += (target + target - n + 1 + index) * (n - index) // 2
            else:
                res += (target + 1) * target // 2 + n - index - target
            return res - target

        while s < e:
            mid = (s + e + 1) // 2
            if get_sum(mid) <= maxSum:
                s = mid
            else:
                e = mid - 1
        return s
