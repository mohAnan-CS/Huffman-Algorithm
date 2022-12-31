package huffman;

import huffman.tree.HuffmanTree;
import huffman.tree.Node;

import java.io.IOException;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {

        try {

            String text = File.readFile("test.txt");
            System.out.println(text);
            System.out.println("------------");
            Map<Character, Integer> characterHashMap = Frequency.calculateFrequency(text);
            System.out.println(characterHashMap);
            System.out.println("-------------");
            System.out.println("Creat huffman tree");
            Node root = HuffmanTree.createHuffmanTree(characterHashMap);
            System.out.println(root.getFrequency());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
