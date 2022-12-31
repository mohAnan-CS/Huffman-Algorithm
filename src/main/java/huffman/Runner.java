package huffman;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) {

        try {

            String text = File.readFile("test.txt");
            System.out.println(text);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
