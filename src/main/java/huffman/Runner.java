package huffman;

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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
