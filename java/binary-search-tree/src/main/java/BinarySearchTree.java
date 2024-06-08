import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    void insert(T valueToInsert) {
        Node<T> node = new Node<>(valueToInsert);
        if (this.root == null) {
            this.root = node;
        } else {
            Node<T> cursor = this.root;
            boolean inserted = false;
            while (!inserted) {
                T current = cursor.getData();
                if (valueToInsert.compareTo(current) <= 0) {
                    Node<T> next = cursor.getLeft();
                    if (next == null) {
                        cursor.setLeft(node);
                        inserted = true;
                    } else {
                        cursor = next;
                    }
                } else {
                    Node<T> next = cursor.getRight();
                    if (next == null) {
                        cursor.setRight(node);
                        inserted = true;
                    } else {
                        cursor = next;
                    }
                }
            }
        }
    }

    List<T> getAsSortedList() {
        return this.sortedRec(new ArrayList<>(), this.root);
    }

    List<T> sortedRec(List<T> queue, Node<T> node) {
        if (node != null) {
            sortedRec(queue, node.getLeft());
            queue.add(node.getData());
            sortedRec(queue, node.getRight());
        }
        return queue;
    }

    List<T> getAsLevelOrderList() {
        return this.levelRec(new ArrayList<>(), Collections.singletonList(this.root));
    }

    List<T> levelRec(List<T> queue, List<Node<T>> children) {
        if (children.isEmpty()) {
            return queue;
        }
        List<Node<T>> next = new ArrayList<>();
        children.stream()
            .filter(n -> n != null)
            .forEach(n -> {
                queue.add(n.getData());
                next.add(n.getLeft());
                next.add(n.getRight());
            });
        return levelRec(queue, next);
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data) {
            this(data, null, null);
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        void setLeft(Node<T> left) {
            this.left = left;
        }

        void setRight(Node<T> right) {
            this.right = right;
        }

        T getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Character> bst = new BinarySearchTree<>();
        List<Character> treeData = Collections.unmodifiableList(
                Arrays.asList('4', '2', '6', '1', '3', '5', '7')
        );
        treeData.forEach(bst::insert);
        System.out.println(treeData);
        System.out.println(bst.getAsSortedList());
        System.out.println(bst.getAsLevelOrderList());
    }
}
