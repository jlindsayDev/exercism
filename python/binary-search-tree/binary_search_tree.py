class TreeNode:
    def __init__(self, data, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def __str__(self):
        return f'TreeNode(data={self.data}, left={self.left}, right={self.right})'

    def __iter__(self):
        if self.left:
            yield from self.left

        yield self.data

        if self.right:
            yield from self.right


class BinarySearchTree:
    def __init__(self, tree_data):
        self.root = None

        if tree_data:
            for value in tree_data:
                self.root = self.insert(self.root, value)

    def __iter__(self):
        yield from self.data()

    def data(self):
        return self.root

    def sorted_data(self):
        return list(self)

    @classmethod
    def insert(cls, node, value):
        if node == None:
            node = TreeNode(value)
        elif value <= node.data:
            node.left = cls.insert(node.left, value)
        else:
            node.right = cls.insert(node.right, value)
        return node
