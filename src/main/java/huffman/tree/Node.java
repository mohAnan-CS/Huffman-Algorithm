package huffman.tree;

public class Node implements Comparable<Node>{

    private char character ;
    private int frequency ;
    private Node left, right;

    public Node(char character, int frequency){
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public Node(int frequency, Node left, Node right) {
        this.character = '\0';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "character=" + character +
                ", frequency=" + frequency +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public int compareTo(Node other) {
        return this.frequency - other.frequency;
    }
}
