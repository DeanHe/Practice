import ast
from collections import defaultdict


# ---------------- AST Nodes ----------------

class Expr:
    pass


class Number(Expr):
    def __init__(self, value):
        self.value = value


class Ref(Expr):
    def __init__(self, name):
        self.name = name


class Binary(Expr):
    def __init__(self, left, op, right):
        self.left = left
        self.op = op
        self.right = right


# ---------------- Parser ----------------

class FormulaParser:

    def parse(self, text: str) -> Expr:
        tree = ast.parse(text, mode="eval")
        return self._build(tree.body)

    def _build(self, node):

        if isinstance(node, ast.Constant):
            return Number(node.value)

        if isinstance(node, ast.Name):
            return Ref(node.id)

        if isinstance(node, ast.BinOp):
            return Binary(
                self._build(node.left),
                node.op,
                self._build(node.right)
            )

        raise ValueError(f"Unsupported expression {node}")


# ---------------- Spreadsheet ----------------

class Spreadsheet:

    def __init__(self):

        self.parser = FormulaParser()

        # cell -> expression tree
        self.expr = {}

        # cell -> cached value
        self.cache = {}

        # dependency graph
        #
        # A = B + C
        #
        # B -> A
        # C -> A
        #
        self.dependents = defaultdict(set)

        # direct references
        self.references = defaultdict(set)

    # ---------------- Public API ----------------

    def set(self, statement: str):

        lhs, rhs = statement.split("=")
        lhs = lhs.strip()
        rhs = rhs.strip()

        expr = self.parser.parse(rhs)

        # remove previous dependency edges
        for ref in self.references[lhs]:
            self.dependents[ref].discard(lhs)

        self.references[lhs].clear()

        # build dependency graph
        refs = self._collect_refs(expr)

        self.references[lhs] = refs

        for ref in refs:
            self.dependents[ref].add(lhs)

        self.expr[lhs] = expr

        self._invalidate(lhs)

    def get(self, cell):

        return self._eval(cell, set())

    # ---------------- Evaluation ----------------

    def _eval(self, cell, visiting):

        if cell in self.cache:
            return self.cache[cell]

        if cell in visiting:
            raise ValueError(
                f"Circular reference detected at {cell}"
            )

        if cell not in self.expr:
            raise KeyError(f"{cell} not defined")

        visiting.add(cell)

        value = self._eval_expr(self.expr[cell], visiting)

        visiting.remove(cell)

        self.cache[cell] = value

        return value

    def _eval_expr(self, expr, visiting):

        if isinstance(expr, Number):
            return expr.value

        if isinstance(expr, Ref):
            return self._eval(expr.name, visiting)

        if isinstance(expr, Binary):

            left = self._eval_expr(expr.left, visiting)
            right = self._eval_expr(expr.right, visiting)

            if isinstance(expr.op, ast.Add):
                return left + right

            if isinstance(expr.op, ast.Sub):
                return left - right

            if isinstance(expr.op, ast.Mult):
                return left * right

            if isinstance(expr.op, ast.Div):
                return left / right

            raise ValueError("Unsupported operator")

        raise ValueError("Unknown expression")

    # ---------------- Dependency ----------------

    def _collect_refs(self, expr):

        refs = set()

        def dfs(node):

            if isinstance(node, Ref):
                refs.add(node.name)

            elif isinstance(node, Binary):
                dfs(node.left)
                dfs(node.right)

        dfs(expr)

        return refs

    def _invalidate(self, cell):

        if cell in self.cache:
            del self.cache[cell]

        for nxt in self.dependents[cell]:
            self._invalidate(nxt)