class TreeNode:
    def __init__(self, data, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def __str__(self):
        return f'TreeNode(data={self.data}, left={self.left}, right={self.right})'

    def __iter__(self):
        if self.left:
            for n in self.left:
                yield n

        yield self.data

        if self.right:
            for n in self.right:
                yield n


class BinarySearchTree:
    def __init__(self, tree_data):
        if not tree_data:
            self.root = None
        else:
            self.root = TreeNode(tree_data[0])
            for value in tree_data[1:]:
                self.insert(self.root, value)

    def __iter__(self):
        return iter(self.data())

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
