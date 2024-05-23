class TreeNode:
    def __init__(self, data, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def __str__(self):
        return f'TreeNode(data={self.data}, left={self.left}, right={self.right})'


class BinarySearchTree:
    def __init__(self, tree_data):
        def insert(node, value):
            if node == None:
                node = TreeNode(value)
            elif value <= node.data:
                node.left = insert(node.left, value)
            else:
                node.right = insert(node.right, value)
            return node

        self.head = None

        for value in tree_data:
            self.head = insert(self.head, value)

    def data(self):
        return self.head

    def sorted_data(self):
        tree_data = []

        def traverse(node):
            if node == None:
                return
            traverse(node.left)
            tree_data.append(node.data)
            traverse(node.right)

        traverse(self.head)
        return tree_data
