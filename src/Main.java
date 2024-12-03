import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree();
        tree1.root = new Node(10);
        tree1.root.right = new Node(7);
        tree1.root.left = new Node(2);
        tree1.root.left.left = new Node(8);
        tree1.root.left.right = new Node(4);

        tree1.printTree(tree1.root, -5);
        System.out.println(tree1.testTree(tree1.root));
        if (!tree1.testTree(tree1.root)) {tree1.fixTree();}
        tree1.printTree(tree1.root, -5);
        System.out.println(tree1.testTree(tree1.root));

        BinaryTree tree2 = new BinaryTree();
        tree2.root = new Node(10);
        tree2.root.left = new Node(30);
        tree2.root.left.left = new Node(20);
        tree2.root.right = new Node(15);
        tree2.root.right.right = new Node(5);

        tree2.printTree(tree2.root, -5);
        System.out.println(tree2.testTree(tree2.root));
        if (!tree2.testTree(tree2.root)) {tree2.fixTree();}
        tree2.printTree(tree2.root, -5);
        System.out.println(tree2.testTree(tree2.root));
    }
}

class Node {
    int id ;
    Node left;
    Node right;

    public Node(int item) {
        id = item;
    }
}

class BinaryTree {
    Node root;
    int nodeNum;

    boolean testTree(Node node) {
        if (node == null) {return true;}
        boolean leftBool = testTree(node.left);
        if (node.left != null) {
            if (node.left.id > node.id) {return false;}
        }
        if (node.right != null) {
            if (node.id > node.right.id) {return false;}
        }
        boolean rightBool = testTree(node.right);
        return leftBool && rightBool;
    }

    ArrayList<Integer> listElements(Node node, ArrayList<Integer> idList) {
        if (node == null) {return null;}
        listElements(node.left, idList);
        idList.add(node.id);
        listElements(node.right, idList);
        return idList;
    }

    void reassignNodes(Node node, ArrayList<Integer> idList) {
        if (node == null) {return;}
        reassignNodes(node.left, idList);
        node.id = idList.get(nodeNum++);
        reassignNodes(node.right, idList);
    }

    void fixTree() {
        ArrayList<Integer> idList = listElements(root, new ArrayList<>());
        Collections.sort(idList);
        nodeNum = 0;
        reassignNodes(root, idList);
    }

    void printTree(Node node, int space) {
        if (node == null) {return;}

        space += 5;
        printTree(node.right, space);

        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println(node.id);

        printTree(node.left, space);
    }
}
