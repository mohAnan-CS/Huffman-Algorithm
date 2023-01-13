package huffman;

import huffman.encode.HuffmanEncode;

import java.io.IOException;
import java.util.Map;

public class Runner {

    public static void main(String[] args) throws IOException {

//        try {
//
//            String text = File.readFile("test.txt");
//            System.out.println(text);
//            System.out.println("------------");
//            Map<Character, Integer> characterHashMap = Frequency.calculateFrequency(text);
//            System.out.println(characterHashMap);
//            System.out.println("-------------");
//            System.out.println("Creat huffman tree");
//            Map<Character, String> table = HuffmanTree.createHuffmanTree(characterHashMap);
//            printHuffmanTable(table);
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        HuffmanEncode.encode2("test.txt" , "output.txt");


    }

    private static void printHuffmanTable(Map<Character , String> map){

        for (Map.Entry<Character, String> entry : map.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());

    }


}
