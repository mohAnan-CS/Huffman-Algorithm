package huffman;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {

    public static String readFile(String fileName) throws IOException {
//        // Open the file
//        FileInputStream inputStream = new FileInputStream(fileName);
//
//        // Read the file into a byte array
//        byte[] data = new byte[inputStream.available()];
//        inputStream.read(data);
//        inputStream.close();
//
//        return data;

        BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();
        String text = sb.toString();

        return text ;
    }

}
