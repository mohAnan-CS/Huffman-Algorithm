package huffman.tree;

import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

    public static Node createHuffmanTree(Map<Character, Integer> frequencies){

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

        return queue.poll();

    }
}
