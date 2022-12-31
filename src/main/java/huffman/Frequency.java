package huffman;

import java.util.HashMap;
import java.util.Map;

public class Frequency {

    public static Map<Character, Integer> calculateFrequency(String text){

        Map<Character , Integer> frequencyHashMap = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {

            char character = text.charAt(i);

            if (!frequencyHashMap.containsKey(character)) {
                frequencyHashMap.put(character, 1);
            }
            else {
                int count = frequencyHashMap.get(character) + 1;
                frequencyHashMap.put(character, count);
            }

        }

        return frequencyHashMap;
    }


}
