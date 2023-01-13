package huffman.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

    public static Node root ;

    public static Map<Character, String> createHuffmanTree(Map<Character, Integer> frequencies){

        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet())
            queue.add(new Node(entry.getKey(), entry.getValue()));


        while (queue.size() > 1){

            Node nodeOne = queue.poll();
            Node nodeTwo = queue.poll();

            int frequencyNewNode = nodeOne.getFrequency() + nodeTwo.getFrequency();
            Node newNode = new Node(frequencyNewNode, nodeOne, nodeTwo);

            queue.add(newNode);

        }

        Node rootNode = queue.poll();
        root = rootNode;
        Map<Character, String> table = new HashMap<>();
        assert rootNode != null;
        buildHuffmanCode(rootNode, "", table);

        return table;

    }

    private static void buildHuffmanCode(Node root, String code, Map<Character, String> table){

        if (root.getLeft() == null && root.getRight() == null){
            table.put(root.getCharacter(), code);
        }else{

            buildHuffmanCode(root.getLeft(), code + "0", table);
            buildHuffmanCode(root.getRight(), code + "1", table);

        }

    }
}
