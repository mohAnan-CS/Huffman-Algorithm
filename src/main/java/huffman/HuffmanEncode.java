package huffman;

import huffman.tree.HuffmanTree;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HuffmanEncode {

    public static void encode(String inputFile, String outputFile) throws IOException {

        // Read the input file and count the frequency of each character
        //Map<Character, Integer> frequencies = new HashMap<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
//            int ch;
//            while ((ch = reader.read()) != -1) {
//                char c = (char) ch;
//                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
//            }
//        } catch (IOException e) {
//            // Handle the exception
//        }

        String text = File.readFile(inputFile);
        Map<Character, Integer> characterHashMap = Frequency.calculateFrequency(text);
        System.out.println("Creat huffman tree");
        Map<Character, String> table = HuffmanTree.createHuffmanTree(characterHashMap);

        for (Map.Entry<Character, String> entry : table.entrySet()) {
            System.out.println(entry.getValue());
        }

        // Open the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Open the input file and encode the characters
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
//                int ch;
//                String code = "";
//                while ((ch = reader.read()) != -1) {
//                    char c = (char) ch;
//                    code += table.get(c);
//                    System.out.println(c);
//                    System.out.println(table.get(c));
//                    System.out.println("--------------------");
//                }

                String line;
                String sentence = "";
                while ((line = reader.readLine()) != null) {
                    // Process the line

                }
                //writer.write(code);
            } catch (IOException e) {
                // Handle the exception
            }
        } catch (IOException e) {
            // Handle the exception
        }




    }

//    public static String build_header(String text) {
//        // Create a frequency table to store the frequencies of each character
//        Map<Character, Integer> freq_table = new HashMap<>();
//        for (char ch : text.toCharArray()) {
//            freq_table.put(ch, freq_table.getOrDefault(ch, 0) + 1);
//        }
//
//        // Sort the frequency table by character in ascending order
//        TreeMap<Character, Integer> sorted_freq_table = new TreeMap<>(freq_table);
//
//        // Build the header string by concatenating the character and its frequency
//        StringBuilder header = new StringBuilder();
//        for (Map.Entry<Character, Integer> entry : sorted_freq_table.entrySet()) {
//            header.append(entry.getKey()).append(entry.getValue());
//        }
//
//        return header.toString();
//    }

}
