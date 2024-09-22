"""
Given a string formula representing a chemical formula, return the count of each atom.

The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.

For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
Two formulas are concatenated together to produce another formula.

For example, "H2O2He3Mg4" is also a formula.
A formula placed in parentheses, and a count (optionally added) is also a formula.

For example, "(H2O2)" and "(H2O2)3" are formulas.
Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

The test cases are generated so that all the values in the output fit in a 32-bit integer.

Example 1:
Input: formula = "H2O"
Output: "H2O"
Explanation: The count of elements are {'H': 2, 'O': 1}.

Example 2:
Input: formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

Example 3:
Input: formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.

Constraints:
1 <= formula.length <= 1000
formula consists of English letters, digits, '(', and ')'.
formula is always valid.

hints:
1 To parse formula[i:], when we see a `'('`, we will parse recursively whatever is inside the brackets (up to the correct closing ending bracket) and add it to our count, multiplying by the following multiplicity if there is one. Otherwise, we should see an uppercase character: we will parse the rest of the letters to get the name, and add that (plus the multiplicity if there is one.)
"""

class NumberOfAtoms:
    def countOfAtoms(self, formula: str) -> str:
        n: int = len(formula)
        result_counter: dict[str, int] = {}
        parenthesis_stack: list[dict[str, int]] = []
        cur_ind = 0

        while cur_ind < n:
            cur_char: str = formula[cur_ind]

            if cur_char == "(":
                cur_ind += 1
                parenthesis_stack.append({})
                continue

            if cur_char == ")":
                mult: str = ""
                cur_ind += 1

                while cur_ind < n and formula[cur_ind].isdigit():
                    mult += formula[cur_ind]
                    cur_ind += 1

                last_counter: dict[str, int] = parenthesis_stack.pop()
                target: dict[str, int] = parenthesis_stack[-1] if parenthesis_stack else result_counter
                for elem, counter in last_counter.items():
                    if elem not in target:
                        target[elem] = 0
                    target[elem] += counter * (int(mult) if mult else 1)
                continue

            cur_elem: str = ""
            cur_counter: str = ""
            target: dict[str, int] = parenthesis_stack[-1] if parenthesis_stack else result_counter

            while cur_ind < n and cur_char not in "()":
                if cur_char.isalpha():
                    if cur_char.isupper() and cur_elem != "":
                        if not cur_elem in target:
                            target[cur_elem] = 0
                        target[cur_elem] += int(cur_counter) if cur_counter else 1
                        cur_counter = ""
                        cur_elem = ""
                    cur_elem += cur_char
                else:
                    cur_counter += cur_char
                cur_ind += 1
                if cur_ind != n:
                    cur_char = formula[cur_ind]

            target = parenthesis_stack[-1] if parenthesis_stack else result_counter
            if not cur_elem in target:
                target[cur_elem] = 0
            target[cur_elem] += int(cur_counter) if cur_counter else 1

        parts: list[str] = [
            elem + str(counter) if not counter == 1 else elem for elem, counter in result_counter.items()
        ]
        parts.sort()

        return "".join(parts)
